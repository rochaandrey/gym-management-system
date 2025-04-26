package com.manager.workout.workout.models.enumns;

import lombok.Getter;

@Getter
public enum Atividade {
    INATIVO(0),
    ATIVO(1);

    private final int atividade;

    Atividade(int atividade) {
        this.atividade = atividade;
    }

}
