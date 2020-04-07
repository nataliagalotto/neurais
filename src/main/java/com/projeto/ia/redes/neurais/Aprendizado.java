package com.projeto.ia.redes.neurais;
import com.projeto.ia.redes.neurais.arquivo.Escrita;
import com.projeto.ia.redes.neurais.arquivo.Leitura;
import com.projeto.ia.redes.neurais.servico.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

/*
	Classe responsável por realizar o aprendizado da rede.
 */

@SpringBootApplication
public class Aprendizado {

	static Double alfa = 0.5;		// Alfa, critério de aprendizado
	static int epocas = 0;			// Contador de épocas
	static int epocaFinal = 2000;	// Limitador de épocas

	public static void main(String[] args) {
		try {
			Leitura leitura = new Leitura("dados/entrada/caracteres-limpo.csv");  // Chama a classe Leitura para abrir o arquivo com os dados de entrada
			List<String[]> dadosPlanilha = leitura.dadosCSV();									// Armazena os dados do arquivo em uma List de String

			// Passo 0 - Estágio de Inicialização
			Rede rede = new Rede();																// Instancia um objeto Rede
			rede.gerarCamadaSensorComPesos();													// e invoca os métodos responsáveis por criar as camadas da rede com
			rede.gerarCamadaOcultaComPesos();													// seus pesos definidos em cada uma das camadas e imprime as informações num arquivo
			rede.gerarCamadaSaida();
			printaInformacoesInicias(rede.getCamadaSensor(), rede.getCamadaOculta(), rede.getCamadaSaida());

			// Passo 1 - Iterador de epocas
			while (epocas < epocaFinal){
				System.out.println("Epoca: "+ epocas);

				for (int i = 0; i < dadosPlanilha.size(); i++) {

					List<Double> dadosEntrada = leitura.gerarDadosEntrada(dadosPlanilha.get(i));
					String letra = leitura.getTarget();

					// Passo 3, 4 e 5 - Estagio feedforward
					rede.gerarDadosCamadaSensor(dadosEntrada);
					rede.gerarDadosCamadaOculta();
					rede.gerarDadosCamadaSaida();

					CamadaSensor camadaSensor= rede.getCamadaSensor();
					CamadaOculta camadaOculta= rede.getCamadaOculta();
					CamadaSaida camadaSaida = rede.getCamadaSaida();

					// Passo 6 e 7 - Estagio Backpropagation
					int target[] = alteraTarget(letra);
					Calcula calcula = new Calcula(camadaSensor.getNeuroniosSensores(),
							camadaOculta.getNeuroniosProcessadores(),
							camadaSaida.getNeuroniosSaida());

					Double [][] deltaoWJK = calcula.funcaoDeltaoWJK(target, alfa);
					Double [][] deltaoVIJ = calcula.funcaoDeltaoVIJ(alfa);
					List<Double> deltao_biasWK = calcula.funcaoBiasWK(alfa);
					List<Double> deltao_biasVJ = calcula.funcaoBiasVJ(alfa);

					printaErros(calcula.getErro());

					// Passo 8 - Estagio de Atualização de Pesos
					rede.atualizaPesosCamadaSensor(deltaoVIJ,deltao_biasVJ);
					rede.atualizaPesosBiasCamadaOculta(deltaoWJK, deltao_biasWK);
				}
				epocas++;
			}

			printaInformacoesFinais(rede.getCamadaSensor(), rede.getCamadaOculta());

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

	/*
		Método responsável por imprimir as informações iniciais da Rede em um arquivo txt.
		São impressos alfa e quantidade de neurônios e pesos em cada uma das camadas em um arquivo.
		Um segundo arquivo é criado contendo os pesos iniciais de cada uma das camadas.
	 */

	public static void printaInformacoesInicias(CamadaSensor camadaSensor, CamadaOculta camadaOculta, CamadaSaida camadaSaida){
		try{
			Escrita escrita = new Escrita("dados/saida/valoresIniciais.txt");
			escrita.printaTexto("Alfa: "+ alfa);
			escrita.printaValoresInicias("\nCamada Sensor", camadaSensor);
			escrita.printaValoresInicias("\nCamada Oculta", camadaOculta);
			escrita.printaValoresInicias("\nCamada Saida", camadaSaida);

			Escrita escrita2 = new Escrita("dados/saida/pesosIniciais.txt");
			escrita2.printaPesos(camadaSensor.getNeuroniosSensores(), "Pesos Camada Sensor","");
			escrita2.printaPesos(camadaOculta.getNeuroniosProcessadores(), "Pesos Camada Escondida","");
			escrita2.printaBiasPesos(camadaSensor.getBias(), "Pesos Bias Camada Sensor", "");
			escrita2.printaBiasPesos(camadaOculta.getBias(), "Pesos Bias Camada Oculta", "");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	/*
		Método responsável por imprimir as informações finais em um arquivo txt.
		São impressos os pesos finais de cada neurônio, bem como o peso dos Bias.
	 */

	public static void printaInformacoesFinais(CamadaSensor camadaSensor, CamadaOculta camadaOculta){
		try{
			Escrita escrita = new Escrita("dados/saida/");
			escrita.printaPesos(camadaSensor.getNeuroniosSensores(), "","pesosFinaisSensor.txt");
			escrita.printaPesos(camadaOculta.getNeuroniosProcessadores(), "","pesosFinaisOculta.txt");
			escrita.printaBiasPesos(camadaSensor.getBias(), "", "pesoBiasFinaisSensor.txt");
			escrita.printaBiasPesos(camadaOculta.getBias(), "", "pesoBiasFinaisOculta.txt");

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	/*
		Método responsável por imprimir os erros cometidos pela rede
		num arquivo txt.
	 */

	public static void printaErros(Double[] erros){
		try {
			Escrita escrita = new Escrita("dados/saida/erros.txt");
			escrita.printaErros(erros);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	/*
		Para cada linha do arquivo de entrada é gerado um vetor target, contendo
		os valores que o representam. (Se a linha corresponder a um A o target será
		[1, 0, 0, 0, 0, 0]. Este método é responsável por alterar o vetor target
		a cada linha do arquivo de entrada.
	 */

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
		}

		return null;
	}
}
