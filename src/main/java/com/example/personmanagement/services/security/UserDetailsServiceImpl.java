package com.example.personmanagement.services.security;

import com.example.personmanagement.entity.UserEntity;
import com.example.personmanagement.repository.UserRepository;
import com.example.personmanagement.services.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MessageService messageService = new MessageService();
    private static final String USER_NOT_FOUND = "userNotFound";
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        log.info("Was calling loadUserByUsername. Input username: {}", username);
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format(
                        messageService.getMessage(USER_NOT_FOUND),
                        username)));
        return UserDetailsImpl.build(user);
    }
}
