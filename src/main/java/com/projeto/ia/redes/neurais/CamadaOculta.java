package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

//dado = zzinho
//somatorio = z_in
//peso = w
public class CamadaOculta extends CamadaBase {

    List<Double> deltinha_J = new ArrayList<>();
    Double[][] deltao_vIJ;
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
        deltao_vIJ = new Double[qtdNeuronios][neuroniosSensores.size()]; //qtdPesos
        Double[] deltinha_inJ = new Double [qtdNeuronios];

        for (int j = 0; j < qtdNeuronios ; j++) {
            deltinha_inJ[j] = 0.0;

            for (int k = 0; k < camadaSaida.qtdNeuronios ; k++) {
                deltinha_inJ[j] = deltinha_inJ[j] +(camadaSaida.deltinhas_K.get(k) * neuroniosProcessadores.get(j).getPeso(k));
            }

            Double z_in = neuroniosProcessadores.get(j).getSomatorio();
            deltinha_J.add(deltinha_inJ[j] * funcaoDerivada(z_in));

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
            NeuronioPerceptron neuroniosProcessador = neuroniosProcessadores.get(k);
            neuroniosProcessador.setBias(neuroniosProcessador.getBias() + deltao_biasWK.get(k));

            for (int j = 0; j < neuroniosProcessadores.size(); j++) {
                neuroniosProcessador.setPeso(j ,neuroniosProcessador.getPeso(j) + deltao_JK[k][j]);
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
