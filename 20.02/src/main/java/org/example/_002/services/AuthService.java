package org.example._002.services;

import org.example._002.dto.LoginDTO;
import org.example._002.entities.Users;
import org.example._002.exceptions.UnauthorizedException;
import org.example._002.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsersService us;
    private final JWTTools jwtTools;
    private final PasswordEncoder bcrypt;

    @Autowired
    public AuthService(UsersService us, JWTTools jwtTools, PasswordEncoder bcrypt) {

        this.us = us;
        this.jwtTools = jwtTools;
        this.bcrypt = bcrypt;
    }

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        Users f = this.us.findByEmail(body.email());
        if (bcrypt.matches(body.password(), f.getPassword())) {
            String Token = jwtTools.generateToken(f);
            return Token;
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }


    }

}

