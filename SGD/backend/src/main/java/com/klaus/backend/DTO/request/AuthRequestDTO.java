package com.klaus.backend.DTO.request;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @NotBlank(message = "O nome é obrigatório") String username,
        @NotBlank(message = "A senha é obrigatória ") String password) {
}
