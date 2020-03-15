package com.projeto.ia.redes.neurais;

import java.util.List;

public class Rede {

    public  List<NeuronioPerceptron> gerarCamadaSensor(){
        CamadaSensor camadaSensor = new CamadaSensor();
        camadaSensor.getEntrada();
        return camadaSensor.gerarListaNeuronios();
    }

    public void gerarCamadaOculta(){
        CamadaProcessador camadaProcessador = new CamadaProcessador();
        List<NeuronioPerceptron> neuronioSensoriais = gerarCamadaSensor();
        camadaProcessador.setNeuroniosSensores(neuronioSensoriais);
        camadaProcessador.gerarListaNeuroniosProcessadores();
    }

    public void gerarCamadaSaida(){

    }



}
