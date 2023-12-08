package com.example.LibraryJavaBe.authService;

import com.example.LibraryJavaBe.Configuration.JwtService;
import com.example.LibraryJavaBe.Request.AuthenticaionRequest;
import com.example.LibraryJavaBe.Response.AuthenticationResponse;
import com.example.LibraryJavaBe.Response.RegisterRequest;
import com.example.LibraryJavaBe.UserService.Entites.Role;
import com.example.LibraryJavaBe.UserService.Entites.User;
import com.example.LibraryJavaBe.UserService.Repository.TokenRepository;
import com.example.LibraryJavaBe.UserService.Repository.UserRepository;
import com.example.LibraryJavaBe.UserService.token.Token;
import com.example.LibraryJavaBe.UserService.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFistname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
       var savedUser= repository.save(user);
        var jwtToken= jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User User, String jwtToken) {
        var token = Token.builder()
                .user(User)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    public AuthenticationResponse authenticate(AuthenticaionRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user =repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken= jwtService.generateToken(user);
        saveUserToken(user,jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
}
