package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

//dado = yzinho
//somatorio = y_in
public class CamadaSaida extends CamadaBase {

    List<Double> deltinhas_K = new ArrayList<>();
    Double[][] deltao_JK = new Double[neuroniosSaida.size()][neuroniosProcessadores.size()];
    List<Double> deltao_biasWK = new ArrayList<>();
    Double [] erro = new Double[7];

    public CamadaSaida() {
        this.qtdNeuronios = 7;
    }

    public List<NeuronioPerceptron> gerarListaNeuroniosSaida(){
        NeuronioFactory neuronioFactory = new NeuronioFactory();
        Double bias = 1.0;

        for (int i = 0; i < qtdNeuronios ; i++) {
            Double y_in = somatorio(i, bias, neuroniosProcessadores);
            Double yzinho = funcaoAtivacao(y_in);

            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.setSomatorio(y_in);
            neuronioPerceptron.setDado(yzinho);
            neuroniosSaida.add(neuronioPerceptron);
        }
        return neuroniosSaida;
    }

    public  void funcaoDeltao(int [] targets,Double alfa){
        for (int k = 0; k < neuroniosSaida.size() ; k++) {
            Double y_in = neuroniosSaida.get(k).getSomatorio();
            Double y = neuroniosSaida.get(k).getDado();

            erro [k] = (targets[k]- y);
            deltinhas_K.add( erro [k] * funcaoDerivada(y_in));

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

    public Double[] getErro() {
        return erro;
    }

    public void setErro(Double[] erro) {
        this.erro = erro;
    }

}
