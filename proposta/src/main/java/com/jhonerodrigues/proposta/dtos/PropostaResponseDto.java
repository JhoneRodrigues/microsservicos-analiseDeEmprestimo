package com.jhonerodrigues.proposta.dtos;

public record PropostaResponseDto(
        Long id,
        String nome,
        String sobrenome,
        String telefone,
        String cpf,
        Double renda,
        Double valorSolicitado,
        int prazoPagamento,
        Boolean aprovado,
        String observacao
) {
}