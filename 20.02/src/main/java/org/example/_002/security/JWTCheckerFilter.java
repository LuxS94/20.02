package org.example._002.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example._002.entities.Users;
import org.example._002.exceptions.UnauthorizedException;
import org.example._002.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JWTCheckerFilter extends OncePerRequestFilter {
    private final JWTTools jwtTools;
    private final UsersService us;

    @Autowired
    public JWTCheckerFilter(JWTTools jwtTools, UsersService us) {
        this.jwtTools = jwtTools;
        this.us = us;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        String userHeader = request.getHeader("Authorization");
        if (userHeader == null || !userHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire il token nel formato corretto");
        String Token = userHeader.replace("Bearer ", "");
        jwtTools.verifyToken(Token);


        String userid = jwtTools.extractIdFromToken(Token);
        Users authenticatedUser = this.us.findById(userid);
        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}