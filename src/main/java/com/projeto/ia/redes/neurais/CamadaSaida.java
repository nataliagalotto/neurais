package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

public class CamadaSaida {
     //dado = yzinho
     //somatorio = y_in

    List<NeuronioPerceptron> neuroniosProcessadores = new ArrayList<>();
    List<NeuronioPerceptron> neuroniosSaida = new ArrayList<>();
    List<Double> deltinhas_K = new ArrayList<>();
    Double[][] deltao_JK = new Double[neuroniosSaida.size()][neuroniosProcessadores.size()];
    List<Double> deltao_biasWK = new ArrayList<>();
    int qtdPesos;
    int qtdNeuronios = 7;

    public Double funcaoAtivacao(Double somatorio){
        return 1/(1 + (Math.exp(-somatorio)));
    }

    public Double somatorio ( Integer indice, Double bias){
        bias = 1.0;
        Double somatorio = 0.0;
        for (NeuronioPerceptron neuronio : neuroniosProcessadores){
            System.out.println("Dado: "+neuronio.getDado()+ " Peso: "+neuronio.getPeso(indice));
            somatorio = (neuronio.getDado() * neuronio.getPeso(indice)) + somatorio;
            System.out.println("Somatorio: "+ somatorio);
        }
        somatorio = bias + somatorio;
        System.out.println("Somatorio do NP:  "+ indice + " valor: "+somatorio);
        return somatorio;
    }

    public List<NeuronioPerceptron> gerarListaNeuroniosSaida(){
        NeuronioFactory neuronioFactory = new NeuronioFactory();
        Double yzinho;
        Double y_in;
        Double bias = 1.0;

        for (int i = 0; i < qtdNeuronios ; i++) {
            y_in = somatorio(i, bias);
            yzinho = funcaoAtivacao(y_in);

            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.setSomatorio(y_in);
            neuronioPerceptron.setDado(yzinho);
            neuroniosSaida.add(neuronioPerceptron);
        }
        return neuroniosSaida;
    }

    public  void funcaoDeltao(int [] targets,Double alfa){
        Utils utils = new Utils();
        Double y_in;
        Double y;
        Double erro;

        for (int k = 0; k < neuroniosSaida.size() ; k++) {
            y_in = neuroniosSaida.get(k).getSomatorio();
            y = neuroniosSaida.get(k).getDado();
            erro = (targets[k]- y);
            deltinhas_K.add( erro * utils.funcaoDerivada(y_in));

            for (int j = 0; j < neuroniosProcessadores.size() ; j++) {
                deltao_JK[k][j] = (deltinhas_K.get(k) * alfa * neuroniosProcessadores.get(j).getDado());
            }
        }
    }


    public void funcaoBias(Double alfa){
        for (int k = 0; k < qtdNeuronios; k++) {
            deltao_biasWK.add(alfa * deltinhas_K.get(k));
        }
    }
    
    public void atualizaPesosBias(){
        for (int k = 0; k < qtdNeuronios; k++) {
            NeuronioPerceptron neuronioSaida = neuroniosSaida.get(k);
            neuronioSaida.setBias(neuronioSaida.getBias() + deltao_biasWK.get(k));

            for (int j = 0; j < neuroniosProcessadores.size(); j++) {
                neuronioSaida.setPeso(j ,neuronioSaida.getPeso(j) + deltao_JK[k][j]);
            }
        }
    }

    public List<NeuronioPerceptron> getneuroniosProcessadores() {
        return neuroniosProcessadores;
    }

    public void setneuroniosProcessadores(List<NeuronioPerceptron> neuroniosSensores) {
        this.neuroniosProcessadores = neuroniosSensores;
    }

}
