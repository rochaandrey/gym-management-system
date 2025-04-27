package com.manager.workout.workout.dto.cliente;

import com.manager.workout.workout.models.Academia;
import com.manager.workout.workout.models.Cliente;
import jakarta.validation.constraints.Email;

public record RequestClienteDto(
        String nome,
        @Email String email,
        String senha,
        Academia academia){
    public Cliente toEntity(){
        return new Cliente(this.nome(), this.email(), this.senha(), this.academia());
    }
}
