package com.laarc.hoamanagerserver.api.module.user.service;

import com.laarc.hoamanagerserver.api.crud.BaseCrudService;
import com.laarc.hoamanagerserver.api.dto.user.PostUser;
import com.laarc.hoamanagerserver.api.dto.user.UserResponse;
import com.laarc.hoamanagerserver.api.module.user.repository.UserRepository;
import com.laarc.hoamanagerserver.api.module.user.repository.UserRoleRepository;
import com.laarc.hoamanagerserver.exception.UserNotFoundException;
import com.laarc.hoamanagerserver.exception.UserRoleNotFound;
import com.laarc.hoamanagerserver.exception.http.ConflictException;
import com.laarc.hoamanagerserver.shared.model.User;
import com.laarc.hoamanagerserver.shared.model.UserRole;
import com.laarc.hoamanagerserver.shared.model.UserRoleName;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements BaseCrudService<User, Long> {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User createUser(@Valid PostUser postUser) {

        if (existByEmail(postUser.getEmail())) {
            throw new ConflictException("Email already exists.");
        }

        String hashedPassword = passwordEncoder.encode(postUser.getPassword());

        return userRepository.save(User.builder()
                        .email(postUser.getEmail())
                        .password(hashedPassword)
                        .userRole(getUserRoleByName(postUser.getUserRoleName()))
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
