package com.projeto.ia.redes.neurais;

import com.projeto.ia.redes.neurais.arquivo.Escrita;
import com.projeto.ia.redes.neurais.arquivo.Leitura;
import com.projeto.ia.redes.neurais.enums.TiposTarget;
import com.projeto.ia.redes.neurais.servico.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/*
	Classe responsável por realizar o aprendizado da rede.
 */


public class Aprendizado {

    Double alfa;
    Double sumErroQuadratico;
    Rede rede;

    public Aprendizado(Double alfa, Rede rede) {
        this.alfa = alfa;
        this.rede = rede;
    }

    public void geraCamadasComPesos() {
        rede.gerarCamadaSensorComPesos();                                                    // e invoca os métodos responsáveis por criar as camadas da rede com
        rede.gerarCamadaOcultaComPesos();                                                    // seus pesos definidos em cada uma das camadas e imprime as informações num arquivo
        rede.gerarCamadaSaida();
        //printaInformacoesInicias(rede.getCamadaSensor(), rede.getCamadaOculta(), rede.getCamadaSaida());
    }

    public Rede principal(List<String[]> dadosPlanilha, LeitorTarget lt) {
        try {
            int qtdDados = dadosPlanilha.size();
            Leitura leitura = new Leitura();

            sumErroQuadratico = 0.0;

            /*
                Para cada linha do arquivo de entrada
                invoca os métodos das classes correspondentes
                para realizar o Feedforward e o Backpropagation
             */
            for (int i = 0; i < qtdDados; i++) {

                // Invoca o método responsável por realizar a leitura
                // dos dados do arquivo de entrada e gera o vetor target
                List<Double> dadosEntrada = leitura.gerarDadosEntrada(dadosPlanilha.get(i), lt);

                // Passo 3, 4 e 5 - Estagio feedforward
                // gera a camada sensor com os dados de entrada
                //
                rede.gerarDadosCamadaSensor(dadosEntrada);
                rede.gerarDadosCamadaOculta();
                rede.gerarDadosCamadaSaida();

                CamadaSensor camadaSensor = rede.getCamadaSensor();
                CamadaOculta camadaOculta = rede.getCamadaOculta();
                CamadaSaida camadaSaida = rede.getCamadaSaida();

                // Passo 6 e 7 - Estagio Backpropagation
                int target[] = lt.pegaTarget(i);
                Calcula calcula = new Calcula(camadaSensor.getNeuroniosSensores(),
                        camadaOculta.getNeuroniosProcessadores(),
                        camadaSaida.getNeuroniosSaida(),
                        new Double[camadaSaida.getQtdNeuronios()]);

                // Calcula e armazena as correções de pesos e bias
                // Passo 6 - Camada Saida
                List<Double> deltinha_K = calcula.funcaoDeltinhaK(target);
                Double[][] deltaoWJK = calcula.funcaoDeltaoWJK(deltinha_K, alfa);
                List<Double> deltaoBiasWK = calcula.funcaoBiasWK(deltinha_K, alfa);

                //Passo 7 - Camada Oculta e Sensor
                Double[] deltinha_inJ = calcula.funcaoDeltinhaInJ(deltinha_K);
                List<Double> deltinha_J = calcula.funcaoDeltinhaJ(deltinha_inJ);
                Double[][] deltaoVIJ = calcula.funcaoDeltaoVIJ(deltinha_J, alfa);
                List<Double> deltao_biasVJ = calcula.funcaoBiasVJ(deltinha_J, alfa);

                // Passo 8 - Estagio de Atualização de Pesos
                rede.atualizaPesosCamadaSensor(deltaoVIJ, deltao_biasVJ);
                rede.atualizaPesosBiasCamadaOculta(deltaoWJK, deltaoBiasWK);

                sumErroQuadratico(calcula.getErro());

                if (i == qtdDados - 1) {
                    printaErros(calcula.calculaMediaErro(sumErroQuadratico, qtdDados), "taxaErroQuadratico");
                }
            }

            return rede;
            //printaInformacoesFinais(rede.getCamadaSensor(), rede.getCamadaOculta());

        } catch (Exception e) {
            for (StackTraceElement tk : e.getStackTrace()) {
                System.err.println(tk.getClassName());
                System.err.println(tk.getFileName());
                System.err.println(tk.getLineNumber());
                System.err.println(tk.getMethodName());
                System.err.println(tk.toString());
            }
            System.err.println(e.getMessage());
            System.err.println(e.getCause().getMessage());
            return null;
        }
    }

    public void sumErroQuadratico(Double[] erro) {
        for (int k = 0; k < erro.length; k++) {
            sumErroQuadratico = sumErroQuadratico + Math.pow(erro[k], 2);
        }
    }

	/*
		Método responsável por imprimir as informações finais em um arquivo txt.
		São impressos os pesos finais de cada neurônio, bem como o peso dos Bias.
	 */

    public void printaInformacoesFinais(CamadaSensor camadaSensor, CamadaOculta camadaOculta) {
        try {
            Escrita escrita = new Escrita("dados/saida/");
            escrita.printaPesos(camadaSensor.getNeuroniosSensores(), "", "pesosFinaisSensor.txt");
            escrita.printaPesos(camadaOculta.getNeuroniosProcessadores(), "", "pesosFinaisOculta.txt");
            escrita.printaBiasPesos(camadaSensor.getBias(), "", "pesoBiasFinaisSensor.txt");
            escrita.printaBiasPesos(camadaOculta.getBias(), "", "pesoBiasFinaisOculta.txt");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

	/*
		Método responsável por imprimir os erros cometidos pela rede
		num arquivo txt.
	 */

    public void printaErros(Double erros, String arquivo) {
        try {
            Escrita escrita = new Escrita("dados/saida/" + arquivo + ".txt");
            escrita.printaDouble(erros);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
