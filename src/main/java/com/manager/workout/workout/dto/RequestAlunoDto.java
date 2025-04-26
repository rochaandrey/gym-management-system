package com.manager.workout.workout.dto;

import com.manager.workout.workout.models.Academia;
import com.manager.workout.workout.models.Aluno;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestAlunoDto(
        String nome,
        @Email String email,
        String senha,
        Academia academia) {
    public Aluno toEntity(){
        return new Aluno(this.nome(), this.email(), this.senha(), this.academia());
    }

    public ResponseAlunoDto toResponseAlunoDto(Aluno aluno){
        return new ResponseAlunoDto(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getAcademia(), aluno.getAtividade());
    }
}
