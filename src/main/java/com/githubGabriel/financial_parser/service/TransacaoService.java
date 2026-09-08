package com.githubGabriel.financial_parser.service;

import com.githubGabriel.financial_parser.model.TipoTransacao;
import com.githubGabriel.financial_parser.model.Transacao;
import com.githubGabriel.financial_parser.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.Format;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository repository;

    public TransacaoService(TransacaoRepository repository) {
        this.repository = repository;
    }

    public void processar(MultipartFile file){
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        String linha;
        while ((linha = bufferedReader.readLine()) != null){
            Transacao transacao = parseLinha(linha);
            repository.save(transacao);
            }
        }
        catch (IOException exception){
        exception.getMessage();
    }
    }
    private Transacao parseLinha (String linha){
        Transacao transacao = new Transacao();
        Integer getTipoTrasacao = Integer.parseInt(linha.substring(0,1));
        TipoTransacao tipoTransacao = TipoTransacao.fromCodigo(getTipoTrasacao);
        transacao.setTipo(tipoTransacao.getDescricao());
        transacao.setNatureza(tipoTransacao.getNatureza());
        transacao.setSinal(tipoTransacao.getSinal());

        transacao.setData(LocalDate.parse(linha.substring(1,9), DateTimeFormatter.BASIC_ISO_DATE));
        transacao.setValor(new BigDecimal(linha.substring(9,19)).divide(new BigDecimal(100)));
        transacao.setCpf(linha.substring(19,30));
        transacao.setCartao(linha.substring(30,42));
        transacao.setHora(LocalTime.parse(linha.substring(42,48), DateTimeFormatter.ofPattern("HHmmss")));
        transacao.setDonoLoja(linha.substring(48,62).trim());
        transacao.setNomeLoja(linha.substring(62,81));

        return transacao;

    }
    public List<Transacao> transacoes (){
        return repository.findAll();
    }
}
