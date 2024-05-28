package com.jhonerodrigeus.notification.services;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationSnsService {

    private final AmazonSNS amazonSNS;

    public void notificar(String telefone,String mensagem){
        PublishRequest publishRequest = new PublishRequest().withMessage(mensagem).withPhoneNumber(telefone);
        amazonSNS.publish(publishRequest);
    }
}
