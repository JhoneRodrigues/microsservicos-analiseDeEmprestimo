package com.jhone.propostaapp.mapper;

import com.jhone.propostaapp.dtos.PropostaRequestDto;
import com.jhone.propostaapp.dtos.PropostaResponseDto;
import com.jhone.propostaapp.entities.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.text.NumberFormat;
import java.util.List;

@Mapper
public interface PropostaMapper {

    PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class);

    @Mapping(target = "usuario.nome", source = "nome")
    @Mapping(target = "usuario.sobrenome", source = "sobrenome")
    @Mapping(target = "usuario.cpf", source = "cpf")
    @Mapping(target = "usuario.telefone", source = "telefone")
    @Mapping(target = "usuario.renda", source = "renda")
    @Mapping(target = "usuario.id", ignore = true)
    @Mapping(target = "aprovada", ignore = true)
    @Mapping(target = "integrada", ignore = true)
    @Mapping(target = "observacao", ignore = true)
    Proposta convertDtoToProposta (PropostaRequestDto dto);

    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "sobrenome", source = "usuario.sobrenome")
    @Mapping(target = "cpf", source = "usuario.cpf")
    @Mapping(target = "telefone", source = "usuario.telefone")
    @Mapping(target = "renda", source = "usuario.renda")
    @Mapping(target = "valorSolicitadoFmt", expression = "java(setValorSolicitadoFmt(entity))")
    PropostaResponseDto convertEntityToDto(Proposta entity);

    List<PropostaResponseDto> convertListEntityToDto(Iterable<Proposta> propostas);

    default String setValorSolicitadoFmt(Proposta proposta) {
        if (proposta != null && proposta.getValorSolicitado() != null) {
            return NumberFormat.getCurrencyInstance().format(proposta.getValorSolicitado());
        } else {
            return "";
        }
    }
}
