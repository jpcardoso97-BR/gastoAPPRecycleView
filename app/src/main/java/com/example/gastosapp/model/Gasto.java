package com.example.gastosapp.model;

import java.io.Serializable;

public class Gasto implements Serializable {
    private int id ;
    private double valor;
    private String data;
    private String descricao;
    private String categoria;
    private String formaPagamento;

    public Gasto(double valor , String data, String descricao,
                 String categoria, String formaPagamento) {

        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.categoria = categoria;
        this.formaPagamento = formaPagamento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getValorComoString() {
        return String.valueOf(valor);
    }

    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    @Override
    public String toString() {
        return "R$ " +valor + " | " + data + " | " + descricao;
    }

}
