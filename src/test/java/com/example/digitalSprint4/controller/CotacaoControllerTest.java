package com.example.digitalSprint4.controller;

import com.example.digitalSprint4.model.Cotacao;
import com.example.digitalSprint4.service.CotacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CotacaoController.class)
class CotacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CotacaoService cotacaoService;

    @Test
    void listarTodasCotacoes() throws Exception {
        when(cotacaoService.listarTodas()).thenReturn(Arrays.asList(
                new Cotacao("Cotacao 1", 100.0, LocalDateTime.now()),
                new Cotacao("Cotacao 2", 200.0, LocalDateTime.now())
        ));

        mockMvc.perform(get("/cotacoes"))
                .andExpect(status().isOk())
                .andExpect(view().name("cotacoes"))
                .andExpect(model().attributeExists("cotacoes"))
                .andExpect(model().attribute("cotacoes", hasSize(2)));
    }

    @Test
    void adicionarCotacao() throws Exception {
        Cotacao cotacao = new Cotacao();
        cotacao.setDescricao("Cotacao 1");
        cotacao.setValor(100.0);
        cotacao.setDataCotacao(LocalDateTime.now());

        when(cotacaoService.adicionarCotacao(any(Cotacao.class))).thenReturn(cotacao);

        mockMvc.perform(post("/cotacoes")
                        .param("descricao", "Cotacao 1")
                        .param("valor", "100.0")
                        .param("dataCotacao", "2024-05-20T10:00"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cotacoes"));
    }
}
