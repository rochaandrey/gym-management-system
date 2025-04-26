package com.manager.workout.workout.repositories;

import com.manager.workout.workout.models.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, UUID> {
}
