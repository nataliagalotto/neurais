package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

public class CamadaSensor {

    List<Double> dadosEntrada = new ArrayList<>();
    List<NeuronioPerceptron> neuroniosSensores = new ArrayList<>();

    public List<NeuronioPerceptron> gerarListaNeuronios(){
        NeuronioFactory neuronioFactory = new NeuronioFactory();
        pegarDadosEntrada();
        for(Double dado : dadosEntrada){
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.setSaida(dado);
            neuronioPerceptron.getPesos();
            neuroniosSensores.add(neuronioPerceptron);
        }
        return neuroniosSensores;
    }

    public void pegarDadosEntrada(){
        Leitura leitura = new Leitura("dados/caracteres-limpo.csv");
        this.dadosEntrada = leitura.gerarDadosEntrada();
    }


}
