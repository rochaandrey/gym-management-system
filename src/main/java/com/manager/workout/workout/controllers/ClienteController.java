package com.manager.workout.workout.controllers;

import com.manager.workout.workout.dto.cliente.RequestClienteDto;
import com.manager.workout.workout.dto.cliente.ResponseClienteDto;
import com.manager.workout.workout.repositories.AlunoRepository;
import com.manager.workout.workout.service.AlunoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<ResponseClienteDto> createClient(@RequestBody RequestClienteDto requestClienteDto, UriComponentsBuilder uriBuilder){
        ResponseClienteDto responseClienteDto = alunoService.createClient(requestClienteDto);
        var uri = uriBuilder
                .path("/api/aluno/{id}")
                .buildAndExpand(responseClienteDto.uuid())
                .toUri();
        return ResponseEntity.created(uri).body(responseClienteDto);
    }

    @GetMapping
    public ResponseEntity<List<ResponseClienteDto>> getAllClients(){
        return ResponseEntity.ok(alunoService.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ResponseClienteDto> getClientById(@PathVariable(value = "uuid") UUID uuid){
        return alunoService.getClientById(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/busca")
    public ResponseEntity<List<ResponseClienteDto>> getClientByNameOrEmail(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email){
        List<ResponseClienteDto> responseClienteDtoList = alunoService.getClientByParams(nome,email);

        if(responseClienteDtoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(responseClienteDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable (value = "id") UUID id){
        alunoService.deleteClient(id);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseClienteDto> update(@PathVariable (value = "id") UUID id, @RequestBody @Valid RequestClienteDto requestClienteDto){
        ResponseClienteDto responseClienteDto = alunoService.updateClient(id, requestClienteDto);
        return ResponseEntity.ok(responseClienteDto);
    }
}
