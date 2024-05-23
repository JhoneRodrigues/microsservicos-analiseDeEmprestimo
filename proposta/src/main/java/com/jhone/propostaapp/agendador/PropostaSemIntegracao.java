package com.jhone.propostaapp.agendador;

import com.jhone.propostaapp.entities.Proposta;
import com.jhone.propostaapp.repositories.PropostaRepository;
import com.jhone.propostaapp.services.NotificacaoRabbitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class PropostaSemIntegracao {

    private PropostaRepository propostaRepository;
    private NotificacaoRabbitService notificacaoRabbitService;
    private String exchange;

    public PropostaSemIntegracao(PropostaRepository propostaRepository,
                                 NotificacaoRabbitService notificacaoRabbitService,
                                 @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 10, timeUnit = SECONDS)
    public void buscarPropostasSemIntegracao(){
        propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {
            try {
                notificacaoRabbitService.notificar(proposta,exchange);
                atualizarProposta(proposta);
            } catch (RuntimeException e){
                System.out.println((e.getMessage()));
            }
        });
    }

    private void atualizarProposta(Proposta proposta){
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);
    }
}
