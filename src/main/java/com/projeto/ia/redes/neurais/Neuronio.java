package com.projeto.ia.redes.neurais;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class Neuronio {

    List<String> dados;
    Double bias;
    List<Double> pesos;
    List<String> targets;
    Double alfa;

    public Neuronio() {
        this.bias = getRandomDouble();
        this.pesos = pesos;
        this.alfa = 1.0;
    }

    public Double getAlfa() {
        return alfa;
    }

    public void setAlfa(Double alfa) {
        this.alfa = alfa;
    }

    public Double getBias() {
        return bias;
    }

    public void setBias(Double bias) {
        this.bias = bias;
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

    public void getLista(){
        Leitura leitura = new Leitura();
        List<String[]> lista = leitura.dados();
        for (String[] linhas : lista){
            for (int i = 0; i < linhas.length; i++) {
                if (i == 2){
                    targets.add(linhas[i]);
                }else{
                    dados.add(linhas[i]);
                }
            }
        }
    }
}
