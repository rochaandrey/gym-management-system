package com.manager.workout.workout.models;

import com.manager.workout.workout.models.enumns.GrupoMuscular;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String nome;
    private String descricao;
    private GrupoMuscular grupoMuscular;

    @OneToMany
    private List<ExercicioTreino> exercicioTreinoList;
}
