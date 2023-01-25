package kg.baiysh.TMPriceBackend.facade;

import kg.baiysh.TMPriceBackend.dto.UserResponseDTO;
import kg.baiysh.TMPriceBackend.entity.User;

public class UserFacade {
    public static UserResponseDTO userToUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setFio(user.getFio());
        userResponseDTO.setRole(user.getRole().name());
        userResponseDTO.setStatus(user.getStatus().name());
        return userResponseDTO;

    }
}
