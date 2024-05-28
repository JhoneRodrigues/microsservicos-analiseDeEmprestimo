package com.jhonerodrigeus.notification.listener;

import com.jhonerodrigeus.notification.consts.MensagemConstante;
import com.jhonerodrigeus.notification.domain.Proposta;
import com.jhonerodrigeus.notification.services.NotificationSnsService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PropostaPendenteListener {

    private final NotificationSnsService notificationSnsService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta){
        String mensagem = String.format(MensagemConstante.PROPOSTA_EM_ANALISE, proposta.getUsuario().getNome());
        notificationSnsService.notificar(proposta.getUsuario().getTelefone(),mensagem);
    }
}
