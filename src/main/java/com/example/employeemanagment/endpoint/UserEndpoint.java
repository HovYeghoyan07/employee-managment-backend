package com.example.employeemanagment.endpoint;

import com.example.employeemanagment.dto.SaveUserRequest;
import com.example.employeemanagment.dto.UserAuthRequest;
import com.example.employeemanagment.dto.UserAuthResponse;
import com.example.employeemanagment.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserEndpoint {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserAuthResponse> login( @RequestBody @Valid UserAuthRequest userAuthRequest) {
        return ResponseEntity.ok(userService.login(userAuthRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register( @RequestBody @Valid SaveUserRequest saveUserRequest) {
        if (userService.save(saveUserRequest)) {
            return ResponseEntity.ok("User registered successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
    }
}
