package com.example.digitalSprint4.service;

import com.example.digitalSprint4.model.Cotacao;
import com.example.digitalSprint4.repository.CotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CotacaoService {

    @Autowired
    private CotacaoRepository cotacaoRepository;

    public List<Cotacao> listarTodas() {
        return cotacaoRepository.findAll();
    }

    public Cotacao adicionarCotacao(Cotacao cotacao) {
        return cotacaoRepository.save(cotacao);
    }
}