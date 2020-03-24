package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

public class CamadaSaida {
     //dado = yzinho
     //somatorio = y_in

    List<NeuronioPerceptron> neuroniosProcessadores = new ArrayList<>();
    List<NeuronioPerceptron> neuroniosSaida = new ArrayList<>();
    List<Double> deltinhas_K = new ArrayList<>();
    Double bias;
    int qtdPesos;
    int qtdNeuronios = 7;

    public Double funcaoAtivacao(Double somatorio){
        return 1/(1 + (Math.exp(-somatorio)));
    }

    public Double somatorio ( Integer indice){
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
        for (int i = 0; i < qtdNeuronios ; i++) {
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.setSomatorio(somatorio(i));
            neuronioPerceptron.setDado(funcaoAtivacao(somatorio(i)));
            neuroniosSaida.add(neuronioPerceptron);
        }
        return neuroniosSaida;
    }

    public  Double[][] funcaoDeltao(int [] targets,Double alfa){
        Double[][] deltao_JK = new Double[neuroniosSaida.size()][neuroniosProcessadores.size()];
        Double y_in;
        Double y;

        for (int k = 0; k < neuroniosSaida.size() ; k++) {
            y_in = neuroniosSaida.get(k).getSomatorio();
            y = neuroniosSaida.get(k).getDado();
            deltinhas_K.add((targets[k]- y) * );

            for (int j = 0; j < neuroniosProcessadores.size() ; j++) {
                deltao_JK[k][j] = (deltinhas_K.get(k) * alfa * neuroniosProcessadores.get(j).getDado());
            }
        }

        return deltao_JK;
    }




    public List<NeuronioPerceptron> getneuroniosProcessadores() {
        return neuroniosProcessadores;
    }

    public void setneuroniosProcessadores(List<NeuronioPerceptron> neuroniosSensores) {
        this.neuroniosProcessadores = neuroniosSensores;
    }

    public List<Double> getDeltinhas_K() {
        return deltinhas_K;
    }

    public void setDeltinhas_K(List<Double> deltinhas_K) {
        this.deltinhas_K = deltinhas_K;
    }
}
