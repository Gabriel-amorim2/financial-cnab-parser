package com.githubGabriel.financial_parser.controller;

import com.githubGabriel.financial_parser.model.Transacao;
import com.githubGabriel.financial_parser.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping("/arquivos")
    public ResponseEntity<String> carregarArquivos(@RequestParam("file")MultipartFile file){
        service.processar(file);
        return ResponseEntity.ok("arquivo carregado com sucesso");
    }
        @GetMapping("/todas")
    public ResponseEntity<List<Transacao>> pegarTransacoes(){
        return ResponseEntity.ok(service.transacoes());
    }

}
