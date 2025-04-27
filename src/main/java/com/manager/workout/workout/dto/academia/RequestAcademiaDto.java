package com.manager.workout.workout.dto.academia;

import com.manager.workout.workout.models.Academia;
import com.manager.workout.workout.models.Endereco;
import jakarta.validation.constraints.Email;

public record RequestAcademiaDto(
        String nome,
        @Email String email,
        String senha,
        Endereco endereco,
        String telefone) {
    public Academia toEntity(){
        return Academia.builder()
                .nome(this.nome)
                .email(this.email)
                .senha(this.senha)
                .endereco(this.endereco)
                .telefone(this.telefone)
                .build();
    }
}
