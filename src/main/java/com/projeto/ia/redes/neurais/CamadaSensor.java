package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

//dado = xzinho
//peso = v
public class CamadaSensor extends CamadaBase{

    List<Double> dadosEntrada = new ArrayList<>();

    public CamadaSensor() {
        this.qtdPesos = 20;
        this.qtdNeuronios = 63;
    }

    public List<NeuronioPerceptron> gerarListaNeuroniosComPesos(){
        NeuronioFactory neuronioFactory = new NeuronioFactory();
        for (int i = 0; i < qtdNeuronios ; i++) {
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.gerarPesos(qtdPesos);
            neuroniosSensores.add(neuronioPerceptron);
        }
        return neuroniosSensores;
    }

    public List<NeuronioPerceptron> atualizaDadosNeuronios(){
        for (NeuronioPerceptron neuroniosSensor : neuroniosSensores){
            for(Double dado : dadosEntrada){
                neuroniosSensor.setDado(dado);
            }
        }
        return neuroniosSensores;
    }

    public List<NeuronioPerceptron> atualizaPesosVJ(Double [][] deltao_vIJ){
        for (int k = 0; k < qtdNeuronios; k++) {
            for (int j = 0; j < qtdPesos; j++) {
                Double peso = (neuroniosSensores.get(k).getPeso(j) + deltao_vIJ[j][k]);
                neuroniosSensores.get(k).setPeso(j , peso);
            }
        }

        return neuroniosSensores;
    }


    public void setDadosEntrada(List<Double> dadosEntrada) {
        this.dadosEntrada = dadosEntrada;
    }
}
