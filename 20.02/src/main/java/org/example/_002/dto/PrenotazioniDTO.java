package org.example._002.dto;

import jakarta.validation.constraints.NotNull;
import org.example._002.entities.Eventi;
import org.example._002.entities.Users;

public record PrenotazioniDTO(@NotNull Users prenotante, @NotNull Eventi evento) {
}
