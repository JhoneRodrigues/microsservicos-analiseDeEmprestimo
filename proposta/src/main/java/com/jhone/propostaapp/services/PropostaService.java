package com.jhone.propostaapp.services;

import com.jhone.propostaapp.dtos.PropostaRequestDto;
import com.jhone.propostaapp.dtos.PropostaResponseDto;
import com.jhone.propostaapp.entities.Proposta;
import com.jhone.propostaapp.mapper.PropostaMapper;
import com.jhone.propostaapp.repositories.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private PropostaRepository propostaRepository;
    private NotificacaoService notificacaoService;
    private String exchange;

    public PropostaService( @Value("${rabbitmq.propostapendente.exchange}") String exchange,
                            NotificacaoService notificacaoService, PropostaRepository repository) {
        this.exchange = exchange;
        this.notificacaoService = notificacaoService;
        this.propostaRepository = repository;
    }

    public List<PropostaResponseDto> obterProposta(){
        return PropostaMapper.INSTANCE.convertListEntityToDto(propostaRepository.findAll());
    }

    public PropostaResponseDto criar (PropostaRequestDto request){
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(request);
        propostaRepository.save(proposta);

        notificarRabbitMq(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    private void notificarRabbitMq(Proposta proposta){
        try {
            notificacaoService.notificar(proposta,exchange);
        } catch (RuntimeException ex){
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }

    }
}
