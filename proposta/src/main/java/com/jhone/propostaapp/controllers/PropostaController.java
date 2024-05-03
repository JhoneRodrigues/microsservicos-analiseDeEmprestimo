package com.jhone.propostaapp.controllers;

import com.jhone.propostaapp.dtos.PropostaRequestDto;
import com.jhone.propostaapp.dtos.PropostaResponseDto;
import com.jhone.propostaapp.services.PropostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/proposta")
@RequiredArgsConstructor
public class PropostaController {

    private final PropostaService service;

    @GetMapping
    public ResponseEntity<List<PropostaResponseDto>> obterPropostas (){
        return ResponseEntity.ok().body(service.obterProposta());
    }

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto request, UriComponentsBuilder uriComponentsBuilder) {
        PropostaResponseDto response = service.criar(request);

        var uri = uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
