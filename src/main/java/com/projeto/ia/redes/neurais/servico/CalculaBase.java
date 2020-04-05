package com.projeto.ia.redes.neurais.servico;

import com.projeto.ia.redes.neurais.entidades.NeuronioPerceptron;

import java.util.ArrayList;
import java.util.List;

public class CalculaBase {

    List<Double> deltinhas_K = new ArrayList<>();       //Estrutura que armazena o termo de informação de erro da camada oculta(ou saida)
    Double[][] deltao_JK;                               //Estrutura que armazena a correção de pesos da camada oculta (ou saida)
    List<Double> deltao_biasWK = new ArrayList<>();     //Estrutura que armazena a correção de peso do bias da camada oculta(ou saida)
    List<Double> deltinha_J = new ArrayList<>();        //Estrutura que armazena o termo de informação de erro da camada sensor(ou oculta)
    Double[][] deltao_vIJ;                              //Estrutura que armazena  a correção de pesos da camada sensor(ou oculta)
    List<Double> deltao_biasVJ = new ArrayList<>();     //Estrutura que armazena a correção de pesos da camada sensor(ou oculta)
    List<NeuronioPerceptron> neuroniosSensores;         //Estrutura que recebe os neuronios sensores para realizar os cálculos
    List<NeuronioPerceptron> neuroniosProcessadores;    //Estrutura que recebe os neuronios processadores para realizar os cálculos
    List<NeuronioPerceptron> neuroniosSaida;            //Estrutura que recebe os neuronios saída para realizar os cálculos
    Double [] erro = new Double[7];                     //Estrutura que recebe os erros cometidos no treinamento

}
