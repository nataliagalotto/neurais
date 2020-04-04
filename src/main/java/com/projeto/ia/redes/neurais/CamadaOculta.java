package com.projeto.ia.redes.neurais;
import java.util.ArrayList;
import java.util.List;

public class CamadaOculta extends CamadaBase {


    public CamadaOculta() {
        this.qtdPesos = 7;
        this.qtdNeuronios = 20;
    }

    public List<NeuronioPerceptron> gerarListaNeuroniosComPesos(){
        bias.gerarPesos(qtdPesos);
        NeuronioFactory neuronioFactory = new NeuronioFactory();

        for (int i = 0; i < qtdNeuronios ; i++) { //qtdNeuronios = 20 (correto!!)
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.gerarPesos(qtdPesos);
            neuroniosProcessadores.add(neuronioPerceptron);
        }

        return neuroniosProcessadores;
    }

    public List<NeuronioPerceptron> gerarListaNeuroniosComPesosTeste(List<Double> pesosEntrada){
        NeuronioFactory neuronioFactory = new NeuronioFactory();
        int inicial = 0;
        int limite = inicial+ qtdPesos;
        for (int i = 0; i < qtdNeuronios ; i++) {
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.gerarPesosTeste(inicial, limite, pesosEntrada);
            neuroniosProcessadores.add(neuronioPerceptron);
            inicial = inicial + qtdPesos;
            limite  = limite + qtdPesos;
        }
        return neuroniosProcessadores;
    }

    public void gerarBiasComPesosTeste(List<Double> pesosBiasEntrada){
        bias.gerarPesosTeste(pesosBiasEntrada);
    }

    public List<NeuronioPerceptron> atualizaDadosNeuronios(Bias biasSensor){
        //Revisar o somatorio na classe CamadaBase um bias a mais na
        //Fazer apenas a inicialização dos neuronios e qtdPesos
        //Somatorio e demais tarefas passar para Aprendizado

        for (int i = 0; i < qtdNeuronios ; i++) { //qtdNeuronios = 20 (correto!!)
            Double bias = biasSensor.getPesos().get(i);
            Double z_in = somatorio( i, bias, neuroniosSensores);
            Double zzinho = funcaoAtivacao(z_in);

            neuroniosProcessadores.get(i).setSomatorio(z_in);
            neuroniosProcessadores.get(i).setDado(zzinho);
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

    public void atualizaBiasWK(List<Double> deltao_biasWK){
        for (int j = 0; j < qtdPesos; j++) {
            Double peso = (bias.getPesos().get(j) + deltao_biasWK.get(j));
            bias.setPeso(j,peso);;
        }
    }
}
