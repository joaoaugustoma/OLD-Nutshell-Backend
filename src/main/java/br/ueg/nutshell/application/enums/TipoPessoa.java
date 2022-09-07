package br.ueg.nutshell.application.enums;

import java.util.Arrays;

public enum TipoPessoa {
    PESSOA_FISICA("F", "Pessoa Física"),
    PESSOA_JURIDICA("J", "Pessoa Jurídica");

    private final String id;

    private final String descricao;


    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    TipoPessoa(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    /**
     * Retorna a instância de {@link StatusAtivoInativo} conforme o 'id' informado.
     *
     * @param id -
     * @return -
     */
    public static TipoPessoa getById(final String id) {
        return Arrays.stream(values()).filter(value -> value.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * Retorna a instância de true ou false conforme o 'id' informado.
     *
     * @param idb -
     * @return -
     */
    public static TipoPessoa getByIdNome(final String idb) {
        String id = idb;
        return Arrays.stream(values()).filter(value -> value.getId().equals(id)).findFirst().orElse(null);
    }
}
