package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

//dado = zzinho
//somatorio = z_in
//peso = w
public class CamadaOculta extends CamadaBase {

    List<Double> deltinha_J = new ArrayList<>();
    Double[][] deltao_vIJ = new Double[qtdNeuronios][qtdPesos];
    List<Double> deltao_biasVJ = new ArrayList<>();

    public CamadaOculta() {
        this.qtdPesos = 7;
        this.qtdNeuronios = 20;
    }

    public List<NeuronioPerceptron> gerarListaNeuroniosProcessadores(){
        NeuronioFactory neuronioFactory = new NeuronioFactory();
        Double bias = 1.0;

        for (int i = 0; i < qtdNeuronios ; i++) {
            Double z_in = somatorio(i, bias, neuroniosSensores);
            Double zzinho = funcaoAtivacao(z_in);

            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.setSomatorio(z_in);
            neuronioPerceptron.setDado(zzinho);
            neuronioPerceptron.gerarPesos(qtdPesos);
            neuroniosProcessadores.add(neuronioPerceptron);
        }
        return neuroniosProcessadores;
    }

    public void funcaoDeltao(CamadaSaida camadaSaida, Double alfa){
        List<Double> deltinha_inJ = new ArrayList<>();

        for (int j = 0; j < qtdNeuronios ; j++) {
            for (int k = 0; k < camadaSaida.qtdNeuronios ; k++) {
                deltinha_inJ.add(camadaSaida.deltinhas_K.get(k) * neuroniosProcessadores.get(k).getPeso(j));
            }

            Double z_in = neuroniosProcessadores.get(j).getSomatorio();
            deltinha_J.add(deltinha_inJ.get(j) * funcaoDerivada(z_in));

            for (int i = 0; i < neuroniosSensores.size() ; i++) {
                deltao_vIJ[j][i] = alfa * deltinha_J.get(j) * neuroniosSensores.get(i).getDado();
            }
        }
    }

    public void funcaoBias(Double alfa){
        for (int j = 0; j < qtdNeuronios; j++) {
            deltao_biasVJ.add(alfa * deltinha_J.get(j));
        }
    }

    public void atualizaPesosBias(){
        for (int k = 0; k < qtdNeuronios; k++) {
            NeuronioPerceptron neuronioProcessador = neuroniosProcessadores.get(k);
            neuronioProcessador.setBias(neuronioProcessador.getBias() + deltao_biasVJ.get(k));

            for (int j = 0; j < neuroniosProcessadores.size(); j++) {
                neuronioProcessador.setPeso(j ,neuronioProcessador.getPeso(j) + deltao_vIJ[k][j]);
            }
        }
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
}
