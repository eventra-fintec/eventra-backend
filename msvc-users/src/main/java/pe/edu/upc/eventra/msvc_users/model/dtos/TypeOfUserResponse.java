package pe.edu.upc.eventra.msvc_users.model.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeOfUserResponse {
    private Long typeId;
    private String description;
}
