package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

public class CamadaBase {

    List<NeuronioPerceptron> neuroniosSensores = new ArrayList<>();
    List<NeuronioPerceptron> neuroniosProcessadores = new ArrayList<>();
    List<NeuronioPerceptron> neuroniosSaida = new ArrayList<>();
    int qtdNeuronios;
    int qtdPesos;

    public Double funcaoAtivacao(Double somatorio){
        return 1/(1 + (Math.exp(-somatorio)));
    }

    //por que indice??
    public Double somatorio ( Integer indice, Double bias, List<NeuronioPerceptron> neuronioPerceptrons){
        Double somatorio = 0.0;
        for (NeuronioPerceptron neuronio : neuronioPerceptrons){
            //System.out.println("Dado: "+neuronio.getDado()+ " Peso: "+neuronio.getPeso(indice));
            somatorio = (neuronio.getDado() * neuronio.getPeso(indice)) + somatorio;
            //System.out.println("Somatorio: "+ somatorio);
        }
        somatorio = bias + somatorio;
        //System.out.println("Somatorio do NP:  "+ indice + " valor: "+somatorio);
        return somatorio;
    }



    public int getQtdNeuronios() {
        return qtdNeuronios;
    }

    public void setQtdNeuronios(int qtdNeuronios) {
        this.qtdNeuronios = qtdNeuronios;
    }

    public int getQtdPesos() {
        return qtdPesos;
    }

    public void setQtdPesos(int qtdPesos) {
        this.qtdPesos = qtdPesos;
    }

    public List<NeuronioPerceptron> getNeuroniosSensores() {
        return neuroniosSensores;
    }

    public void setNeuroniosSensores(List<NeuronioPerceptron> neuroniosSensores) {
        this.neuroniosSensores = neuroniosSensores;
    }

    public List<NeuronioPerceptron> getNeuroniosProcessadores() {
        return neuroniosProcessadores;
    }

    public void setNeuroniosProcessadores(List<NeuronioPerceptron> neuroniosProcessadores) {
        this.neuroniosProcessadores = neuroniosProcessadores;
    }

    public List<NeuronioPerceptron> getNeuroniosSaida() {
        return neuroniosSaida;
    }

    public void setNeuroniosSaida(List<NeuronioPerceptron> neuroniosSaida) {
        this.neuroniosSaida = neuroniosSaida;
    }
}
