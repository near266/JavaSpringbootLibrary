package com.example.LibraryJavaBe.Controller;

import com.example.LibraryJavaBe.Request.AuthenticaionRequest;
import com.example.LibraryJavaBe.Response.AuthenticationResponse;
import com.example.LibraryJavaBe.Response.RegisterRequest;
import com.example.LibraryJavaBe.authService.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private  final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request

    ){
        return  ResponseEntity.ok(service.register(request));

    }
    @PostMapping("/authenticate")

    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticaionRequest request

    ){
         return  ResponseEntity.ok(service.authenticate(request));


    }
    @GetMapping("/test")
    public ResponseEntity<String> sayHello(){
        return  ResponseEntity.ok("Hello");
    }
}
