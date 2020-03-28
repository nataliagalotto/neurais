package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

public class Calcula {

    List<Double> deltinhas_K = new ArrayList<>();
    Double[][] deltao_JK;
    List<Double> deltao_biasWK = new ArrayList<>();
    List<Double> deltinha_J = new ArrayList<>();
    Double[][] deltao_vIJ;
    List<Double> deltao_biasVJ = new ArrayList<>();
    List<NeuronioPerceptron> neuroniosSensores;
    List<NeuronioPerceptron> neuroniosProcessadores;
    List<NeuronioPerceptron> neuroniosSaida;
    Double [] erro = new Double[7];


    public Calcula(List<NeuronioPerceptron> neuroniosSensores, List<NeuronioPerceptron> neuroniosProcessadores, List<NeuronioPerceptron> neuroniosSaida) {
        this.neuroniosSensores = neuroniosSensores;
        this.neuroniosProcessadores = neuroniosProcessadores;
        this.neuroniosSaida = neuroniosSaida;
    }

    public  Double [][] funcaoDeltaoWJK(int [] targets, Double alfa){
        deltao_JK = new Double[neuroniosSaida.size()][neuroniosProcessadores.size()];

        for (int k = 0; k < neuroniosSaida.size() ; k++) {
            Double y_in = neuroniosSaida.get(k).getSomatorio();
            Double y = neuroniosSaida.get(k).getDado();

            erro [k] = (targets[k]- y);
            deltinhas_K.add( erro [k] * funcaoDerivada(y_in));

            for (int j = 0; j < neuroniosProcessadores.size() ; j++) {
                deltao_JK[k][j] = (deltinhas_K.get(k) * alfa * neuroniosProcessadores.get(j).getDado());
            }
        }
        return deltao_JK;
    }

    public  Double [][] funcaoDeltaoVIJ(Double alfa){
        deltao_vIJ = new Double[neuroniosProcessadores.size()][neuroniosSensores.size()]; //qtdPesos
        Double[] deltinha_inJ = new Double [neuroniosProcessadores.size()];

        for (int j = 0; j < neuroniosProcessadores.size() ; j++) {
            deltinha_inJ[j] = 0.0;

            for (int k = 0; k < neuroniosSaida.size(); k++) {
                deltinha_inJ[j] = deltinha_inJ[j] +(deltinhas_K.get(k) * neuroniosProcessadores.get(j).getPeso(k));
            }

            Double z_in = neuroniosProcessadores.get(j).getSomatorio();
            deltinha_J.add(deltinha_inJ[j] * funcaoDerivada(z_in));

            for (int i = 0; i < neuroniosSensores.size() ; i++) {
                deltao_vIJ[j][i] = (alfa * deltinha_J.get(j) * neuroniosSensores.get(i).getDado());
            }
        }

        return deltao_vIJ;

    }

    public List<Double> funcaoBiasWK(Double alfa){
        for (int k = 0; k < neuroniosSaida.size(); k++) {
            deltao_biasWK.add(alfa * deltinhas_K.get(k));
        }
        return deltao_biasWK;
    }

    public List<Double>  funcaoBiasVJ(Double alfa){
        for (int j = 0; j < neuroniosProcessadores.size(); j++) {
            deltao_biasVJ.add(alfa * deltinha_J.get(j));
        }

        return deltao_biasVJ;
    }

    public Double funcaoDerivada(Double x){
        Double y = 1/(1 + (Math.exp(-x)));
        return y - Math.pow(y,2);
    }

    public Double[] getErro() {
        return erro;
    }

}
