package com.jhone.propostaapp.services;

import com.jhone.propostaapp.dtos.PropostaRequestDto;
import com.jhone.propostaapp.dtos.PropostaResponseDto;
import com.jhone.propostaapp.entities.Proposta;
import com.jhone.propostaapp.mapper.PropostaMapper;
import com.jhone.propostaapp.repositories.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private PropostaRepository repository;
    private NotificacaoService notificacaoService;
    private String exchange;

    public PropostaService( @Value("${rabbitmq.propostapendente.exchange}") String exchange,
                            NotificacaoService notificacaoService, PropostaRepository repository) {
        this.exchange = exchange;
        this.notificacaoService = notificacaoService;
        this.repository = repository;
    }

    public List<PropostaResponseDto> obterProposta(){
        return PropostaMapper.INSTANCE.convertListEntityToDto(repository.findAll());
    }

    public PropostaResponseDto criar (PropostaRequestDto request){
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(request);
        repository.save(proposta);

        var response = PropostaMapper.INSTANCE.convertEntityToDto(proposta);

        notificacaoService.notificar(response,exchange);

        return response;
    }
}
