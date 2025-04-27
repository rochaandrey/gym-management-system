package com.manager.workout.workout.repositories;

import com.manager.workout.workout.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
    Optional<Aluno> findByEmail(String email);

    List<Aluno> findByNomeContainingIgnoreCaseAndEmail(String nome, String email);

    List<Aluno> findByNomeContainingIgnoreCase(String nome);
}
