package kg.baiysh.TMPriceBackend.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String uid;
    private String login;
    private String password;
    private String fio;
    private String role;
    private String status;
}
