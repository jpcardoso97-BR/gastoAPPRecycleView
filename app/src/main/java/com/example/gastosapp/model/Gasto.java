package com.example.gastosapp.model;

import java.io.Serializable;

public class Gasto implements Serializable {
    private int id = 0;
    private double valor;
    private String data;
    private String descricao;
    private String categoria;
    private String formaPagamento;

    public Gasto(double valor , String data, String descricao,
                 String categoria, String formaPagamento) {
        this.id++;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.categoria = categoria;
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        return "R$ " +valor + " | " + data + " | " + descricao;
    }
}
