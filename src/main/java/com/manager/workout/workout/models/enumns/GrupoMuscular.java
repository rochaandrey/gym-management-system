package com.manager.workout.workout.models.enumns;

import lombok.Getter;

@Getter
public enum GrupoMuscular {
    PEITO("Peito"),
    COSTAS("Costas"),
    PERNAS("Pernas"),
    OMBROS("Ombros"),
    BICEPS("Bíceps"),
    TRICEPS("Tríceps"),
    ABDOMEN("Abdômen"),
    GLUTEOS("Glúteos"),
    ANTEBRACO("Antebraço"),
    PANTURRILHA("Panturrilha");

    private final String label;

    GrupoMuscular(String label) {
        this.label = label;
    }
}
