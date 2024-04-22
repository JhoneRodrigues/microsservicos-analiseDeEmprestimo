package com.jhonerodrigues.proposta.dtos;

public record PropostaRequestDto(
        String nome,
        String sobrenome,
        String telefone,
        String cpf,
        Double renda,
        Double valorSolicitado,
        int prazoPagamento
) {
}
