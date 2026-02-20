package org.example._002.controllers;

import org.example._002.dto.LoginDTO;
import org.example._002.dto.LoginResDTO;
import org.example._002.dto.UsersDTO;
import org.example._002.entities.Users;
import org.example._002.exceptions.ValidationExceptions;
import org.example._002.services.AuthService;
import org.example._002.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService as;
    private final UsersService us;

    public AuthController(AuthService as, UsersService us) {
        this.as = as;
        this.us = us;
    }

    @PostMapping("/login")
    public LoginResDTO login(@RequestBody LoginDTO body) {

        return new LoginResDTO(this.as.checkCredentialsAndGenerateToken(body));
    }//http://localhost:3001/auth/login

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Users createUser(@RequestBody @Validated UsersDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationExceptions(errorsList);
        } else {
            return this.us.save(payload);
        }

    }//http://localhost:3001/auth/register
}

