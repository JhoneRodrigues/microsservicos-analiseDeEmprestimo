package com.jhone.propostaapp.repositories;

import com.jhone.propostaapp.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository <Proposta, Long> {
}
