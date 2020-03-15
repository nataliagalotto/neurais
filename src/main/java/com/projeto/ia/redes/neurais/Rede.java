package com.projeto.ia.redes.neurais;

import java.util.List;

//Responsavel por gerar as Camadas e administrar elas
public class Rede {

    //Responsavel por gerar a camada sensor
    public  List<NeuronioPerceptron> gerarCamadaSensor(){
        CamadaSensor camadaSensor = new CamadaSensor();
        return camadaSensor.gerarListaNeuronios();
    }

    //Responsavel por gerar a Camada oculta
    public List<NeuronioPerceptron> gerarCamadaOculta(List<NeuronioPerceptron> neuronioSensoriais){
        CamadaProcessador camadaProcessador = new CamadaProcessador();
        camadaProcessador.setNeuroniosSensores(neuronioSensoriais);
        return camadaProcessador.gerarListaNeuroniosProcessadores();
    }

    //Responsavel por gerar a Camada Processador
    public List<NeuronioPerceptron> gerarCamadaSaida(List<NeuronioPerceptron> neuronioProcessadores){
        CamadaSaida camadaSaida = new CamadaSaida();
        camadaSaida.setneuroniosProcessadores(neuronioProcessadores);
        return camadaSaida.gerarListaNeuroniosSaida();
    }



}
