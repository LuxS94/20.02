package org.example._002.services;

import org.example._002.dto.RoleDTO;
import org.example._002.dto.UsersDTO;
import org.example._002.entities.Users;
import org.example._002.exceptions.AlreadyExsists;
import org.example._002.exceptions.NotFoundException;
import org.example._002.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UserRepo ur;

    @Autowired
    public UsersService(UserRepo ur, PasswordEncoder passwordEncoder) {
        this.ur = ur;

    }

    public Page<Users> findAll(int page, int size, String orderBy, String sortCriteria) {
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.ur.findAll(pageable);
    }

    public Users findById(String id) {
        return this.ur.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato"));
    }

    public Users findByEmail(String email) {
        return this.ur.findByEmail(email).orElseThrow(() -> new NotFoundException("La mail non è registrata!"));
    }

    public Users save(UsersDTO payload) {
        Users nUser = new Users(payload.username(), payload.email(), payload.password(), payload.role());
        this.ur.findByEmail(payload.email()).ifPresent(u -> {
            throw new AlreadyExsists("La mail è già registrata");
        });
        return this.ur.save(nUser);
    }

    public Users findByUsername(String username) {
        return this.ur.findByUsername(username).orElseThrow(() -> new NotFoundException("L'username non è registrato"));
    }

    public Users findByIdAndUpadte(String id, UsersDTO payload) {
        Users f = this.findById(id);
        f.setEmail(payload.email());
        f.setUsername(payload.username());
        f.setPassword(payload.password());
        f.setRole(payload.role());
        this.ur.findByEmail(payload.email()).ifPresent(u -> {
            throw new AlreadyExsists("La mail è già registrata");
        });
        this.ur.findByUsername(payload.username()).ifPresent(u -> {
            throw new AlreadyExsists("L'username esiste già !");
        });

        return this.ur.save(f);
    }

    public void deleteById(String id) {
        Users f = this.findById(id);
        this.ur.delete(f);

    }

    public Users setRoleById(String id, RoleDTO role) {
        Users f = this.findById(id);
        f.setRole(role.role());
        return this.ur.save(f);
    }


}

