package org.example._002.repositories;

import org.example._002.entities.Eventi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventiRepo extends JpaRepository<Eventi, String> {
}
