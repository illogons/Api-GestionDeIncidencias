package com.worktrack.worktrack.user.service;

import com.worktrack.worktrack.department.Infrastructure.assembler.DepartmentAssembler;
import com.worktrack.worktrack.department.domain.model.Department;
import com.worktrack.worktrack.department.domain.respository.DepartmentRepository;
import com.worktrack.worktrack.user.Infrastructure.assembler.UserAssembler;
import com.worktrack.worktrack.user.domain.model.User;
import com.worktrack.worktrack.user.domain.repository.UserRepository;
import com.worktrack.worktrack.user.dto.UserRequestDto;
import com.worktrack.worktrack.user.dto.UserResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserAssembler  userAssembler;
    private final DepartmentRepository departmentRepository;


    @Override
    @Transactional
    public List<UserResponseDto> getUsers() {
        log.info("Getting all users");

        List<UserResponseDto> users = userRepository.findAll()
                .stream()
                .map(userAssembler::toDto)
                .toList();

        log.info("Getting all users successfully");
        return users;

    }

    @Override
    @Transactional
    public UserResponseDto getUserById(Long Id) {
        log.info("Getting user by id: {}", Id);

        User user = userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        UserResponseDto nuevo = userAssembler.toDto(user);

        log.info("Getting user successfully");
        return nuevo;

    }

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto dto) {
        log.info("Creating user: {}", dto);

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + dto.getDepartmentId()));

        User model= userAssembler.toModel(dto);
        model.setDepartmentId(department);
        log.info("Creating user successfully");

        return userAssembler.toDto(userRepository.save(model));

    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long Id, UserRequestDto dto) {
        log.info("Updating user: {}", dto);

        User user = userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + dto.getDepartmentId()));

        userAssembler.toUpdate(dto, user);
        user.setDepartmentId(department);

        User saveUser = userRepository.save(user);
        log.info("Updating user successfully");
        return userAssembler.toDto(saveUser);

    }

    @Override
    @Transactional
    public void deleteUser(Long Id, UserRequestDto user) {
        log.info("Deleting user: {}", user);

        User usuario = userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        usuario.setActive(false);
        userRepository.save(usuario);
        log.info("Deleting user successfully");
    }
}
