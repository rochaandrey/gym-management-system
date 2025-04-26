package com.manager.workout.workout.dto;

import com.manager.workout.workout.models.Academia;
import com.manager.workout.workout.models.enumns.Atividade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ResponseAlunoDto(
        @NotBlank UUID uuid,
        @NotBlank String nome,
        @Email @NotBlank String email,
        @NotBlank Academia academia,
        @NotBlank Atividade atividade) {
}
