package org.example._002.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EventiDTO(@NotBlank String location, @NotNull LocalDate data, @NotNull boolean disponibile) {
}
