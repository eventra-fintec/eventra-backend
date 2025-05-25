package pe.edu.upc.eventra.events_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upc.eventra.events_service.model.dtos.BulkUploadReportDTO;
import pe.edu.upc.eventra.events_service.model.entities.CategoryEvent;
import pe.edu.upc.eventra.events_service.model.entities.Event;
import pe.edu.upc.eventra.events_service.repository.CategoryEventRepository;
import pe.edu.upc.eventra.events_service.repository.EventRepository;
import pe.edu.upc.eventra.events_service.util.CsvHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BulkEventUploadService {

    private final EventRepository eventRepository;
    private final CategoryEventRepository categoryEventRepository;

    public BulkUploadReportDTO processCsvFile(MultipartFile file) {
        List<String> errors = new ArrayList<>();
        int successCount = 0;
        int totalCount = 0;

        List<String[]> rows = CsvHelper.readCsv(file);
        if (rows == null) {
            return new BulkUploadReportDTO(0, 0, 0, List.of("Archivo CSV inválido o corrupto"));
        }

        // Suponemos que la primera fila es header
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (int i = 1; i < rows.size(); i++) {
            totalCount++;
            String[] row = rows.get(i);

            try {
                String title = row[0];
                String description = row[1];
                LocalDateTime startDate = LocalDateTime.parse(row[2], formatter);
                LocalDateTime endDate = LocalDateTime.parse(row[3], formatter);
                String location = row[4];
                Long organizerId = Long.parseLong(row[5]);
                String url = row[6];
                String categoryName = row[7];

                // Buscar o crear categoría
                CategoryEvent category = categoryEventRepository.findByName(categoryName)
                        .orElseGet(() -> categoryEventRepository.save(CategoryEvent.builder()
                                .name(categoryName)
                                .build()));

                Event event = Event.builder()
                        .title(title)
                        .description(description)
                        .startDate(startDate)
                        .endDate(endDate)
                        .location(location)
                        .organizerId(organizerId)
                        .url(url)
                        .categoryEvent(category)
                        .build();

                eventRepository.save(event);
                successCount++;

            } catch (Exception e) {
                errors.add("Fila " + (i + 1) + ": " + e.getMessage());
            }
        }

        int failureCount = totalCount - successCount;

        return new BulkUploadReportDTO(totalCount, successCount, failureCount, errors);
    }
}
