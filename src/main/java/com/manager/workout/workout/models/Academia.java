package com.manager.workout.workout.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
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
    private String senha; //salvar a senha em formato BCryptPasswordEncoder ao inves de str

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    private String telefone;

    @OneToMany(mappedBy = "academia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cliente> clienteList;
}
