package com.manager.workout.workout.models;

import com.manager.workout.workout.models.enumns.Atividade;
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
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String nome;
    private String email;
    private String senha;
    private Atividade atividade;

    @ManyToOne
    @JoinColumn(name = "academia_id")
    private Academia academia;

    public Aluno(String nome, String email, String senha, Academia academia) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.academia = academia;
    }
}
