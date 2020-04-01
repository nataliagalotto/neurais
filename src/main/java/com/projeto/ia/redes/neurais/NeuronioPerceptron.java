package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;
import com.projeto.ia.redes.neurais.Utils;

public class NeuronioPerceptron {

    List<Double> pesos = new ArrayList<>();
    // A variável dado representa a saída da camada
    // ou seja, o resultado após a aplicaçao da funçao
    // de ativação
    Double dado;
    Double somatorio;
    Double bias;

    public NeuronioPerceptron() {
        this.bias = 1.0;
    }

    public List<Double> getPesos() {
        return pesos;
    }

    public Double getPeso(int index){
        return pesos.get(index);
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

    public void setDado(Double dado) {
        this.dado = dado;
    }

    public Double getSomatorio() {
        return somatorio;
    }

    public void setSomatorio(Double somatorio) {
        this.somatorio = somatorio;
    }

    public Double getBias() {
        return bias;
    }

    public void setBias(Double bias) {
        this.bias = bias;
    }

    public List<Double> gerarPesos(int qtd){
        Utils utils = new Utils();
        for (int i = 0; i < qtd ; i++) {
            pesos.add(utils.getRandomDouble());
        }
        return pesos;
    }

    public List<Double> gerarPesosTeste(int qtdInicial, int qtdFinal , List<Double> pesosEntrada){
        for (int j= qtdInicial; j < qtdFinal; j++) {
            pesos.add(pesosEntrada.get(j));
        }
        return pesos;
    }

}
