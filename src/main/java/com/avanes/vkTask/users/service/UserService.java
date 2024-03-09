package com.avanes.vkTask.users.service;

import com.avanes.vkTask.users.entity.UserEntity;
import com.avanes.vkTask.users.entity.UserEntityDetails;
import com.avanes.vkTask.users.storage.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(mail);

        return user.map(UserEntityDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(mail + " пользователь с заданной почтой не найден."));
    }
}
