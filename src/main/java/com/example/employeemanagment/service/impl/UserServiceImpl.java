package com.example.employeemanagment.service.impl;

import com.example.employeemanagment.dto.SaveUserRequest;
import com.example.employeemanagment.dto.UserAuthRequest;
import com.example.employeemanagment.dto.UserAuthResponse;
import com.example.employeemanagment.entity.User;
import com.example.employeemanagment.repository.UserRepository;
import com.example.employeemanagment.service.UserService;
import com.example.employeemanagment.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean save(SaveUserRequest userRequest) {
        Optional<User> userOptional = userRepository.findByEmail(userRequest.getEmail());
        if (userOptional.isPresent()) {
            return false;
        }
        User user = User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
        userRepository.save(user);
        return true;
    }

    @Override
    public UserAuthResponse login(UserAuthRequest userRequest) {
        Optional<User> userInDb = userRepository.findByEmail(userRequest.getEmail());
        if (userInDb.isPresent()) {
            User user = userInDb.get();
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
                throw new RuntimeException("Invalid password");
            }
            return UserAuthResponse.builder()
                    .token(jwtTokenUtil.generateToken(user.getEmail()))
                    .name(user.getName())
                    .surname(user.getSurname())
                    .userId(user.getId())
                    .build();
        }
        throw new RuntimeException("User does not exist");
    }

}

