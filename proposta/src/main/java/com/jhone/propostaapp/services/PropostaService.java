package com.jhone.propostaapp.services;

import com.jhone.propostaapp.dtos.PropostaRequestDto;
import com.jhone.propostaapp.dtos.PropostaResponseDto;
import com.jhone.propostaapp.entities.Proposta;
import com.jhone.propostaapp.mapper.PropostaMapper;
import com.jhone.propostaapp.repositories.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropostaService {

    private final PropostaRepository repository;

    public List<PropostaResponseDto> obterProposta(){
        return PropostaMapper.INSTANCE.convertListEntityToDto(repository.findAll());
    }

    public PropostaResponseDto criar (PropostaRequestDto request){
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(request);
        repository.save(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }
}
