package com.projeto.ia.redes.neurais;
import com.projeto.ia.redes.neurais.arquivo.Escrita;
import com.projeto.ia.redes.neurais.arquivo.Leitura;
import com.projeto.ia.redes.neurais.entidades.NeuronioPerceptron;
import com.projeto.ia.redes.neurais.servico.Rede;
import org.decimal4j.util.DoubleRounder;
import java.util.List;

/*
    Classe responsável por executar os testes
    após treinada a rede
 */

public class Teste {

    public static void main(String[] args) {

        //Realiza a leitura do arquivo contendo os
        //pesos da rede após o treinamento e do arquivo
        //contendo os dados de entrada para o teste
        try {
            Leitura leitura = new Leitura("dados/entrada/problemAND.csv"); //recebe os dados de entrada

            //Armazena os dados lidos em estruturas
            //List de Strings
            List<String[]> dadosPlanilha = leitura.dadosCSV();

            //Utiliza o método da classe Leitura para "setar" os pesos
            //obtidos no treinamento na rede
            List<Double> pesosEntradaSensor = leitura.gerarPesosEntrada("dados/saida/pesosFinaisSensor.txt");
            List<Double> pesosEntradaOculta = leitura.gerarPesosEntrada("dados/saida/pesosFinaisOculta.txt");
            List<Double> pesosBiasEntradaSensor = leitura.gerarPesosEntrada("dados/saida/pesoBiasFinaisSensor.txt");
            List<Double> pesosBiasEntradaOculta = leitura.gerarPesosEntrada("dados/saida/pesoBiasFinaisOculta.txt");

            //Utiliza os métodos da classe Rede
            //para construir uma rede com os parâmetros
            //obtidos aós o treinamento
            //Note: Da forma que foram implementadas
            // as camadas neurônios, os pesos são setados
            //nos neuronios da camada sensor e
            //da camada oculta - sem perder seu significado
            Rede rede = new Rede();
            rede.gerarCamadaSensorComPesosTeste(pesosEntradaSensor,pesosBiasEntradaSensor);
            rede.gerarCamadaOcultaComPesosTeste(pesosEntradaOculta,pesosBiasEntradaOculta);
            rede.gerarCamadaSaida();

            //Para cada entrada do arquivo de dados de entrada
            //realiza a leitura e computa os resultados na rede
            for (int i = 0; i < dadosPlanilha.size(); i++) {

                    //Le cada linha do arquivo de entrada
                    //para utilizá-los na rede
                    List<Double> dadosEntrada = leitura.gerarDadosEntrada(dadosPlanilha.get(i));

                    //Constrói as variáveis correspondentes
                    //ao target de cada linha de dados de entrada
                    String letra = leitura.getTarget();
                    int target[] = alteraTarget(letra);

                    //Computa os valores da rede
                    //para cada camada realiza as somas ponderadas
                    //aplica a função de ativação
                    //e envia os resultados para a próxima camada
                    rede.gerarDadosCamadaSensor(dadosEntrada);
                    rede.gerarDadosCamadaOculta();
                    rede.gerarDadosCamadaSaida();

                    //Armazena os resultados finais da rede(camada saida)
                    //e invoca o método para imprimir os resultados
                    List<NeuronioPerceptron> neuronioPerceptrons = rede.getCamadaSaida().getNeuroniosSaida();
                    printFinal(neuronioPerceptrons, target, letra);
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

    //Cria um vetor com os dados que representam o
    //target de cada entrada
    //se o target for A o vetor será A[1 0 0 0 0 0 0]
    public static int [] alteraTarget(String letra){
        int target []  = new int []{0,0,0,0,0,0,0};

        switch (letra){
            case "A":
                target[0]=1;
                return target;
            case "B":
                target[1]=1;
                return target;
            case "C":
                target[2]=1;
                return target;
            case "D":
                target[3]=1;
                return target;
            case "E":
                target[4]=1;
                return target;
            case "J":
                target[5]=1;
                return target;
            case "K":
                target[6]=1;
                return target;
            default:
                int numero =  Integer.parseInt(letra);
                return new int []{numero};
        }
    }

    /*
        Método responsável por imprimir os resultados finais
        com a classificação dada pela Rede
     */
    public static void printFinal(List<NeuronioPerceptron> neuronioPerceptrons, int [] target, String letra){
        Escrita escrita = new Escrita("dados/saida/validaTeste.txt");
        escrita.printFinalComRound(neuronioPerceptrons,target,letra);

        Escrita escrita2 = new Escrita("dados/saida/validaTesteSemArredondar.txt");
        escrita2.printFinalSemRound(neuronioPerceptrons,target,letra);
    }
}
