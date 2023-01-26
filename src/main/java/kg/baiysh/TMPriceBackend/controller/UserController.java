package kg.baiysh.TMPriceBackend.controller;

import kg.baiysh.TMPriceBackend.dto.UserDTO;
import kg.baiysh.TMPriceBackend.dto.UserResponseDTO;
import kg.baiysh.TMPriceBackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    public ResponseEntity<UserResponseDTO> getCurrentUser(Principal principal) {
        UserResponseDTO currentUser = userService.getUserByPrincipal(principal);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @GetMapping("/upload")
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<?> uploadUsers() {
        userService.uploadUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO) {
        UserResponseDTO userResponseDTO = userService.updateUser(userDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody UserDTO userDTO) {
        UserResponseDTO userResponseDTO = userService.addUser(userDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/del/{uid}")
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<?> del(@PathVariable String uid) {
        userService.delUser(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

