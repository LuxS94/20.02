package dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record EventiDTO(@NotBlank String location, @NotBlank LocalDate data, @NotBlank boolean disponibile) {
}
