package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

public class CamadaSaida {

    List<NeuronioPerceptron> neuroniosProcessadores = new ArrayList<>();
    List<NeuronioPerceptron> neuroniosSaida = new ArrayList<>();
    Double bias;

    public Double funcaoAtivacao(Double somatorio){
        return 1/(1 + (Math.exp(-somatorio)));
    }

    public Double somatorio ( Integer indice){
        bias = 1.0;
        Double somatorio = 0.0;
        for (NeuronioPerceptron neuronio : neuroniosProcessadores){
            System.out.println("Dado: "+neuronio.getSaida()+ " Peso: "+neuronio.getPesos().get(indice));
            somatorio = (neuronio.getSaida() * neuronio.getPesos().get(indice)) + somatorio;
            System.out.println("Somatorio: "+ somatorio);
        }
        somatorio = bias + somatorio;
        System.out.println("Somatorio do NP:  "+ indice + " valor: "+somatorio);
        return somatorio;
    }

    public List<NeuronioPerceptron> gerarListaNeuroniosSaida(){
        NeuronioFactory neuronioFactory = new NeuronioFactory();
        for (int i = 0; i < 7 ; i++) {
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.setSomatorio(somatorio(i));
            neuronioPerceptron.setSaida(funcaoAtivacao(somatorio(i)));
            neuronioPerceptron.getPesos();
            neuroniosSaida.add(neuronioPerceptron);
        }
        return neuroniosSaida;
    }

    public List<NeuronioPerceptron> getneuroniosProcessadores() {
        return neuroniosProcessadores;
    }

    public void setneuroniosProcessadores(List<NeuronioPerceptron> neuroniosSensores) {
        this.neuroniosProcessadores = neuroniosSensores;
    }

}
