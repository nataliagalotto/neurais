package com.projeto.ia.redes.neurais.servico;
import com.projeto.ia.redes.neurais.entidades.Bias;
import com.projeto.ia.redes.neurais.entidades.NeuronioPerceptron;
import com.projeto.ia.redes.neurais.factory.NeuronioFactory;

import java.util.List;

/*
    Classe responsável por
    gerar a camada de saída
 */
public class CamadaSaida extends CamadaBase {


    //Construtor da classe com a
    //quantidade de neurônios
    //definida
    public CamadaSaida(int qtdNeuronios) {
        this.qtdNeuronios = qtdNeuronios;
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
    public List<NeuronioPerceptron> atualizaDadosNeuronios(Bias biasOculta){

        //Para cada um dos neurônios
        //utiliza os valores de correcao
        //de erro para atualizar os
        //valores da camada
        for (int i = 0; i < qtdNeuronios ; i++) {
            Double bias = biasOculta.getPesos().get(i);
            Double y_in = somatorio(i, bias, neuroniosProcessadores);
            Double yzinho = funcaoAtivacao(y_in);

            neuroniosSaida.get(i).setSomatorio(y_in);
            neuroniosSaida.get(i).setDado(yzinho);
        }
        return neuroniosSaida;
    }
}
