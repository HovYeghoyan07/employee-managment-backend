package com.example.employeemanagment.mapper;

import com.example.employeemanagment.dto.SaveUserRequest;
import com.example.employeemanagment.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SaveUserRequest saveUserRequest);
}
