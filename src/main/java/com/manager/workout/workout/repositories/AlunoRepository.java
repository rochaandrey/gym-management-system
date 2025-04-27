package com.manager.workout.workout.repositories;

import com.manager.workout.workout.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByEmail(String email);

    List<Cliente> findByNomeContainingIgnoreCaseAndEmail(String nome, String email);

    List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
