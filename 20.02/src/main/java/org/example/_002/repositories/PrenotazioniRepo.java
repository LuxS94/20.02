package org.example._002.repositories;

import org.example._002.entities.Prenotazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrenotazioniRepo extends JpaRepository<Prenotazioni, String> {
    Optional<Prenotazioni> findByEventoAndPrenotante(String idE, String idP);
}
