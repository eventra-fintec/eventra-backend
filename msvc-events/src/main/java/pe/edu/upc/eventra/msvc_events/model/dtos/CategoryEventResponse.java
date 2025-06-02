package pe.edu.upc.eventra.msvc_events.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryEventResponse {
    private Long id;
    private String name;
}

