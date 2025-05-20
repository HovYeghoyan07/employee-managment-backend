package com.example.employeemanagment.service;

import com.example.employeemanagment.dto.SaveUserRequest;
import com.example.employeemanagment.dto.UserAuthRequest;
import com.example.employeemanagment.dto.UserAuthResponse;
import com.example.employeemanagment.entity.User;

import java.util.Optional;

public interface UserService {

    boolean save(SaveUserRequest user);

    Optional<User> findByEmail(String email);

    UserAuthResponse login(UserAuthRequest userRequest);


}
