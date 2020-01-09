package com.renanleon.domain.tripulante;

public abstract class Tripulante {

    private String nome;
    private Boolean podeDirigir;

    public Tripulante(String nome, Boolean podeDirigir) {
        this.nome = nome;
        this.podeDirigir = podeDirigir;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getPodeDirigir() {
        return podeDirigir;
    }

    @Override
    public String toString() {
        return nome + " (" + this.getClass().getSimpleName() + ")";
    }
}
