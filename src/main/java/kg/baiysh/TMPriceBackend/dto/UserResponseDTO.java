package kg.baiysh.TMPriceBackend.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private String id;
    private String username;
    private String fio;
    private String role;
    private String status;
}
