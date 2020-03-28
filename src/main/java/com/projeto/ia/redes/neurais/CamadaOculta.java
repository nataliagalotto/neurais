package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

//dado = zzinho
//somatorio = z_in
//peso = w
public class CamadaOculta extends CamadaBase {


    public CamadaOculta() {
        this.qtdPesos = 7;
        this.qtdNeuronios = 20;
    }

    public List<NeuronioPerceptron> gerarListaNeuroniosComPesos(){
        NeuronioFactory neuronioFactory = new NeuronioFactory();

        for (int i = 0; i < qtdNeuronios ; i++) { //qtdNeuronios = 20 (correto!!)
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.gerarPesos(qtdPesos);
            neuroniosProcessadores.add(neuronioPerceptron);
        }

        return neuroniosProcessadores;
    }

    public List<NeuronioPerceptron> atualizaDadosNeuronios(){
        //Revisar o somatorio na classe CamadaBase um bias a mais na
        //Fazer apenas a inicialização dos neuronios e qtdPesos
        //Somatorio e demais tarefas passar para Aprendizado

        for (int i = 0; i < qtdNeuronios ; i++) { //qtdNeuronios = 20 (correto!!)
            Double bias = neuroniosProcessadores.get(i).getBias();
            Double z_in = somatorio( i, bias, neuroniosSensores);
            Double zzinho = funcaoAtivacao(z_in);

            neuroniosProcessadores.get(i).setSomatorio(z_in);
            neuroniosProcessadores.get(i).setDado(zzinho);
        }

        return neuroniosProcessadores;
    }

    public List<NeuronioPerceptron> atualizaBiasVJ(List<Double> deltao_biasVJ){
        for (int k = 0; k < neuroniosProcessadores.size(); k++) {
            NeuronioPerceptron neuroniosProcessador = neuroniosProcessadores.get(k);
            neuroniosProcessador.setBias(neuroniosProcessador.getBias() + deltao_biasVJ.get(k));
        }

        return neuroniosProcessadores;
    }

    public List<NeuronioPerceptron> atualizaPesosWK(Double [][] deltao_JK){
        for (int k = 0; k < qtdNeuronios; k++) {
            for (int j = 0; j <qtdPesos; j++) {
                Double peso = (neuroniosProcessadores.get(k).getPeso(j) + deltao_JK[j][k]);
                neuroniosProcessadores.get(k).setPeso(j ,peso);
            }
        }

        return neuroniosProcessadores;
    }


}
