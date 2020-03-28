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

    public void atualizaPesosBias(){
        for (int k = 0; k < qtdNeuronios; k++) {
            NeuronioPerceptron neuroniosSensor = neuroniosSensores.get(k);
            neuroniosSensor.setBias(neuroniosSensor.getBias() + deltao_biasVJ.get(k));

            for (int j = 0; j < neuroniosProcessadores.size(); j++) {
                neuroniosSensor.setPeso(j ,neuroniosSensor.getPeso(j) + deltao_vIJ[k][j]);
            }
        }
    }

    public void setDadosEntrada(List<Double> dadosEntrada) {
        this.dadosEntrada = dadosEntrada;
    }

    public List<NeuronioPerceptron> getNeuroniosSensores() {
        return neuroniosSensores;
    }
}
