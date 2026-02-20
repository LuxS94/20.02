package org.example._002.controllers;

import org.example._002.dto.PrenotazioniDTO;
import org.example._002.entities.Prenotazioni;
import org.example._002.exceptions.ValidationExceptions;
import org.example._002.services.PrenotazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {
    private final PrenotazioniService ps;

    @Autowired
    public PrenotazioniController(PrenotazioniService ps) {
        this.ps = ps;
    }

    @GetMapping
    public Page<Prenotazioni> findAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "20") int size,
                                      @RequestParam(defaultValue = "username") String orderBy,
                                      @RequestParam(defaultValue = "asc") String sortCriteria) {
        return this.ps.findAll(page, size, orderBy, sortCriteria);
    }// http://localhost:3001/prenotazioni

    @GetMapping("/{id}")
    public Prenotazioni FindById(@PathVariable String id) {
        return this.ps.findById(id);
    }// http://localhost:3001/prenotazioni/{id}

    @PreAuthorize("hasAnyAuthority('NORMALE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazioni save(@RequestBody @Validated PrenotazioniDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationExceptions(errorsList);
        } else {
            return this.ps.save(payload);
        }
    }// http://localhost:3001/prenotazioni

    @PreAuthorize("hasAnyAuthority('NORMALE')")
    @PutMapping("/{id}")
    public Prenotazioni findByIdAndUpdate(@PathVariable String id, @RequestBody @Validated PrenotazioniDTO payload) {
        return this.ps.findByIdAndUpadte(id, payload);
    }// http://localhost:3001/prenotazioni/{id}

    @PreAuthorize("hasAnyAuthority('NORMALE')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) {
        this.ps.deleteById(id);
    }// http://localhost:3001/prenotazioni/{id}
}
