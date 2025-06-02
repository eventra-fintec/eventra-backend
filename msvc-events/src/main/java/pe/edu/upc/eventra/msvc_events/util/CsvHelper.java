package pe.edu.upc.eventra.msvc_events.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class CsvHelper {

    private CsvHelper() {}

    public static List<String[]> readCsv(MultipartFile file) {
        List<String[]> records = new ArrayList<>();
        try (
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
                CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
        ) {
            for (CSVRecord csvRecord : csvParser) {
                int size = csvRecord.size();
                String[] row = new String[size];
                for (int i = 0; i < size; i++) {
                    row[i] = csvRecord.get(i);
                }
                records.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return records;
    }
}
