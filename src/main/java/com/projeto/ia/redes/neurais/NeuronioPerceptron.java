package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

public class NeuronioPerceptron implements  NeuronioPerceptronInterface {

    List<Double> pesos = new ArrayList<>(19);
    Double dado;
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

    public Double getDado() {
        return dado;
    }

    public void setDado(Double dado) {
        this.dado = dado;
    }

    public Double getSomatorio() {
        return somatorio;
    }

    public void setSomatorio(Double somatorio) {
        this.somatorio = somatorio;
    }

    public List<Double> gerarPesos(){
        for (int i = 0; i < 20 ; i++) {
            pesos.add(getRandomDouble());
        }
        return pesos;
    }

    public static Double getRandomDouble(){
        double min = -1;
        double max =  1;
        double numero = ( Math.random() * ( (max-min) + 1 ) ) + min;
        if (numero > max || numero < min){
            return 1.0;
        }
        return numero;
    }

}
