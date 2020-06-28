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

@SpringBootApplication
public class Aprendizado {

	static Double alfa = 0.1;		// Alfa, critério de aprendizado
	static int epocas = 0;			// Contador de épocas
	static int epocaFinal = 20;	// Limitador de épocas
	static Double sumErro;
	static Double sumErroLogLess;

	public static void main(String[] args) {
		try {
			// Informações que devem ser alteradas de acordo com o problema
			Leitura leitura = new Leitura("dados/entrada/ep3/x_train_scaled.csv");  // Chama a classe Leitura para abrir o arquivo com os dados de entrada
			LeitorTarget lt = new LeitorTarget(TiposTarget.CSV);
			lt.csvTarget();
			List<String[]> dadosPlanilha = leitura.dadosCSV();									// Armazena os dados do arquivo em uma List de String
			int qtdDados = dadosPlanilha.size();

			// Passo 0 - Estágio de Inicialização
			Rede rede = new Rede(25,20,1);																// Instancia um objeto Rede
			rede.gerarCamadaSensorComPesos();													// e invoca os métodos responsáveis por criar as camadas da rede com
			rede.gerarCamadaOcultaComPesos();													// seus pesos definidos em cada uma das camadas e imprime as informações num arquivo
			rede.gerarCamadaSaida();
			//printaInformacoesInicias(rede.getCamadaSensor(), rede.getCamadaOculta(), rede.getCamadaSaida());

			// Passo 1 - Iterador de epocas
            // o laço será executado enquanto
            // epocas for menor do que o limite
            // definido no início do programa.

			while (epocas < epocaFinal){
				System.out.println("Epoca: "+ epocas);                                          // Imprime a epoca atual na tela
				sumErro = 0.0;
				sumErroLogLess = 0.0;

                /*
                    Para cada linha do arquivo de entrada
                    invoca os métodos das classes correspondentes
                    para realizar o Feedforward e o Backpropagation
                 */

				for (int i = 0; i < qtdDados; i++) {

				    // Invoca o método responsável por realziar a leitura
                    // dos dados do arquivo de entrada e gera o vetor target
					List<Double> dadosEntrada = leitura.gerarDadosEntrada(dadosPlanilha.get(i), lt);

					// Passo 3, 4 e 5 - Estagio feedforward
                    // gera a camada sensor com os dados de entrada
                    //
					rede.gerarDadosCamadaSensor(dadosEntrada);
					rede.gerarDadosCamadaOculta();
					rede.gerarDadosCamadaSaida();

					CamadaSensor camadaSensor= rede.getCamadaSensor();
					CamadaOculta camadaOculta= rede.getCamadaOculta();
					CamadaSaida camadaSaida = rede.getCamadaSaida();

					// Passo 6 e 7 - Estagio Backpropagation
					int target[] = lt.pegaTarget(i);
					Calcula calcula = new Calcula(camadaSensor.getNeuroniosSensores(),
							camadaOculta.getNeuroniosProcessadores(),
							camadaSaida.getNeuroniosSaida(),
							new Double[camadaSaida.getQtdNeuronios()],
							new Double[camadaSaida.getQtdNeuronios()]);

					// Calcula e armazena as correções de pesos e bias
					// Passo 6 - Camada Saida
					List<Double> deltinha_K = calcula.funcaoDeltinhaK(target);
					Double[][] deltaoWJK = calcula.funcaoDeltaoWJK(deltinha_K, alfa);
					List<Double> deltaoBiasWK = calcula.funcaoBiasWK(deltinha_K, alfa);

					//Passo 7 - Camada Oculta e Sensor
					Double[] deltinha_inJ = calcula.funcaoDeltinhaInJ(deltinha_K);
					List<Double> deltinha_J = calcula.funcaoDeltinhaJ(deltinha_inJ);
					Double [][] deltaoVIJ = calcula.funcaoDeltaoVIJ(deltinha_J, alfa);
					List<Double> deltao_biasVJ = calcula.funcaoBiasVJ(deltinha_J,alfa);

					// Passo 8 - Estagio de Atualização de Pesos
					rede.atualizaPesosCamadaSensor(deltaoVIJ,deltao_biasVJ);
					rede.atualizaPesosBiasCamadaOculta(deltaoWJK, deltaoBiasWK);

					printaErrosQuadratico(calcula.getErro(), "erroQuadratico");
					printaErros(calcula.getErro(), "erroNomal");
					somaErro(calcula.getErro());
					somaErroLogLess(calcula.getErroLogLess());

					if( i == qtdDados - 1 ){
						printaErros(calcula.calculaMediaErro(sumErro, qtdDados), "taxaErro");
						printaErros(calcula.calculaMediaErro(sumErroLogLess, qtdDados),"taxaErroLogLess");
					}
				}
				epocas++;
			}

			//printaInformacoesFinais(rede.getCamadaSensor(), rede.getCamadaOculta());

		}catch (Exception e ){
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

	public static void somaErro(Double[] erro){
		for (int k = 0; k < erro.length; k++) {
			sumErro = sumErro + Math.pow(erro[k],2);
		}
	}

	public static void somaErroLogLess(Double[] erro){
		for (int k = 0; k < erro.length; k++) {
			sumErroLogLess = sumErroLogLess + erro[k];
		}
	}

	/*
		Método responsável por imprimir as informações iniciais da Rede em um arquivo txt.
		São impressos alfa e quantidade de neurônios e pesos em cada uma das camadas em um arquivo.
		Um segundo arquivo é criado contendo os pesos iniciais de cada uma das camadas.
	 */

//	public static void printaInformacoesInicias(CamadaSensor camadaSensor, CamadaOculta camadaOculta, CamadaSaida camadaSaida){
//		try{
//			Escrita escrita = new Escrita("dados/saida/valoresIniciais.txt");
//			escrita.printaTexto("Alfa: "+ alfa);
//            escrita.printaTexto("EpocaFinal: "+ epocaFinal);
//			escrita.printaValoresInicias("\nCamada Sensor", camadaSensor);
//			escrita.printaValoresInicias("\nCamada Oculta", camadaOculta);
//			escrita.printaValoresInicias("\nCamada Saida", camadaSaida);
//
//			Escrita escrita2 = new Escrita("dados/saida/pesosIniciais.txt");
//			escrita2.printaPesos(camadaSensor.getNeuroniosSensores(), "Pesos Camada Sensor","");
//			escrita2.printaPesos(camadaOculta.getNeuroniosProcessadores(), "Pesos Camada Escondida","");
//			escrita2.printaBiasPesos(camadaSensor.getBias(), "Pesos Bias Camada Sensor", "");
//			escrita2.printaBiasPesos(camadaOculta.getBias(), "Pesos Bias Camada Oculta", "");
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//		}
//	}

	/*
		Método responsável por imprimir as informações finais em um arquivo txt.
		São impressos os pesos finais de cada neurônio, bem como o peso dos Bias.
	 */

//	public static void printaInformacoesFinais(CamadaSensor camadaSensor, CamadaOculta camadaOculta){
//		try{
//			Escrita escrita = new Escrita("dados/saida/");
//			escrita.printaPesos(camadaSensor.getNeuroniosSensores(), "","pesosFinaisSensor.txt");
//			escrita.printaPesos(camadaOculta.getNeuroniosProcessadores(), "","pesosFinaisOculta.txt");
//			escrita.printaBiasPesos(camadaSensor.getBias(), "", "pesoBiasFinaisSensor.txt");
//			escrita.printaBiasPesos(camadaOculta.getBias(), "", "pesoBiasFinaisOculta.txt");
//
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//		}
//	}
//
//		/*
//		Método responsável por imprimir os erros cometidos pela rede
//		num arquivo txt.
//	 */
//
	public static void printaErros(Double erros, String arquivo){
		try {
			Escrita escrita = new Escrita("dados/saida/"+arquivo+".txt");
			escrita.printaDouble(erros);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void printaErros(Double[] erros, String arquivo){
		try {
			Escrita escrita = new Escrita("dados/saida/"+arquivo+".txt");
			escrita.printaErros(erros);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void printaErrosQuadratico(Double[] erros, String arquivo){
		try {
			Escrita escrita = new Escrita("dados/saida/"+arquivo+".txt");
			escrita.printaErrosQuadratico(erros);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
