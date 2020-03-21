package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

public class CamadaSensor {

    List<Double> dadosEntrada = new ArrayList<>();
    List<NeuronioPerceptron> neuroniosSensores = new ArrayList<>();

    public List<NeuronioPerceptron> gerarListaNeuronios(){
        NeuronioFactory neuronioFactory = new NeuronioFactory();
        for(Double dado : dadosEntrada){
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.setSaida(dado);
            neuronioPerceptron.getPesos();
            neuroniosSensores.add(neuronioPerceptron);
        }
        return neuroniosSensores;
    }

    public void setDadosEntrada(List<Double> dadosEntrada) {
        this.dadosEntrada = dadosEntrada;
    }

    public List<NeuronioPerceptron> getNeuroniosSensores() {
        return neuroniosSensores;
    }
}
