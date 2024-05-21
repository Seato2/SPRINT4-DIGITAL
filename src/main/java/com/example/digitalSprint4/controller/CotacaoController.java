package com.example.digitalSprint4.controller;

import com.example.digitalSprint4.model.Cotacao;
import com.example.digitalSprint4.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/cotacoes")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @GetMapping
    public String listarTodas(Model model) {
        List<Cotacao> cotacoes = cotacaoService.listarTodas();
        model.addAttribute("cotacoes", cotacoes);
        return "cotacoes";
    }

    @GetMapping("/adicionar")
    public String adicionarCotacaoForm(Model model) {
        model.addAttribute("cotacao", new Cotacao());
        return "adicionarCotacao";
    }

    @PostMapping
    public String adicionarCotacao(@ModelAttribute Cotacao cotacao, Model model) {
        try {
            if (cotacao.getDescricao() == null || cotacao.getDescricao().isEmpty() ||
                    cotacao.getValor() == null ||
                    cotacao.getDataCotacao() == null) {
                model.addAttribute("message", "Todos os campos são obrigatórios.");
                return "adicionarCotacao";
            }

            cotacaoService.adicionarCotacao(cotacao);
            model.addAttribute("message", "Cotação adicionada com sucesso!");
        } catch (Exception e) {
            model.addAttribute("message", "Erro ao adicionar cotação: " + e.getMessage());
            return "adicionarCotacao";
        }

        List<Cotacao> cotacoes = cotacaoService.listarTodas();
        model.addAttribute("cotacoes", cotacoes);
        return "cotacoes";
    }
}