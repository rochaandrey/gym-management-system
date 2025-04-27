package com.manager.workout.workout.dto.academia;

import jakarta.validation.constraints.NotBlank;

public record RequestAcademiaSenhaDto(@NotBlank String senha) {
}
