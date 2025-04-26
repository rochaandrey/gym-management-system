package com.manager.workout.workout.controllers;

import com.manager.workout.workout.dto.RequestAlunoDto;
import com.manager.workout.workout.dto.ResponseAlunoDto;
import com.manager.workout.workout.models.Aluno;
import com.manager.workout.workout.repositories.AlunoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseAlunoDto> create(@RequestBody RequestAlunoDto requestAlunoDto, UriComponentsBuilder uriBuilder){
        Aluno aluno = requestAlunoDto.toEntity();
        alunoRepository.save(aluno);
        var uri = uriBuilder
                .path("/api/aluno/{id}")
                .buildAndExpand(aluno.getId())
                .toUri();
        return ResponseEntity.created(uri).body(requestAlunoDto.toResponseAlunoDto(aluno));
    }

    @GetMapping
    public ResponseEntity<List<ResponseAlunoDto>> getAll(){
        List<ResponseAlunoDto> alunoList = alunoRepository.findAll()
                .stream()
                .map(aluno -> new ResponseAlunoDto(
                        aluno.getId(),
                        aluno.getNome(),
                        aluno.getEmail(),
                        aluno.getAcademia(),
                        aluno.getAtividade()
                ))
                .toList();
        return ResponseEntity.ok(alunoList);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ResponseAlunoDto> getByEmail(@PathVariable(value = "uuid") UUID uuid){
        return alunoRepository.findById(uuid)
                .map(aluno -> ResponseEntity.ok(
                        new ResponseAlunoDto(
                                aluno.getId(),
                                aluno.getNome(),
                                aluno.getEmail(),
                                aluno.getAcademia(),
                                aluno.getAtividade()
                        )
                ))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/busca")
    public ResponseEntity<ResponseAlunoDto> getByEmail(@PathVariable(value = "email") String email){
        return alunoRepository.findByEmail(email)
                .map(aluno -> ResponseEntity.ok(
                        new ResponseAlunoDto(
                                aluno.getId(),
                                aluno.getNome(),
                                aluno.getEmail(),
                                aluno.getAcademia(),
                                aluno.getAtividade()
                        )
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("busca/{email}")
    public ResponseEntity<ResponseAlunoDto> getByNome(@RequestParam(value = "nome") String nome){
        return alunoRepository.findByNome(nome)
                .map(aluno -> ResponseEntity.ok(
                        new ResponseAlunoDto(
                                aluno.getId(),
                                aluno.getNome(),
                                aluno.getEmail(),
                                aluno.getAcademia(),
                                aluno.getAtividade()
                        )
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable (value = "id") UUID id){
        return alunoRepository.findById(id)
                .map(aluno -> {
                    alunoRepository.delete(aluno);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseAlunoDto> update(@PathVariable (value = "id") UUID id, @RequestBody @Valid RequestAlunoDto requestAlunoDto){;
        return alunoRepository.findById(id)
                .map(aluno-> {
                    BeanUtils.copyProperties(requestAlunoDto,aluno);
                    ResponseAlunoDto responseAlunoDto = requestAlunoDto.toResponseAlunoDto(aluno);
                alunoRepository.save(aluno);
                return ResponseEntity.ok(responseAlunoDto);
        }).orElse(ResponseEntity.notFound().build());
    }
}
