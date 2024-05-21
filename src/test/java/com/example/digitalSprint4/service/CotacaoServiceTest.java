package com.example.digitalSprint4.service;

import com.example.digitalSprint4.model.Cotacao;
import com.example.digitalSprint4.repository.CotacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CotacaoServiceTest {

    @InjectMocks
    private CotacaoService cotacaoService;

    @Mock
    private CotacaoRepository cotacaoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodasCotacoes() {
        List<Cotacao> cotacoes = Arrays.asList(
                new Cotacao("Cotacao 1", 100.0, LocalDateTime.now()),
                new Cotacao("Cotacao 2", 200.0, LocalDateTime.now())
        );

        when(cotacaoRepository.findAll()).thenReturn(cotacoes);

        List<Cotacao> result = cotacaoService.listarTodas();
        assertEquals(2, result.size());
        verify(cotacaoRepository, times(1)).findAll();
    }

    @Test
    void adicionarCotacao() {
        Cotacao cotacao = new Cotacao("Cotacao 1", 100.0, LocalDateTime.now());
        when(cotacaoRepository.save(any(Cotacao.class))).thenReturn(cotacao);

        Cotacao result = cotacaoService.adicionarCotacao(cotacao);
        assertEquals(cotacao, result);
        verify(cotacaoRepository, times(1)).save(cotacao);
    }
}
