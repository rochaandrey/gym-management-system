package com.manager.workout.workout.repositories;

import com.manager.workout.workout.models.Academia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AcademiaRepository extends JpaRepository<Academia, UUID> {
}
