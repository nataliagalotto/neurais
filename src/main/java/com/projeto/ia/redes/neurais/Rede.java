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
    public CamadaOculta gerarCamadaOculta(CamadaSensor camadaSensor){
        CamadaOculta camadaOculta = new CamadaOculta();
        camadaOculta.setNeuroniosSensores(camadaSensor.getNeuroniosSensores());
        camadaOculta.gerarListaNeuroniosProcessadores();
        return camadaOculta;
    }

    //Responsavel por gerar a Camada Processador
    public CamadaSaida gerarCamadaSaida(CamadaOculta camadaOculta){
        CamadaSaida camadaSaida = new CamadaSaida();
        camadaSaida.setneuroniosProcessadores(camadaOculta.getNeuroniosProcessadores());
        camadaSaida.gerarListaNeuroniosSaida();
        return camadaSaida;
    }

}
