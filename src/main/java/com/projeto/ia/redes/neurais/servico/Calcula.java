package com.projeto.ia.redes.neurais.servico;
import com.projeto.ia.redes.neurais.entidades.NeuronioPerceptron;

import java.util.ArrayList;
import java.util.List;


/*
    Classe responsável por realizar
    alguns cálculos do processo de aprendizado
 */
public class Calcula {

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


    //Construtor da classe
    public Calcula(List<NeuronioPerceptron> neuroniosSensores, List<NeuronioPerceptron> neuroniosProcessadores, List<NeuronioPerceptron> neuroniosSaida) {
        this.neuroniosSensores = neuroniosSensores;
        this.neuroniosProcessadores = neuroniosProcessadores;
        this.neuroniosSaida = neuroniosSaida;
    }


    /*
        Método responsável por calcular a correção de peses da camada de saída
     */
    public  Double [][] funcaoDeltaoWJK(int [] targets, Double alfa){

        //Instancia o vetor que recebe a correção de pesos da última camada
        //matriz cuja dimensão é qtd de neurônios saída por qtd de neuronios
        //da camada oculta
        deltao_JK = new Double[neuroniosSaida.size()][neuroniosProcessadores.size()];

        //Para cada neurônio de saída, pega o valor de saída
        //após aplicada a função de ativação: y
        //e o valor da soma ponderada, antes de
        //ser aplicada a função de ativação: y_in
        for (int k = 0; k < neuroniosSaida.size() ; k++) {
            Double y_in = neuroniosSaida.get(k).getSomatorio();
            Double y = neuroniosSaida.get(k).getDado();

            //Calcula o erro com base no target e na
            //saída da camada final y
            erro [k] = (targets[k]- y);

            //Computa a correção dos pesos da camada de saida
            //multiplicando o erro pela função derivada
            //aplicada em y_in
            //Armazena em deltinhas_K o termo de informação de erro
            deltinhas_K.add( erro[k] * funcaoDerivada(y_in));

            //Calcula a correção dos pesos e armazena em
            //deltao_JK
            for (int j = 0; j < neuroniosProcessadores.size() ; j++) {
                deltao_JK[k][j] = (deltinhas_K.get(k) * alfa * neuroniosProcessadores.get(j).getDado());
            }
        }
        //Retorna deltao_JK com a correção dos pesos
        return deltao_JK;
    }


    /*
        Método responsável por calcular a correção de peses da camada escondida
     */
    public  Double [][] funcaoDeltaoVIJ(Double alfa){

        //Instancia o vetor que recebe a correção de pesos da camada
        //matriz cuja dimensão é qtd de neurônios saída por qtd de neuronios
        //da camada oculta
        deltao_vIJ = new Double[neuroniosProcessadores.size()][neuroniosSensores.size()]; //qtdPesos
        Double[] deltinha_inJ = new Double [neuroniosProcessadores.size()];

        //Para cada neurônio da camada oculta
        //calcula deltinha_inJ para usar adiante:
        //multiplica o valor obtido no método anterior: deltinha_K
        //pelo peso correspondente e somando os resultados
        for (int j = 0; j < neuroniosProcessadores.size() ; j++) {
            deltinha_inJ[j] = 0.0;

            for (int k = 0; k < neuroniosSaida.size(); k++) {
                deltinha_inJ[j] = deltinha_inJ[j] +(deltinhas_K.get(k) * neuroniosProcessadores.get(j).getPeso(k));
            }

            //Para cada neurônio da camada oculta
            //calcula o termo de informação de erro
            //utiliza deltinha_inJ obtido acima e
            //multiplica pela função derivada
            //aplicada na soma das entradas ponderadas
            //da camada
            Double z_in = neuroniosProcessadores.get(j).getSomatorio();
            deltinha_J.add(deltinha_inJ[j] * funcaoDerivada(z_in));

            //Calcula a correção dos pesos da camada oculta
            //e armazena em deltao_vIJ
            for (int i = 0; i < neuroniosSensores.size() ; i++) {
                deltao_vIJ[j][i] = (alfa * deltinha_J.get(j) * neuroniosSensores.get(i).getDado());
            }
        }
        //Retorna deltao_vIJ com a correção dos pesos da camada oculta
        return deltao_vIJ;

    }


    /*
        Método responsável por computar a correção
        do Bias da camada de saída
     */
    public List<Double> funcaoBiasWK(Double alfa){

        //Para cada neuronio da camada de saida
        //multiplica o valor de alpha pelo Bias
        //correspondente, armazena em deltao_biasWK e
        //retorna o resultado
        for (int k = 0; k < neuroniosSaida.size(); k++) {
            deltao_biasWK.add(alfa * deltinhas_K.get(k));
        }
        return deltao_biasWK;
    }


    /*
        Método responsável por computar a correção
        do Bias da camada oculta
     */
    public List<Double>  funcaoBiasVJ(Double alfa){

        //Para cada neuronio da camada oculta
        //multiplica o valor de alpha pelo Bias
        //correspondente, armazena em deltao_biasVJ e
        //retorna o resultado
        for (int j = 0; j < neuroniosProcessadores.size(); j++) {
            deltao_biasVJ.add(alfa * deltinha_J.get(j));
        }

        return deltao_biasVJ;
    }


    /*
        Método responsável por aplicar a função derivada
     */
    public Double funcaoDerivada(Double x){
        Double y = 1/(1 + (Math.exp(-x)));
        return y - Math.pow(y,2);
    }


    /*
        Getter dos erros
        cometidos pela rede
     */
    public Double[] getErro() {
        return erro;
    }

}
