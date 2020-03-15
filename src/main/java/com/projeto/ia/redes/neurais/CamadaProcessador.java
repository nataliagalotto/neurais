package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

public class CamadaProcessador {

    List<Double> dados = new ArrayList<>();
    List<String> targets = new ArrayList<>();
    List<NeuronioPerceptron> neuroniosSensores = new ArrayList<>();
    List<NeuronioPerceptron> neuroniosProcessadores = new ArrayList<>();
    Double bias;

    public Double funcaoAtivacao(Double somatorio){
        return 1/(1 + (Math.exp(-somatorio)));
    }

    public Double somatorio ( Integer indice){
        bias = 1.0;
        Double somatorio = 0.0;
        for (NeuronioPerceptron neuronio : neuroniosSensores){
            //System.out.println("Dado: "+neuronio.getDado()+ " Peso: "+neuronio.getPesos().get(indice));
            somatorio = (neuronio.getDado() * neuronio.getPesos().get(indice)) + somatorio;
            //System.out.println("Somatorio: "+ somatorio);
        }
        somatorio = bias + somatorio;
        System.out.println("Somatorio do NP:  "+ indice + " valor: "+somatorio);
        return somatorio;
    }

    public List<NeuronioPerceptron> gerarListaNeuroniosProcessadores(){
        NeuronioFactory neuronioFactory = new NeuronioFactory();
        for (int i = 0; i < 20 ; i++) {
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.setSomatorio(somatorio(i));
            neuronioPerceptron.setDado(funcaoAtivacao(somatorio(i)));
            neuronioPerceptron.getPesos();
            neuroniosProcessadores.add(neuronioPerceptron);
        }
        return neuroniosProcessadores;
    }

    public List<NeuronioPerceptron> getNeuroniosSensores() {
        return neuroniosSensores;
    }

    public void setNeuroniosSensores(List<NeuronioPerceptron> neuroniosSensores) {
        this.neuroniosSensores = neuroniosSensores;
    }

    public static Double getRandomDouble(){
        double min = -1;
        double max =  1;
        double numero = ( Math.random() * ( (max-min) + 1 ) ) + min;
        if (numero > max || numero < min){
            return 1.0;
        }
        return numero;
    }

}
