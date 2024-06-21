package com.jhonerodrigues.analisecredito.service.strategy;

import com.jhonerodrigues.analisecredito.domain.Proposta;

public interface CalculoPonto {

    int calcular(Proposta proposta);
}
