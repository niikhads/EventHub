//package com.example.eventhub.service.impl;
//
//import com.example.eventhub.dto.request.LoginRequest;
//import com.example.eventhub.dto.request.RefreshTokenRequest;
//import com.example.eventhub.dto.request.RegisterRequest;
//import com.example.eventhub.dto.response.LoginResponse;
//import com.example.eventhub.dto.response.UserDto;
//import com.example.eventhub.entity.User;
//import com.example.eventhub.enums.Roles;
//import com.example.eventhub.exception.UserAlreadyException;
//import com.example.eventhub.exception.UserNotFoundException;
//import com.example.eventhub.jwt.JwtService;
//import com.example.eventhub.mapper.UserMapper;
//import com.example.eventhub.repository.UserRepository;
//import com.example.eventhub.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//
//import java.util.ArrayList;
//import java.util.List;
//@Slf4j
//@Service
//@RequiredArgsConstructor
//
//public class UserImplService implements UserService {
//
//    private final UserRepository userRepository;
//    private final UserMapper userMapper;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//
//    @Override
//    public UserDto register (RegisterRequest registerRequest) {
//        userRepository.findByEmail(registerRequest.getEmail())
//                .ifPresent( user ->{
//                    throw  new UserAlreadyException("Username already exists");
//                });
//        User user = userMapper.toUser(registerRequest);
//        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
//        User savedUser = userRepository.save(user);
//
//        return userMapper.toUserDto(savedUser);
//    }
//
//    @Override
//    public UserDto getUserById(Long id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        authentication.getAuthorities()
//                .forEach(grantedAuthority -> {
//                    log.info("Roles {}", grantedAuthority.getAuthority());
//
//                });
//
//        return userRepository.findById(id)
//                .map(userMapper::toUserDto)
//                .orElseThrow(() -> new UserNotFoundException("User is not found" + id));
//    }
//
//    @Override
//    public LoginResponse login(LoginRequest loginRequest) {
//
//        User user = userRepository.findByEmail(loginRequest.getEmail())
//                .orElseThrow(() -> new UserNotFoundException("User is not found" ));
//        List<Roles>  roles = new ArrayList<>();
//
//        if (user.getId() == 8){
//            roles.add(Roles.USER);
//            roles.add(Roles.ADMIN);
//        }
//
//        String currentPassword = user.getPassword();
//        String appliedPassword = loginRequest.getPassword();
//        if (!passwordEncoder.matches(appliedPassword,currentPassword)) {
//            throw  new RuntimeException("Wrong password");
//        }
//
//        UserDto userDto = userMapper.toUserDto(user);
//
//        String accessToken = jwtService.(userDto, roles);
//        String refreshToken = jwtService.refreshToken(userDto);
//
//        return   LoginResponse.builder()
//               // .refreshToken(refreshToken)
//               // .accessToken(accessToken)
//                .build();
//
//
//
//
//    }
//
//    @Override
//    public LoginResponse refresh(RefreshTokenRequest request) {
//        Long id = jwtService.validateRefreshToken(request.getRefreshToken());
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("User is not found" ));
//
//        UserDto userDto = userMapper.toUserDto(user);
//        String accessToken = jwtService.generateToken(userDto, List.of());
//        String refreshToken = jwtService.refreshToken(userDto);
//
//        return   LoginResponse.builder()
//                .refreshToken(refreshToken)
//                .accessToken(accessToken)
//                .build();
//
//    }
//}
//
