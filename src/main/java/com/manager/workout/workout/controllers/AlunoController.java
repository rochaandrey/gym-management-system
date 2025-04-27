package com.manager.workout.workout.controllers;

import com.manager.workout.workout.dto.aluno.RequestAlunoDto;
import com.manager.workout.workout.dto.aluno.ResponseAlunoDto;
import com.manager.workout.workout.repositories.AlunoRepository;
import com.manager.workout.workout.service.AlunoService;
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

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<ResponseAlunoDto> createClient(@RequestBody RequestAlunoDto requestAlunoDto, UriComponentsBuilder uriBuilder){
        ResponseAlunoDto responseAlunoDto = alunoService.create(requestAlunoDto);
        var uri = uriBuilder
                .path("/api/aluno/{id}")
                .buildAndExpand(responseAlunoDto.uuid())
                .toUri();
        return ResponseEntity.created(uri).body(responseAlunoDto);
    }

    @GetMapping
    public ResponseEntity<List<ResponseAlunoDto>> getAllClients(){
        return ResponseEntity.ok(alunoService.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ResponseAlunoDto> getClientById(@PathVariable(value = "uuid") UUID uuid){
        return alunoService.getById(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/busca")
    public ResponseEntity<List<ResponseAlunoDto>> getClientByNameOrEmail(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email){
        List<ResponseAlunoDto> responseAlunoDtoList = alunoService.getByParams(nome, email);

        if(responseAlunoDtoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(responseAlunoDtoList);
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
    public ResponseEntity<ResponseAlunoDto> update(@PathVariable (value = "id") UUID id, @RequestBody @Valid RequestAlunoDto requestAlunoDto){
        return alunoRepository.findById(id)
                .map(aluno-> {
                    BeanUtils.copyProperties(requestAlunoDto,aluno);
                    ResponseAlunoDto responseAlunoDto = requestAlunoDto.toResponseAlunoDto(aluno);
                alunoRepository.save(aluno);
                return ResponseEntity.ok(responseAlunoDto);
        }).orElse(ResponseEntity.notFound().build());
    }
}
