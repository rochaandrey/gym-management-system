package com.manager.workout.workout.models.enumns;

import lombok.Getter;

@Getter
public enum CodigoPais {
    BRASIL("BR"),
    ESTADOS_UNIDOS("US"),
    RUSSIA("RU"),
    PORTUGAL("PT"),
    REINO_UNIDO("GB");

    private final String codigo;

    CodigoPais(String codigo) {
        this.codigo = codigo;
    }
}
