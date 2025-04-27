package com.manager.workout.workout.service;

import com.manager.workout.workout.dto.aluno.RequestAlunoDto;
import com.manager.workout.workout.dto.aluno.ResponseAlunoDto;
import com.manager.workout.workout.models.Aluno;
import com.manager.workout.workout.repositories.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    private ResponseAlunoDto convertToDto(Aluno aluno) {
        return new ResponseAlunoDto(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getAcademia(),
                aluno.getAtividade()
        );
    }

    @Transactional
    public ResponseAlunoDto create(RequestAlunoDto requestAlunoDto){
        Aluno aluno = requestAlunoDto.toEntity();
        alunoRepository.save(aluno);
        return requestAlunoDto.toResponseAlunoDto(aluno);
    }

    public List<ResponseAlunoDto> getAll(){
        return alunoRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public Optional<ResponseAlunoDto> getById(UUID uuid){
        return alunoRepository.findById(uuid)
                .map(this::convertToDto);
    }

    public List<ResponseAlunoDto> getByParams(String nome, String email){
        if(nome != null && email != null){
            return alunoRepository.findByNomeContainingIgnoreCaseAndEmail(nome, email)
                    .stream()
                    .map(this::convertToDto)
                    .toList();
        } else if (nome != null) {
            return alunoRepository.findByNomeContainingIgnoreCase(nome)
                    .stream()
                    .map(this::convertToDto)
                    .toList();
        } else if (email != null) {
            return alunoRepository.findByEmail(email)
                    .stream()
                    .map(this::convertToDto)
                    .toList();
        }
        throw new IllegalArgumentException("invalid params");
    }
}
