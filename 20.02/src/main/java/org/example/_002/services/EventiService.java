package org.example._002.services;

import org.example._002.dto.EventiDTO;
import org.example._002.entities.Eventi;
import org.example._002.exceptions.AlreadyExsists;
import org.example._002.exceptions.NotFoundException;
import org.example._002.repositories.EventiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventiService {
    private final EventiRepo er;

    @Autowired
    public EventiService(EventiRepo er) {
        this.er = er;
    }

    public Page<Eventi> findAll(int page, int size, String orderBy, String sortCriteria) {
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.er.findAll(pageable);
    }

    public Eventi findById(String id) {
        return this.er.findById(id).orElseThrow(() -> new NotFoundException("Evento non trovato"));
    }

    public Eventi save(EventiDTO payload) {
        Eventi nEvent = new Eventi(payload.location(), payload.data(), payload.disponibile());
        this.er.findByDataAndLocation(payload.data(), payload.location()).ifPresent(u -> {
            throw new AlreadyExsists("L'evento è già stato creato!");
        });
        return this.er.save(nEvent);
    }

    public Eventi findByIdAndUpadte(String id, EventiDTO payload) {
        Eventi f = this.findById(id);
        f.setData(payload.data());
        f.setLocation(payload.location());
        f.setDisponibile(payload.disponibile());
        this.er.findByDataAndLocation(payload.data(), payload.location()).ifPresent(u -> {
            throw new AlreadyExsists("L'evento è già stato creato!");
        });
        return this.er.save(f);
    }

    public void deleteById(String id) {
        Eventi f = this.findById(id);
        this.er.delete(f);

    }
}
