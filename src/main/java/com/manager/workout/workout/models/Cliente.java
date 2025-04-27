package com.manager.workout.workout.models;

import com.manager.workout.workout.models.enumns.Atividade;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Atividade status;

    @ManyToOne
    @JoinColumn(name = "academia_id")
    private Academia academia;

    public Cliente(String nome, String email, Academia academia) {
        this.nome = nome;
        this.email = email;
        this.academia = academia;
    }
}
