package com.manager.workout.workout.controllers;

import com.manager.workout.workout.dto.academia.RequestAcademiaDto;
import com.manager.workout.workout.dto.academia.RequestAcademiaSenhaDto;
import com.manager.workout.workout.dto.academia.ResponseAcademiaDto;
import com.manager.workout.workout.service.AcademiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/academia")
public class AcademiaController {
    @Autowired
    private AcademiaService academiaService;

    @PostMapping
    public ResponseEntity<ResponseAcademiaDto> create(@RequestBody RequestAcademiaDto requestAcademiaDto, UriComponentsBuilder uriComponentsBuilder){
        ResponseAcademiaDto responseAcademiaDto = academiaService.createGym(requestAcademiaDto);
        var uri = uriComponentsBuilder
                .path("/api/academia/{id}")
                .buildAndExpand(responseAcademiaDto.uuid())
                .toUri();
        return ResponseEntity.created(uri).body(responseAcademiaDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseAcademiaDto> getGym(@PathVariable(value = "id") UUID id){
        return academiaService.getGymById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseAcademiaDto> deleteGym(@PathVariable(value = "id") UUID id){
        academiaService.deleteGym(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<ResponseAcademiaDto> updatePartial(@PathVariable(value = "uuid") UUID uuid, RequestAcademiaDto requestAcademiaDto){
        ResponseAcademiaDto responseAcademiaDto = academiaService.updateGym(uuid, requestAcademiaDto);
        return ResponseEntity.ok(responseAcademiaDto);
    }

    @PatchMapping("/update/{uuid}")
    public ResponseEntity<ResponseAcademiaDto> updateSenha(@PathVariable(value = "uuid") UUID uuid, RequestAcademiaSenhaDto requestAcademiaSenhaDto){
        ResponseAcademiaDto responseAcademiaDto = academiaService.updateSenha(uuid, requestAcademiaSenhaDto);
        return ResponseEntity.ok(responseAcademiaDto);
    }
}
