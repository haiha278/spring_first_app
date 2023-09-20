package com.example.spring_first_app.service;

import com.example.spring_first_app.common.Messages;
import com.example.spring_first_app.dto.UserDTO;
import com.example.spring_first_app.entity.User;
import com.example.spring_first_app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImp implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByName(username);
            if (user != null) {
                UserDetails userDetails = new ModelMapper().map(user, UserDetails.class);
                return userDetails;
            }
        } catch (Exception e) {
            log.error(Messages.HANDLED_EXCEPTION);
        }
        return null;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("role");
        userRepository.save(user);
        return userDTO;
    }
}
