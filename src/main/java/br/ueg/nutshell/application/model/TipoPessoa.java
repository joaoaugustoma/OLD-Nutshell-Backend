package br.ueg.nutshell.application.model;

public enum TipoPessoa {
    PESSOA_FISICA("Pessoa Física"),
    PESSOA_JURIDICA("Pessoa Jurídica");

    private final String nome;

    TipoPessoa(String nome) {
        this.nome = nome;
    }
}
