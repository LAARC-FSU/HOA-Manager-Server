package com.laarc.hoamanagerserver.api.module.user.service;

import com.laarc.hoamanagerserver.exception.UserNotFoundException;
import com.laarc.hoamanagerserver.shared.model.User;
import com.laarc.hoamanagerserver.shared.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

}
