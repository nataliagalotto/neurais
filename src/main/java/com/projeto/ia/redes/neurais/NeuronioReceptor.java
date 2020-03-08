package com.projeto.ia.redes.neurais;

import java.util.List;

public class NeuronioReceptor {
    List<String> dados;
    List<Double> pesos;
    List<String> targets;

    public NeuronioReceptor() {
        this.pesos = pesos;
    }

    public List<Double> getPesos() {
        return pesos;
    }

    public void setPesos(List<Double> pesos) {
        this.pesos = pesos;
    }

    public double getRandomDouble(){
        double min = -1;
        double max =  1;
        return ( Math.random() * ( (max-min) + 1 ) ) + min;
    }


}
