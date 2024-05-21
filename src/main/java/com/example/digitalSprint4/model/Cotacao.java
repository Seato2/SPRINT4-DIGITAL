package com.example.digitalSprint4.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Cotacao {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String descricao;
   private Double valor;
   private LocalDateTime dataCotacao;

   public Cotacao() {
   }

   public Cotacao(String descricao, Double valor, LocalDateTime dataCotacao) {
      this.descricao = descricao;
      this.valor = valor;
      this.dataCotacao = dataCotacao;
   }


   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getDescricao() {
      return descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public Double getValor() {
      return valor;
   }

   public void setValor(Double valor) {
      this.valor = valor;
   }

   public LocalDateTime getDataCotacao() {
      return dataCotacao;
   }

   public void setDataCotacao(LocalDateTime dataCotacao) {
      this.dataCotacao = dataCotacao;
   }
}