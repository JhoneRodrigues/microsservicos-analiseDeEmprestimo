package com.jhone.propostaapp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchangePropostaPendente;

    @Value("${rabbitmq.propostaconcluida.exchange}")
    private String exchangePropostaConcluida;

    @Bean
    public Queue criarFilaPropostaPendenteMsAnaliseDeCredito(){
        return QueueBuilder.durable("proposta-pendente.ms-analise-credito").build();
    }

    @Bean
    public Queue criarFilaPropostaPendenteMsNotificacao(){
        return QueueBuilder.durable("proposta-pendente.ms-notificacao").build();
    }

    @Bean
    public Queue criarFilaPropostaConcluidaMsProposta(){
        return QueueBuilder.durable("proposta-concluida.ms-proposta").build();
    }

    @Bean
    public Queue criarFilaPropostaConcluidaMsNotificacao(){
        return QueueBuilder.durable("proposta-concluida.ms-notificacao").build();
    }

    @Bean
    public RabbitAdmin criarRabbitAdmin (ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializarAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public FanoutExchange criarFanoutExchangePropostaPendente(){
        return ExchangeBuilder.fanoutExchange(exchangePropostaPendente).build();
    }

    @Bean
    public FanoutExchange criarFanoutExchangePropostaConcluida(){
        return ExchangeBuilder.fanoutExchange(exchangePropostaConcluida).build();
    }

    @Bean
    public Binding criarBidingPropostaPendenteMsAnaliseDeCredito(){
        return BindingBuilder.bind(criarFilaPropostaPendenteMsAnaliseDeCredito())
                .to(criarFanoutExchangePropostaPendente());
    }

    @Bean
    public Binding criarBidingPropostaPendenteMsNotificacao(){
        return BindingBuilder.bind(criarFilaPropostaPendenteMsNotificacao())
                .to(criarFanoutExchangePropostaPendente());
    }

    @Bean
    public Binding criarBidingPropostaConcluidaMsPropostaApp(){
        return BindingBuilder.bind(criarFilaPropostaConcluidaMsProposta())
                .to(criarFanoutExchangePropostaConcluida());
    }

    @Bean
    public Binding criarBidingPropostaConcluidaMsNotificacao(){
        return BindingBuilder.bind(criarFilaPropostaConcluidaMsNotificacao())
                .to(criarFanoutExchangePropostaConcluida());
    }


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());

        return rabbitTemplate;
    }
}
