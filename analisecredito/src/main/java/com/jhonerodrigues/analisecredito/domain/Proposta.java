package com.jhonerodrigeus.notification.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Proposta {

    private Long id;
    private Double valorSolicitado;
    private int prazoPagamento;
    private Boolean aprovada;
    private Boolean integrada;
    private String observacao;

    private Usuario usuario;
}
