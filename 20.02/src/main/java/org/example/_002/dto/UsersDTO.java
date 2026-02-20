package org.example._002.dto;

import jakarta.validation.constraints.*;
import org.example._002.entities.Role;

public record UsersDTO(
        @NotBlank(message = "Username obbligatorio!") @Size(min = 2, max = 30, message = " l'username deve essere tra i 2 e i 30 caratteri") String username,
        @Email(message = "Email inserita non valida!")
        String email, @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$", message = "La password deve contenere almeno una maiuscola,un numero,un carattere speciale,e deve essere lunga almeno 8 caratteri!") String password,
        @NotNull(message = "Impostare un ruolo è obbligatorio!")
        Role role) {
}
