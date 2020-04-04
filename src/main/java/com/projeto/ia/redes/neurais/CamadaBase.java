package com.projeto.ia.redes.neurais;
import java.util.ArrayList;
import java.util.List;

/*
    Classe pai que fornece os métodos utilizados por cada uma das camadas: Sensor,
    Camada Oculta e Saída
 */
public class CamadaBase {

    List<NeuronioPerceptron> neuroniosSensores = new ArrayList<>();         //Estrutura que armazena neuroniosSensores
    List<NeuronioPerceptron> neuroniosProcessadores = new ArrayList<>();    //Estrutura que armazena neuronios Processadores
    List<NeuronioPerceptron> neuroniosSaida = new ArrayList<>();            //Estrutura que armazena Neurônios de Saída
    int qtdNeuronios;                                                       //Variável que controla a qtd de neuronios da camada
    int qtdPesos;                                                           //Variável para controle da quantidade de pesos de cada neurônio
    Bias bias = new Bias();


    //Método responsável por aplicar a função de ativação 1 / (1 + e^(-x))

    public Double funcaoAtivacao(Double somatorio){
        return 1/(1 + (Math.exp(-somatorio)));
    }

    /*
        Método responsável por realizar a soma ponderada
        Recebe os dados, aplica os pesos, realiza a soma,
        adiciona o bias e retorna o resultado
     */

    public Double somatorio ( Integer indice, Double bias, List<NeuronioPerceptron> neuronioPerceptrons){
        Double somatorio = 0.0;
        for (NeuronioPerceptron neuronio : neuronioPerceptrons){
            //System.out.println("Dado: "+neuronio.getDado()+ " Peso: "+neuronio.getPeso(indice));
            somatorio = (neuronio.getDado() * neuronio.getPeso(indice)) + somatorio;
            //System.out.println("Somatorio: "+ somatorio);
        }
        somatorio = bias + somatorio;
        //System.out.println("Somatorio do NP:  "+ indice + " valor: "+somatorio);
        return somatorio;
    }


    //Getters e Setters da classe
    public int getQtdNeuronios() {
        return qtdNeuronios;
    }

    public void setQtdNeuronios(int qtdNeuronios) {
        this.qtdNeuronios = qtdNeuronios;
    }

    public int getQtdPesos() {
        return qtdPesos;
    }

    public void setQtdPesos(int qtdPesos) {
        this.qtdPesos = qtdPesos;
    }

    public List<NeuronioPerceptron> getNeuroniosSensores() {
        return neuroniosSensores;
    }

    public void setNeuroniosSensores(List<NeuronioPerceptron> neuroniosSensores) {
        this.neuroniosSensores = neuroniosSensores;
    }

    public List<NeuronioPerceptron> getNeuroniosProcessadores() {
        return neuroniosProcessadores;
    }

    public void setNeuroniosProcessadores(List<NeuronioPerceptron> neuroniosProcessadores) {
        this.neuroniosProcessadores = neuroniosProcessadores;
    }

    public List<NeuronioPerceptron> getNeuroniosSaida() {
        return neuroniosSaida;
    }

    public void setNeuroniosSaida(List<NeuronioPerceptron> neuroniosSaida) {
        this.neuroniosSaida = neuroniosSaida;
    }

    public Bias getBias() {
        return bias;
    }
}
