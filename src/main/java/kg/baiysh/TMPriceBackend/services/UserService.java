package kg.baiysh.TMPriceBackend.services;

import kg.baiysh.TMPriceBackend.dto.UserDTO;
import kg.baiysh.TMPriceBackend.entity.User;
import kg.baiysh.TMPriceBackend.exceptions.ObjExistException;
import kg.baiysh.TMPriceBackend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepo userRepo;
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
            user.setRole(userDTO.getRole());
            user.setStatus(userDTO.getStatus());

            log.info("Saving User {}", user.getFio());
            userRepo.save(user);
        }
    }

    private void createUsers(List<UserDTO> userDTOS) {
        userDTOS.forEach(this::createUser);
    }


    public User getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepo.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }

    public User getUserByUid(String uid) {
        return userRepo.findById(uid).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
