package org.example._002.controllers;

import org.example._002.dto.EventiDTO;
import org.example._002.entities.Eventi;
import org.example._002.services.EventiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventi")
public class EventiController {
    private final EventiService es;

    @Autowired
    public EventiController(EventiService es) {
        this.es = es;
    }

    @GetMapping
    public Page<Eventi> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "20") int size,
                                @RequestParam(defaultValue = "username") String orderBy,
                                @RequestParam(defaultValue = "asc") String sortCriteria) {
        return this.es.findAll(page, size, orderBy, sortCriteria);
    }// http://localhost:3001/eventi

    @GetMapping("/{id}")
    public Eventi FindById(@PathVariable String id) {
        return this.es.findById(id);
    }// http://localhost:3001/eventi/{id}

    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Eventi save(@RequestBody @Validated EventiDTO payload) {
        return this.es.save(payload);
    }// http://localhost:3001/eventi

    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE')")
    @PutMapping("/{id}")
    public Eventi findByIdAndUpdate(@PathVariable String id, @RequestBody @Validated EventiDTO payload) {
        return this.es.findByIdAndUpadte(id, payload);
    }// http://localhost:3001/eventi/{id}

    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) {
        this.es.deleteById(id);
    }// http://localhost:3001/eventi/{id}
}
