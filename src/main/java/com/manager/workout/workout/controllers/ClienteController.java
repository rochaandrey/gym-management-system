package com.manager.workout.workout.controllers;

import com.manager.workout.workout.dto.cliente.RequestClienteDto;
import com.manager.workout.workout.dto.cliente.ResponseClienteDto;
import com.manager.workout.workout.repositories.ClienteRepository;
import com.manager.workout.workout.service.ClienteService;
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
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ResponseClienteDto> createClient(@RequestBody RequestClienteDto requestClienteDto, UriComponentsBuilder uriComponentsBuilder){
        ResponseClienteDto responseClienteDto = clienteService.createClient(requestClienteDto);
        var uri = uriComponentsBuilder
                .path("/api/aluno/{id}")
                .buildAndExpand(responseClienteDto.uuid())
                .toUri();
        return ResponseEntity.created(uri).body(responseClienteDto);
    }

    @GetMapping
    public ResponseEntity<List<ResponseClienteDto>> getAllClients(){
        return ResponseEntity.ok(clienteService.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ResponseClienteDto> getClientById(@PathVariable(value = "uuid") UUID uuid){
        return clienteService.getClientById(uuid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/busca")
    public ResponseEntity<List<ResponseClienteDto>> getClientByNameOrEmail(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email){
        List<ResponseClienteDto> responseClienteDtoList = clienteService.getClientByParams(nome,email);

        if(responseClienteDtoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(responseClienteDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable (value = "id") UUID id){
        clienteService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseClienteDto> update(@PathVariable (value = "id") UUID id, @RequestBody @Valid RequestClienteDto requestClienteDto){
        ResponseClienteDto responseClienteDto = clienteService.updateClient(id, requestClienteDto);
        return ResponseEntity.ok(responseClienteDto);
    }
}
