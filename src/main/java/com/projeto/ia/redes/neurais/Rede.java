package com.projeto.ia.redes.neurais;

import java.util.List;

//Responsavel por gerar as Camadas e administrar elas
public class Rede {

    //Responsavel por gerar a camada sensor
    public  CamadaSensor gerarCamadaSensor(List<Double> dadosEntrada){
        CamadaSensor camadaSensor = new CamadaSensor();
        camadaSensor.setDadosEntrada(dadosEntrada);
        camadaSensor.gerarListaNeuronios();
        return camadaSensor;
    }

    //Responsavel por gerar a Camada oculta
    public CamadaProcessador gerarCamadaOculta(CamadaSensor camadaSensor){
        CamadaProcessador camadaProcessador = new CamadaProcessador();
        camadaProcessador.setNeuroniosSensores(camadaSensor.getNeuroniosSensores());
        camadaProcessador.gerarListaNeuroniosProcessadores();
        return camadaProcessador;
    }

    //Responsavel por gerar a Camada Processador
    public CamadaSaida gerarCamadaSaida(CamadaProcessador camadaProcessador){
        CamadaSaida camadaSaida = new CamadaSaida();
        camadaSaida.setneuroniosProcessadores(camadaProcessador.getNeuroniosProcessadores());
        camadaSaida.gerarListaNeuroniosSaida();
        return camadaSaida;
    }

}
