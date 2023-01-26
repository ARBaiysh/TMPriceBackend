package kg.baiysh.TMPriceBackend.facade;

import kg.baiysh.TMPriceBackend.dto.UserResponseDTO;
import kg.baiysh.TMPriceBackend.entity.User;
import kg.baiysh.TMPriceBackend.entity.enums.ERole;

public class UserFacade {
    public static UserResponseDTO userToUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setFio(user.getFio());
        userResponseDTO.setRole(convertRole(user.getRole()));
        userResponseDTO.setStatus(user.getStatus().name());
        return userResponseDTO;
    }

    private static String convertRole(ERole role) {
        switch (role) {
            case ROLE_ADMIN:
                return "Админ";
            case ROLE_SALESMAN:
                return "Продавец";
            case ROLE_DEALER:
                return "Диллер";
            default:
                return "Пользователь";
        }
    }
}
