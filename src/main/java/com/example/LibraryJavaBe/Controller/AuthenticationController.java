package com.example.LibraryJavaBe.Controller;

import com.example.LibraryJavaBe.Request.AuthenticaionRequest;
import com.example.LibraryJavaBe.Request.CategoryRequest.AddCateRq;
import com.example.LibraryJavaBe.Response.AuthenticationResponse;
import com.example.LibraryJavaBe.Response.RegisterRequest;
import com.example.LibraryJavaBe.authService.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private  final AuthenticationService service;
    @GetMapping("/current-user")
    public ResponseEntity<AddCateRq> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var res  = new AddCateRq();
        res.setName(authentication.getName());
        return ResponseEntity.ok(res);


    }
    @GetMapping("/current-userId")
    public Integer getCurrentUserID() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       return service.getUserId(authentication.getName());



    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request

    ){
        return  ResponseEntity.ok(service.register(request));

    }
    @PostMapping("/registerAdmin")

    public ResponseEntity<AuthenticationResponse> AdminResgister(
            @RequestBody RegisterRequest request

    ){
        return  ResponseEntity.ok(service.registerAdmin(request));

    }
    @PostMapping("/authenticate")

    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticaionRequest request

    ){
         return  ResponseEntity.ok(service.authenticate(request));


    }

    @PostMapping("/refeshToken")

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response

    ) throws IOException {

        service.refeshToken(request, response);


    }
    @GetMapping("/test")
    public ResponseEntity<String> sayHello(){
        return  ResponseEntity.ok("Hello");
    }
}
