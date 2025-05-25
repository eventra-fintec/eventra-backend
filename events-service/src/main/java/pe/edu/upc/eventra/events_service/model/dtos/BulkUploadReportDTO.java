package pe.edu.upc.eventra.events_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BulkUploadReportDTO {
    private int total;
    private int success;
    private int failure;
    private List<String> errors;
}
