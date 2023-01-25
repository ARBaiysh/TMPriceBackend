package kg.baiysh.TMPriceBackend.services;

import kg.baiysh.TMPriceBackend.dto.UserDTO;
import kg.baiysh.TMPriceBackend.dto.UserResponseDTO;
import kg.baiysh.TMPriceBackend.entity.User;
import kg.baiysh.TMPriceBackend.entity.enums.ERole;
import kg.baiysh.TMPriceBackend.entity.enums.EStatus;
import kg.baiysh.TMPriceBackend.exceptions.ObjExistException;
import kg.baiysh.TMPriceBackend.facade.UserFacade;
import kg.baiysh.TMPriceBackend.repo.UserRepo;
import kg.baiysh.TMPriceBackend.rest.RestClientUsers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepo userRepo;
    private final RestClientUsers restClientUsers;
    private final BCryptPasswordEncoder passwordEncoder;


    public void createUser(UserDTO userDTO) {
        if (userRepo.existsUserByUsername(userDTO.getLogin())) {
            throw new ObjExistException("The user login " + userDTO.getLogin() + " already exist. Please check credentials");
        } else {
            User user = new User();
            user.setId(userDTO.getUid());
            user.setUsername(userDTO.getLogin());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setFio(userDTO.getFio());
            user.setRole(convertRole(userDTO.getRole()));
            user.setStatus(convertStatus(userDTO.getStatus()));

            log.info("Saving User {}", user.getFio());
            userRepo.save(user);
        }
    }

    private void createUsers(List<UserDTO> userDTOS) {
        userDTOS.forEach(this::createUser);
    }


    public UserResponseDTO getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        User user = userRepo.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
        return UserFacade.userToUserResponseDTO(user);
    }

    public User getUserByUid(String uid) {
        return userRepo.findById(uid).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    public void uploadUsers() {
        List<UserDTO> body = restClientUsers.getUsers().getBody();
        createUsers(Objects.requireNonNull(body));
    }

    public UserResponseDTO updateUser(UserDTO userDTO) {
        if (!userRepo.existsById(userDTO.getUid())) {
            throw new ObjExistException("uid " + userDTO.getUid() + " not found");
        } else {
            User user = userRepo.findById(userDTO.getUid()).get();
            user.setUsername(userDTO.getLogin());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setFio(userDTO.getFio());
            user.setRole(convertRole(userDTO.getRole()));
            user.setStatus(convertStatus(userDTO.getStatus()));

            log.info("Update User {}", user.getFio());
            User updateUser = userRepo.save(user);
            return UserFacade.userToUserResponseDTO(updateUser);
        }
    }


    private ERole convertRole(String role) {
        switch (role) {
            case "ADMIN":
                return ERole.ROLE_ADMIN;
            case "SALESMAN":
                return ERole.ROLE_SALESMAN;
            case "DEALER":
                return ERole.ROLE_DEALER;
            default:
                return ERole.ROLE_USER;
        }
    }

    private EStatus convertStatus(String status) {
        if (status.equals("ACTIVE")) {
            return EStatus.ACTIVE;
        }
        return EStatus.BANNED;
    }
}
