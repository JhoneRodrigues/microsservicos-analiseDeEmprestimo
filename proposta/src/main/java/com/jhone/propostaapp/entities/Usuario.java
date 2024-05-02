package com.jhone.propostaapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private Double renda;

    @OneToOne(mappedBy = "usuario")
    private Proposta proposta;
}
