package com.projeto.ia.redes.neurais;
import com.projeto.ia.redes.neurais.arquivo.Escrita;
import com.projeto.ia.redes.neurais.arquivo.Leitura;
import com.projeto.ia.redes.neurais.entidades.NeuronioPerceptron;
import com.projeto.ia.redes.neurais.enums.TiposTarget;
import com.projeto.ia.redes.neurais.servico.Calcula;
import com.projeto.ia.redes.neurais.servico.LeitorTarget;
import com.projeto.ia.redes.neurais.servico.Rede;
import java.util.List;

/*
    Classe responsável por executar os testes
    após treinada a rede
 */

public class Teste {

    Double sumErroQuadraticoTeste;

    public void principal(List<String[]> dadosPlanilha, LeitorTarget lt, Rede rede) {

        int qtdDados = dadosPlanilha.size();
        Leitura leitura = new Leitura();
        sumErroQuadraticoTeste = 0.0;

        //Realiza a leitura do arquivo contendo os
        //pesos da rede após o treinamento e do arquivo
        //contendo os dados de entrada para o teste
        try {
            //Para cada entrada do arquivo de dados de entrada
            //realiza a leitura e computa os resultados na rede
            for (int i = 0; i < qtdDados; i++) {

                    //Le cada linha do arquivo de entrada
                    //para utilizá-los na rede
                    List<Double> dadosEntrada = leitura.gerarDadosEntrada(dadosPlanilha.get(i),lt);

                    //Computa os valores da rede
                    //para cada camada realiza as somas ponderadas
                    //aplica a função de ativação
                    //e envia os resultados para a próxima camada
                    rede.gerarDadosCamadaSensor(dadosEntrada);
                    rede.gerarDadosCamadaOculta();
                    rede.gerarDadosCamadaSaida();

                    Calcula calcula = new Calcula();
                    int qtdDadosSaida = rede.getCamadaSaida().getQtdNeuronios();
                    int target[] = lt.pegaTarget(i);

                    for (int k = 0; k < qtdDadosSaida ; k++) {
                        Double dadoSaida = getDadoSaida(rede,k);
                        sumErroQuadratico(calcula.calculaError(target[k],dadoSaida));
                    }

                    if (i == qtdDados - 1) {
                        printaErros(calcula.calculaMediaErro(sumErroQuadraticoTeste, qtdDados), "taxaErroQuadraticoTeste");
                    }
                }
            } catch (Exception e ){
            for (StackTraceElement tk: e.getStackTrace()) {
                System.err.println(tk.getClassName());
                System.err.println(tk.getFileName());
                System.err.println(tk.getLineNumber());
                System.err.println(tk.getMethodName());
                System.err.println(tk.toString());
            }
            System.err.println(e.getMessage());
            System.err.println(e.getCause().getMessage());
        }
    }

    public void sumErroQuadratico(Double erro) {
        sumErroQuadraticoTeste = sumErroQuadraticoTeste + Math.pow(erro, 2);
    }

    public  Double getDadoSaida(Rede rede, int k){
        return rede.getCamadaSaida().getNeuroniosSaida().get(k).getDado();
    }

    public void printaErros(Double erros, String arquivo) {
        try {
            Escrita escrita = new Escrita("dados/saida/" + arquivo + ".txt");
            escrita.printaDouble(erros);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        Método responsável por imprimir os resultados finais
        com a classificação dada pela Rede
     */
    public void printFinal(List<NeuronioPerceptron> neuronioPerceptrons, int [] target, String letra){
        Escrita escrita = new Escrita("dados/saida/validaTeste.txt");
        escrita.printFinalComRound(neuronioPerceptrons,target);
    }
}
