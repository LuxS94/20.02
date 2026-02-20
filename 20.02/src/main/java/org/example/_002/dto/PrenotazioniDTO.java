package org.example._002.dto;

import jakarta.validation.constraints.NotBlank;
import org.example._002.entities.Eventi;
import org.example._002.entities.Users;

public record PrenotazioniDTO(@NotBlank Users prenotante, @NotBlank Eventi evento) {
}
