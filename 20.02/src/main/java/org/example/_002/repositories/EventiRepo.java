package org.example._002.repositories;

import org.example._002.entities.Eventi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EventiRepo extends JpaRepository<Eventi, String> {

    Optional<Eventi> findByDataAndLocation(LocalDate data, String location);
}
