package com.projeto.ia.redes.neurais.servico;
import com.projeto.ia.redes.neurais.entidades.NeuronioPerceptron;

import java.util.ArrayList;
import java.util.List;


/*
    Classe responsável por realizar
    alguns cálculos do processo de aprendizado
 */
public class Calcula extends CalculaBase {

    //Construtor da classe
    public Calcula(List<NeuronioPerceptron> neuroniosSensores,
                   List<NeuronioPerceptron> neuroniosProcessadores,
                   List<NeuronioPerceptron> neuroniosSaida,
                   Double[] erro) {
        this.neuroniosSensores = neuroniosSensores;
        this.neuroniosProcessadores = neuroniosProcessadores;
        this.neuroniosSaida = neuroniosSaida;
        this.erro = erro;
    }

    public Calcula() {
    }

    //   δk =(tk − yk ) * f′(y_in)
    public List<Double> funcaoDeltinhaK(int [] targets){
        List<Double> deltinhas_K = new ArrayList<>();

        for (int k = 0; k < neuroniosSaida.size() ; k++) {
            Double y_in = neuroniosSaida.get(k).getSomatorio();
            Double y = neuroniosSaida.get(k).getDado();

            //Calcula o erro com base no target e na
            //saída da camada final y
            erro[k] = calculaError(targets[k] , y);

            //Computa a correção dos pesos da camada de saida
            //multiplicando o erro pela função derivada
            //aplicada em y_in
            //Armazena em deltinhas_K o termo de informação de erro
            deltinhas_K.add(erro[k] * funcaoDerivada(y_in));
        }

        return deltinhas_K;
    }

    public Double calculaError(int targets, Double y){
        return targets - y;
    }

    public Double calculaMediaErro(Double sumErro, int qtdDados){
        return  sumErro/qtdDados;
    }

    /*
               Método responsável por calcular a correção de peses da camada de saída
    */
    // ∆Wjk = α * δk * Zj
    public  Double [][] funcaoDeltaoWJK(List<Double> deltinhas_K, Double alfa){

        //Instancia o vetor que recebe a correção de pesos da última camada
        //matriz cuja dimensão é qtd de neurônios saída por qtd de neuronios
        //da camada oculta
        Double [][] deltao_JK = new Double[neuroniosSaida.size()][neuroniosProcessadores.size()];

        //Para cada neurônio de saída, pega o valor de saída
        //após aplicada a função de ativação: y
        //e o valor da soma ponderada, antes de
        //ser aplicada a função de ativação: y_in
        for (int k = 0; k < neuroniosSaida.size() ; k++) {

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
       Método responsável por computar a correção
       do Bias da camada de saída
    */
    // ∆W0k= α * δk
    public List<Double> funcaoBiasWK(List<Double> deltinhas_K, Double alfa){
        List<Double> deltao_biasWK = new ArrayList<>();
        //Para cada neuronio da camada de saida
        //multiplica o valor de alpha pelo Bias
        //correspondente, armazena em deltao_biasWK e
        //retorna o resultado
        for (int k = 0; k < neuroniosSaida.size(); k++) {
            deltao_biasWK.add(alfa * deltinhas_K.get(k));
        }
        return deltao_biasWK;
    }

    // δinj = ∑ δk * Wjk
    public Double[] funcaoDeltinhaInJ(List<Double> deltinhas_K){

        Double[] deltinha_inJ = new Double [neuroniosProcessadores.size()];

        for (int j = 0; j < neuroniosProcessadores.size() ; j++) {
            deltinha_inJ[j] = 0.0;

            for (int k = 0; k < neuroniosSaida.size(); k++) {
                deltinha_inJ[j] = deltinha_inJ[j] + (deltinhas_K.get(k) * neuroniosProcessadores.get(j).getPeso(k));
            }
        }

        return deltinha_inJ;
    }

    // δj = δinj * f′(zinj)
    public List<Double> funcaoDeltinhaJ(Double[] deltinha_inJ){
        List<Double> deltinha_J = new ArrayList<>();
        //Para cada neurônio da camada oculta
        //calcula o termo de informação de erro
        //utiliza deltinha_inJ obtido acima e
        //multiplica pela função derivada
        //aplicada na soma das entradas ponderadas
        //da camada
        for (int j = 0; j < neuroniosProcessadores.size() ; j++) {
            Double z_in = neuroniosProcessadores.get(j).getSomatorio();
            deltinha_J.add(deltinha_inJ[j] * funcaoDerivada(z_in));
        }
        return deltinha_J;
    }

    /*
        Método responsável por calcular a correção de peses da camada escondida
     */
    // ∆ vij = α * δj * Xi
    public  Double [][] funcaoDeltaoVIJ(List<Double> deltinha_J, Double alfa){

        //Instancia o vetor que recebe a correção de pesos da camada
        //matriz cuja dimensão é qtd de neurônios saída por qtd de neuronios
        //da camada oculta
        Double[][] deltao_vIJ = new Double[neuroniosProcessadores.size()][neuroniosSensores.size()]; //qtdPesos

        //Para cada neurônio da camada oculta
        //calcula deltinha_inJ para usar adiante:
        //multiplica o valor obtido no método anterior: deltinha_K
        //pelo peso correspondente e somando os resultados
        for (int j = 0; j < neuroniosProcessadores.size() ; j++) {

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
        do Bias da camada oculta
     */
    //∆v0j = α * δj
    public List<Double>  funcaoBiasVJ(List<Double> deltinha_J, Double alfa){

        List<Double> deltao_biasVJ = new ArrayList<>();
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
