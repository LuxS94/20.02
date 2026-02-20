package org.example._002.repositories;

import org.example._002.entities.Prenotazioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioniRepo extends JpaRepository<Prenotazioni, String> {
}
