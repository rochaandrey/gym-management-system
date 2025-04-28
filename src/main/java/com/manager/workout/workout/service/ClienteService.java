package com.manager.workout.workout.service;

import com.manager.workout.workout.dto.cliente.RequestClienteDto;
import com.manager.workout.workout.dto.cliente.ResponseClienteDto;
import com.manager.workout.workout.models.Cliente;
import com.manager.workout.workout.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    private ResponseClienteDto convertToResponseDto(Cliente cliente) {
        return new ResponseClienteDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getAcademia(),
                cliente.getStatus()
        );
    }

    @Transactional
    public ResponseClienteDto createClient(RequestClienteDto requestClienteDto){
        Cliente cliente = requestClienteDto.toEntity();
        clienteRepository.save(cliente);
        return convertToResponseDto(cliente);
    }

    public List<ResponseClienteDto> getAll(){
        return clienteRepository.findAll()
                .stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    public Optional<ResponseClienteDto> getClientById(UUID uuid){
        return clienteRepository.findById(uuid)
                .map(this::convertToResponseDto);
    }

    public List<ResponseClienteDto> getClientByParams(String nome, String email){
        if(nome != null && email != null){
            return clienteRepository.findByNomeContainingIgnoreCaseAndEmail(nome, email)
                    .stream()
                    .map(this::convertToResponseDto)
                    .toList();
        } else if (nome != null) {
            return clienteRepository.findByNomeContainingIgnoreCase(nome)
                    .stream()
                    .map(this::convertToResponseDto)
                    .toList();
        } else if (email != null) {
            return clienteRepository.findByEmail(email)
                    .stream()
                    .map(this::convertToResponseDto)
                    .toList();
        }
        throw new IllegalArgumentException("invalid params");
    }

    @Transactional
    public void deleteClient(UUID id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"nao foi possivel excluir o cliente"));

        clienteRepository.delete(cliente);
    }

    @Transactional
    public ResponseClienteDto updateClient(UUID id, RequestClienteDto requestClienteDto){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"cliente nao foi encontrado"));

        if(requestClienteDto.nome() != null ){
            cliente.setNome(requestClienteDto.nome());
        }
        if (requestClienteDto.email() != null) {
            cliente.setEmail(requestClienteDto.email());
        }
        if (requestClienteDto.academia() != null){
            cliente.setAcademia(requestClienteDto.academia());
        }
        clienteRepository.save(cliente);
        return convertToResponseDto(cliente);
    }
}
