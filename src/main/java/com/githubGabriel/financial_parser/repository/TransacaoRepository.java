package com.githubGabriel.financial_parser.repository;

import com.githubGabriel.financial_parser.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao,Long> {

}
