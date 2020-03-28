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

    public Double somatorio ( Integer indice, Double bias, List<NeuronioPerceptron> neuronioPerceptrons){
        bias = 1.0;
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

    public Double funcaoDerivada(Double x){
        Double y = 1/(1 + (Math.exp(-x)));
        return y - Math.pow(y,2);
    }
}
