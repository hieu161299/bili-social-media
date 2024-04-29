package com.bilibili.controller;

import com.bilibili.config.JwtProvider;
import com.bilibili.models.User;
import com.bilibili.repository.UserRepository;
import com.bilibili.request.LoginRequest;
import com.bilibili.response.AuthResponse;
import com.bilibili.service.CustomUserDetailService;
import com.bilibili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/sign-up")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        User isExist = userRepository.findByEmail(user.getEmail());
        if (isExist != null) {
            throw new Exception("this email already used with another account!");
        }
        User newUser = new User();

        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, savedUser);
        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token, "Register Success");

        return res;
    }
    @PostMapping("/sign-in")
    public AuthResponse signIn(@RequestBody LoginRequest loginRequest) {
        Authentication authentication =
                authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token, "Login Success");
        return res;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
        if(userDetails == null){
            throw new BadCredentialsException("invalid username");
        }
        if(!passwordEncoder.matches(password , userDetails.getPassword())){
            throw new BadCredentialsException("password not matched");
        }
        return  new UsernamePasswordAuthenticationToken(userDetails ,
                null ,
                userDetails.getAuthorities());
    }
}
