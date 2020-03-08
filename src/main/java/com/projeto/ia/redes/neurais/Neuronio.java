package com.projeto.ia.redes.neurais;

import java.util.List;

public class Neuronio {

    Double peso;
    Double dado;

    public Neuronio(Double peso, Double dado) {
        this.peso = peso;
        this.dado = dado;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getDado() {
        return dado;
    }

    public void setDado(Double dado) {
        this.dado = dado;
    }
}
