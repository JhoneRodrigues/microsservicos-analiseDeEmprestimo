package com.jhonerodrigues.analisecredito.service;

import com.jhonerodrigues.analisecredito.domain.Proposta;
import com.jhonerodrigues.analisecredito.service.strategy.CalculoPonto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnaliseCreditoService {

    private final List <CalculoPonto> calculoPontoList;

    public void analisar(Proposta proposta){
        int pontuacao = calculoPontoList.stream()
                .mapToInt(impl -> impl.calcular(proposta)).sum();
    }
}
