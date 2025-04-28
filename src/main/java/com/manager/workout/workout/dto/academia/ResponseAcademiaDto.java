package com.manager.workout.workout.dto.academia;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ResponseAcademiaDto(
        @NotBlank UUID uuid,
        @NotBlank String nome,
        @Email String email,
        @NotBlank String telefone
) { }
