package com.manager.workout.workout.service;

import com.manager.workout.workout.dto.academia.RequestAcademiaDto;
import com.manager.workout.workout.dto.academia.RequestAcademiaSenhaDto;
import com.manager.workout.workout.dto.academia.ResponseAcademiaDto;
import com.manager.workout.workout.models.Academia;
import com.manager.workout.workout.repositories.AcademiaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class AcademiaService {
    @Autowired
    private AcademiaRepository academiaRepository;

    private ResponseAcademiaDto convertToResponseDto(Academia academia){
        return new ResponseAcademiaDto(
                academia.getNome(),
                academia.getEmail(),
                academia.getTelefone()
        );
    }

    @Transactional
    public ResponseAcademiaDto createGym(RequestAcademiaDto requestAcademiaDto){
        Academia academia = requestAcademiaDto.toEntity();
        academiaRepository.save(academia);
        return convertToResponseDto(academia);
    }

    public Optional<ResponseAcademiaDto> getGymById(UUID uuid){
        return academiaRepository.findById(uuid)
                .map(this::convertToResponseDto);
    }

    @Transactional
    public void deleteGym(UUID id){
        Academia academia = academiaRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"nao foi possivel excluir o cliente"));

        academiaRepository.delete(academia);
    }

    @Transactional
    public ResponseAcademiaDto updateGym(UUID id, RequestAcademiaDto requestAcademiaDto){
        Academia academia = academiaRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"cliente nao foi encontrado"));

        if(requestAcademiaDto.nome() != null ){
            academia.setNome(requestAcademiaDto.nome());
        }
        if (requestAcademiaDto.email() != null) {
            academia.setEmail(requestAcademiaDto.email());
        }
        if (requestAcademiaDto.telefone() != null){
            academia.setTelefone(requestAcademiaDto.telefone());
        }
        academiaRepository.save(academia);
        return convertToResponseDto(academia);
    }

    @Transactional
    public ResponseAcademiaDto updateSenha(UUID id, RequestAcademiaSenhaDto requestAcademiaSenhaDto){
        Academia academia = academiaRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"cliente nao foi encontrado"));

        if(requestAcademiaSenhaDto != null){
            academia.setSenha(requestAcademiaSenhaDto.senha());
        }
        academiaRepository.save(academia);
        return convertToResponseDto(academia);
    }

}
