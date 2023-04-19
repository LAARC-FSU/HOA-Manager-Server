package com.laarc.hoamanagerserver.api.module.user.service;

import com.laarc.hoamanagerserver.api.crud.BaseCrudService;
import com.laarc.hoamanagerserver.api.dto.user.CreateUser;
import com.laarc.hoamanagerserver.exception.UserNotFoundException;
import com.laarc.hoamanagerserver.shared.model.User;
import com.laarc.hoamanagerserver.shared.repository.UserRepository;
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
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User createUser(@Valid CreateUser createUser) {
        User user = mapper.map(createUser, User.class);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
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
