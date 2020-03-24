package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

public class CamadaSensor {
    //dado = xzinho
    //peso = v

    List<Double> dadosEntrada = new ArrayList<>();
    List<NeuronioPerceptron> neuroniosSensores = new ArrayList<>();
    int qtdPesos = 20;

    public List<NeuronioPerceptron> gerarListaNeuronios(){
        NeuronioFactory neuronioFactory = new NeuronioFactory();
        for(Double dado : dadosEntrada){
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.setDado(dado);
            neuronioPerceptron.gerarPesos(qtdPesos);
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
