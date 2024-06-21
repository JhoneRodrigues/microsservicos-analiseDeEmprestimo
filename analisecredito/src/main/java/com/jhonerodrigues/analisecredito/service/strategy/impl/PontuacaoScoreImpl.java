package com.jhonerodrigues.analisecredito.service.strategy.impl;

import com.jhonerodrigues.analisecredito.domain.Proposta;
import com.jhonerodrigues.analisecredito.service.strategy.CalculoPonto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(2)
@Component
public class PontuacaoScoreImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        int score = score();
        if (score <= 200){
            throw new RuntimeException("Score abaixo do permitido");
        } else if (score <= 400){
            return 150;
        } else if (score <= 600){
            return 180;
        }
        return 220;
    }

    private int score(){
        return new Random().nextInt(0,1000);
    }
}
