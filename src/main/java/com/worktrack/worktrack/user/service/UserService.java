package com.worktrack.worktrack.user.service;

import com.worktrack.worktrack.user.dto.UserRequestDto;
import com.worktrack.worktrack.user.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getUsers();
    UserResponseDto getUserById(Long Id);
    UserResponseDto createUser(UserRequestDto user);
    UserResponseDto updateUser(Long Id, UserRequestDto user);
    void deleteUser(Long Id, UserRequestDto user);
}
