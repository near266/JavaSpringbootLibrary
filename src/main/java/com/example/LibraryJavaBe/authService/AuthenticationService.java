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
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    public Integer getUserId(String phone){
        var user =repository.findByPhonenumber(phone)
                .orElseThrow();
        return user.getId();
    }
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .fullname(request.getFullname())
                .phonenumber(request.getPhonenumber())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)

                .build();
       var savedUser= repository.save(user);
        var jwtToken= jwtService.generateToken(user);
        var refreshToken=jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .role(user.getRole().name())
                .refreshToken(refreshToken)
                .build();
    }
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        var user = User.builder()
                .fullname(request.getFullname())
                .phonenumber(request.getPhonenumber())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        var savedUser= repository.save(user);
        var jwtToken= jwtService.generateToken(user);
        var refreshToken=jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .role(user.getRole().name())

                .refreshToken(refreshToken)
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
private void revokeAllsUserToken(User user){
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if(validUserTokens.isEmpty()){
            return;
        }
    validUserTokens.forEach(t->{
        t.setExpired(false);
        t.setRevoked((true));
    });
        tokenRepository.saveAll(validUserTokens);

}
    public AuthenticationResponse authenticate(AuthenticaionRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhonenumber(),
                        request.getPassword()
                )
        );
        var user =repository.findByPhonenumber(request.getPhonenumber())
                .orElseThrow();
        var jwtToken= jwtService.generateToken(user);
        var refreshToken=jwtService.generateRefreshToken(user)  ;
        revokeAllsUserToken(user);
        saveUserToken(user,jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role(user.getRole().name())
                .userId(user.getId())

                .build();

    }

    public void refeshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader =request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if(authHeader ==null || !authHeader.startsWith("Bear")){
            return;
        }
        refreshToken=authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if(userEmail !=null ){
            var user = this.repository.findByPhonenumber(userEmail).orElseThrow();

            if(jwtService.isTokenValid(refreshToken,user)){
               var accessToken=jwtService.generateToken(user);
                revokeAllsUserToken(user);
                saveUserToken(user,accessToken);
               var authRespose=AuthenticationResponse.builder()
                       .accessToken(accessToken)
                       .refreshToken(refreshToken)
                       .build();
               new ObjectMapper().writeValue(response.getOutputStream(),authRespose);
            }
        }

    }
}
