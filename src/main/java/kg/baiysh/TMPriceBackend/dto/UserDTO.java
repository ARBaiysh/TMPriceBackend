package kg.baiysh.TMPriceBackend.dto;

import kg.baiysh.TMPriceBackend.entity.enums.ERole;
import kg.baiysh.TMPriceBackend.entity.enums.EStatus;
import lombok.Data;

@Data
public class UserDTO {
    private String uid;
    private String login;
    private String password;
    private String fio;
    private ERole role;
    private EStatus status;
}
