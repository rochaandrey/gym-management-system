package com.manager.workout.workout.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Academia {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 80, nullable = false)
    private String senha;
    private String endereco;
    private String telefone;

    @OneToMany(mappedBy = "academia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cliente> clienteList;
}
