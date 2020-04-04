package com.projeto.ia.redes.neurais;
import java.util.ArrayList;
import java.util.List;

/*
    Classe responsável por
    gerar a camada de saída
 */
public class CamadaSaida extends CamadaBase {


    //Construtor da classe com a
    //quantidade de neurônios
    //definida
    public CamadaSaida() {
        this.qtdNeuronios = 7;
    }

    //Método responsável por criar uma List
    //com os neurônios da camada de saída
    public List<NeuronioPerceptron> gerarListaNeuroniosSaida(){
        NeuronioFactory neuronioFactory = new NeuronioFactory();

        //Instancia neuronios de Saida de acordo
        //com a quantidade definida anteriormente
        //armanzenando numa List e retorna o resultado
        for (int i = 0; i < qtdNeuronios ; i++) {
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuroniosSaida.add(neuronioPerceptron);
        }
        return neuroniosSaida;
    }

    //Método utilizado para atualizar os dados
    //do neurônio no passo backpropagation
    public List<NeuronioPerceptron> atualizaDadosNeuronios(){

        //Para cada um dos neurônios
        //utiliza os valores de correcao
        //de erro para atualizar os
        //valores da camada
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
