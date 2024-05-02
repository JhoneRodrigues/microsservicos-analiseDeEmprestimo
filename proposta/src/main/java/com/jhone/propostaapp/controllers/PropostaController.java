package com.jhone.propostaapp.controllers;

import com.jhone.propostaapp.dtos.PropostaRequestDto;
import com.jhone.propostaapp.dtos.PropostaResponseDto;
import com.jhone.propostaapp.services.PropostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/propostas")
@RequiredArgsConstructor
public class PropostaController {

    private final PropostaService service;

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto request, UriComponentsBuilder uriComponentsBuilder) {
        PropostaResponseDto response = service.criar(request);

        var uri = uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
