package com.mycompany.hotelreservationsystem.models;

import java.io.Serializable;

public class Hospede implements Serializable {
    private String nome;
    private String documento;
    private String contato;
    private String endereco;

    public Hospede(String nome, String documento, String contato, String endereco) {
        this.nome = nome;
        this.documento = documento;
        this.contato = contato;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getContato() {
        return contato;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return nome;
    }
}