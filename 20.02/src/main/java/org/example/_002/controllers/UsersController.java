package org.example._002.controllers;

import org.example._002.dto.RoleDTO;
import org.example._002.dto.UsersDTO;
import org.example._002.entities.Users;
import org.example._002.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService us;

    @Autowired
    public UsersController(UsersService us) {
        this.us = us;
    }

    @GetMapping
    public Page<Users> findAll(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "20") int size,
                               @RequestParam(defaultValue = "username") String orderBy,
                               @RequestParam(defaultValue = "asc") String sortCriteria) {
        return this.us.findAll(page, size, orderBy, sortCriteria);
    }// http://localhost:3001/users

    @GetMapping("/{id}")
    public Users FindById(@PathVariable String id) {
        return this.us.findById(id);
    }// http://localhost:3001/users/{id}

    @PutMapping("/me")
    public Users updateProfile(@AuthenticationPrincipal Users currentAuthenticatedUser, @RequestBody UsersDTO payload) {
        return this.us.findByIdAndUpadte(currentAuthenticatedUser.getId(), payload);
    }// http://localhost:3001/users/me

    @PatchMapping("/me/role")
    public Users setRoleById(@AuthenticationPrincipal Users currentAuthenticatedUser, @RequestBody RoleDTO role) {
        return this.us.setRoleById(currentAuthenticatedUser.getId(), role);
    }// http://localhost:3001/users/me/role
    
    @DeleteMapping("/me")
    public void deleteProfile(@AuthenticationPrincipal Users currentAuthenticatedUser) {
        this.us.deleteById(currentAuthenticatedUser.getId());
    }// http://localhost:3001/users/me


}
