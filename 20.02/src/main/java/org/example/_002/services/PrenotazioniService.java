package org.example._002.services;

import org.example._002.dto.PrenotazioniDTO;
import org.example._002.entities.Prenotazioni;
import org.example._002.exceptions.AlreadyExsists;
import org.example._002.exceptions.NotFoundException;
import org.example._002.repositories.PrenotazioniRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioniService {
    private final PrenotazioniRepo pr;

    @Autowired
    public PrenotazioniService(PrenotazioniRepo pr) {
        this.pr = pr;
    }

    public Page<Prenotazioni> findAll(int page, int size, String orderBy, String sortCriteria) {
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.pr.findAll(pageable);
    }

    public Prenotazioni findById(String id) {
        return this.pr.findById(id).orElseThrow(() -> new NotFoundException("Prenotazione non trovata"));
    }

    public Prenotazioni save(PrenotazioniDTO payload) {
        Prenotazioni nEvent = new Prenotazioni(payload.prenotante(), payload.evento());
        this.pr.findByEventoAndPrenotante(payload.evento().getId(), payload.prenotante().getId()).ifPresent(u -> {
            throw new AlreadyExsists("La prenotazione esiste già!");
        });
        return this.pr.save(nEvent);
    }

    public Prenotazioni findByIdAndUpadte(String id, PrenotazioniDTO payload) {
        Prenotazioni f = this.findById(id);
        f.setEvento(payload.evento());
        f.setPrenotante(payload.prenotante());
        this.pr.findByEventoAndPrenotante(payload.evento().getId(), payload.prenotante().getId()).ifPresent(u -> {
            throw new AlreadyExsists("La prenotazione esiste già!");
        });
        return this.pr.save(f);
    }

    public void deleteById(String id) {
        Prenotazioni f = this.findById(id);
        this.pr.delete(f);

    }
}
