package com.example.spring_first_app.service;

import com.example.spring_first_app.common.Messages;
import com.example.spring_first_app.dto.LoginDTO;
import com.example.spring_first_app.dto.UserDTO;
import com.example.spring_first_app.entity.User;
import com.example.spring_first_app.repository.UserRepository;
import com.example.spring_first_app.security.CustomerDetails;
import com.example.spring_first_app.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImp implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<User> user = userRepository.findByName(username);
            if (user != null) {
                CustomerDetails customerDetails = new ModelMapper().map(user.get(), CustomerDetails.class);
                return customerDetails;
            }
        } catch (Exception e) {
            log.error(Messages.HANDLED_EXCEPTION);
        }
        return null;
    }

    public UserDetails loadUserByID(Integer id) {
        Optional<User> user = userRepository.findById(id);
        CustomerDetails customerDetails = new CustomerDetails(user.get());
        return customerDetails;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("role");
        userRepository.save(user);
        return userDTO;
    }

    public LoginDTO login(UserDTO userDTO) {
        Optional<User> user = userRepository.findByName(userDTO.getUsername());
        if (user.isPresent()) {
            boolean checkPassword = passwordEncoder.matches(userDTO.getPassword(), user.get().getPassword());
            if (checkPassword) {
                return generateToken(user);
            }
        }
        return null;
    }

    public LoginDTO refresh(String refreshToken) {
        var check = jwtTokenProvider.validateRefreshToken(refreshToken);
        if (check) {
            var userId = jwtTokenProvider.getUserIdFromJWTRefresh(refreshToken);
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                return generateToken(user);
            }
        }
        return null;
    }

    private LoginDTO generateToken(Optional<User> user) {
        var token = jwtTokenProvider.generateToken(new CustomerDetails(user.get()));
        var refreshTokenGenerated = jwtTokenProvider.generateRefreshToken(new CustomerDetails(user.get()));
        return LoginDTO.builder()
                .token(token)
                .refreshToken(refreshTokenGenerated)
                .build();
    }


}
