package com.projeto.ia.redes.neurais.servico;

import com.projeto.ia.redes.neurais.entidades.NeuronioPerceptron;

import java.util.ArrayList;
import java.util.List;

public class CalculaBase {

    List<NeuronioPerceptron> neuroniosSensores;         //Estrutura que recebe os neuronios sensores para realizar os cálculos
    List<NeuronioPerceptron> neuroniosProcessadores;    //Estrutura que recebe os neuronios processadores para realizar os cálculos
    List<NeuronioPerceptron> neuroniosSaida;            //Estrutura que recebe os neuronios saída para realizar os cálculos
    Double [] erro;                  //Estrutura que recebe os erros cometidos no treinamento
    Double [] erroLogLess;
}
