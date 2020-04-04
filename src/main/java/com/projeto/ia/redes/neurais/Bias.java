package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

public class Bias {

    List<Double> pesos = new ArrayList<>();
    Double dado;

    public Bias() {
        this.dado = 1.0;
    }

    public List<Double> getPesos() {
        return pesos;
    }

    public void setPeso(int index, Double peso){
        pesos.set(index, peso);
    }

    public void setPesos(List<Double> pesos) {
        this.pesos = pesos;
    }

    public Double getDado() {
        return dado;
    }

    public List<Double> gerarPesos(int qtd){
        Utils utils = new Utils();
        for (int i = 0; i < qtd ; i++) {
            pesos.add(utils.getRandomDouble());
        }
        return pesos;
    }

    public List<Double> gerarPesosTeste(List<Double> pesosEntrada){
        for (int j= 0; j < pesosEntrada.size(); j++) {
            pesos.add(pesosEntrada.get(j));
        }
        return pesos;
    }
}
