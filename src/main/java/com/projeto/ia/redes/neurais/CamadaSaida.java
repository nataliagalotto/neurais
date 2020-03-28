package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

//dado = yzinho
//somatorio = y_in
public class CamadaSaida extends CamadaBase {

    public CamadaSaida() {
        this.qtdNeuronios = 7;
    }

    public List<NeuronioPerceptron> gerarListaNeuroniosSaida(){
        NeuronioFactory neuronioFactory = new NeuronioFactory();

        for (int i = 0; i < qtdNeuronios ; i++) {
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuroniosSaida.add(neuronioPerceptron);
        }
        return neuroniosSaida;
    }

    public List<NeuronioPerceptron> atualizaDadosNeuronios(){

        for (int i = 0; i < qtdNeuronios ; i++) {
            Double bias = neuroniosSaida.get(i).getBias();
            Double y_in = somatorio(i, bias, neuroniosProcessadores);
            Double yzinho = funcaoAtivacao(y_in);

            neuroniosSaida.get(i).setSomatorio(y_in);
            neuroniosSaida.get(i).setDado(yzinho);
        }
        return neuroniosSaida;
    }

    public List<NeuronioPerceptron> atualizaBiasWK(List<Double> deltao_biasWK){
        for (int k = 0; k < neuroniosSaida.size(); k++) {
            NeuronioPerceptron neuronioSaida = neuroniosSaida.get(k);
            neuronioSaida.setBias(neuronioSaida.getBias() + deltao_biasWK.get(k));
        }

        return neuroniosSaida;
    }

}
