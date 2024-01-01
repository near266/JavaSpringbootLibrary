package com.example.LibraryJavaBe.authService;

import com.example.LibraryJavaBe.Request.ChangePasswordRequest;
import com.example.LibraryJavaBe.UserService.Entites.User;
import com.example.LibraryJavaBe.UserService.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {
private  final PasswordEncoder passwordEncoder;
private  final UserRepository repository;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User)((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        //check current pass is correct
      if(!  passwordEncoder.matches(request.getCurrentPassword(),user.getPassword())){
          throw  new IllegalStateException("Wrong password");

      }
      if(!request.getNewPassword().equals(request.getConfirmationPassword())){

            throw  new IllegalStateException("Password is not same");
        }
      user.setPassword(passwordEncoder.encode(request.getNewPassword()));
      repository.save(user);
    }
}
