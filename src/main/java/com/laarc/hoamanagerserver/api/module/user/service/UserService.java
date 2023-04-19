package com.laarc.hoamanagerserver.api.module.user.service;

import com.laarc.hoamanagerserver.api.crud.BaseCrudService;
import com.laarc.hoamanagerserver.api.dto.user.CreateUser;
import com.laarc.hoamanagerserver.api.dto.user.UserResponse;
import com.laarc.hoamanagerserver.exception.UserNotFoundException;
import com.laarc.hoamanagerserver.exception.UserRoleNotFound;
import com.laarc.hoamanagerserver.exception.http.ConflictException;
import com.laarc.hoamanagerserver.shared.model.User;
import com.laarc.hoamanagerserver.shared.model.UserRole;
import com.laarc.hoamanagerserver.shared.model.UserRoleName;
import com.laarc.hoamanagerserver.shared.repository.UserRepository;
import com.laarc.hoamanagerserver.shared.repository.UserRoleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements BaseCrudService<User, Long> {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User createUser(@Valid CreateUser createUser) {

        if (existByEmail(createUser.getEmail())) {
            throw new ConflictException("Email already exists.");
        }

        String hashedPassword = passwordEncoder.encode(createUser.getPassword());

        return userRepository.save(User.builder()
                        .email(createUser.getEmail())
                        .password(hashedPassword)
                        .userRole(getUserRoleByName(createUser.getUserRoleName()))
                .build());
    }

    public UserRole getUserRoleByName(UserRoleName name) {
        return userRoleRepository.findByRoleName(name)
                .orElseThrow(UserRoleNotFound::new);
    }

    public UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .userRoleName(user.getUserRole().getRoleName())
                .build();
    }

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

}
