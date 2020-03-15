package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

public class NeuronioPerceptron {

    List<Double> pesos = new ArrayList<>(19);
    Double saida;
    Double somatorio;

    public NeuronioPerceptron() {

    }

    public List<Double> getPesos() {
        this.pesos = gerarPesos();
        return pesos;
    }

    public void setPesos(List<Double> pesos) {
        this.pesos = pesos;
    }

    public Double getSaida() {
        return saida;
    }

    public void setSaida(Double saida) {
        this.saida = saida;
    }

    public Double getSomatorio() {
        return somatorio;
    }

    public void setSomatorio(Double somatorio) {
        this.somatorio = somatorio;
    }

    public List<Double> gerarPesos(){
        for (int i = 0; i < 20 ; i++) {
            pesos.add(Utils.getRandomDouble());
        }
        return pesos;
    }

}
