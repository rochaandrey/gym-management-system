package com.manager.workout.workout.dto.academia;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResponseAcademiaDto(
        @NotBlank String nome,
        @Email String email,
        @NotBlank String telefone
) { }
