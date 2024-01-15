package com.example.LibraryJavaBe.UserService.Repository;

import com.example.LibraryJavaBe.UserService.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByPhonenumber(String phonenumber);
}
