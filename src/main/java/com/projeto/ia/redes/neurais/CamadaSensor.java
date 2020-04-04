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
        bias.gerarPesos(qtdPesos);
        NeuronioFactory neuronioFactory = new NeuronioFactory();

        for (int i = 0; i < qtdNeuronios ; i++) {
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.gerarPesos(qtdPesos);
            neuroniosSensores.add(neuronioPerceptron);
        }
        return neuroniosSensores;
    }

    public List<NeuronioPerceptron> gerarListaNeuroniosComPesosTeste(List<Double> pesosEntrada){
        NeuronioFactory neuronioFactory = new NeuronioFactory();
        int inicial = 0;
        int limite = qtdPesos;
        for (int i = 0; i < qtdNeuronios ; i++) {
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.gerarPesosTeste(inicial, limite, pesosEntrada);
            neuroniosSensores.add(neuronioPerceptron);
            inicial = inicial + qtdPesos;
            limite  = limite + qtdPesos;
        }
        return neuroniosSensores;
    }

    public void gerarBiasComPesosTeste(List<Double> pesosBiasEntrada){
        bias.gerarPesosTeste(pesosBiasEntrada);
    }

    public List<NeuronioPerceptron> atualizaDadosNeuronios(){
        for (NeuronioPerceptron neuroniosSensor : neuroniosSensores){
            for(Double dado : dadosEntrada){
                neuroniosSensor.setDado(dado);
            }
        }
        return neuroniosSensores;
    }

    public void atualizaBiasVJ(List<Double> deltao_biasVJ){
        for (int j = 0; j < qtdPesos; j++) {
            Double peso = (bias.getPesos().get(j) + deltao_biasVJ.get(j));
            bias.setPeso(j,peso);;
        }
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
