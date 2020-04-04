package com.projeto.ia.redes.neurais;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

/*
	Classe responsável por realizar o aprendizado da rede.
 */

@SpringBootApplication
public class Aprendizado {

	static Double alfa = 10.0;	// Alfa, critério de aprendizado
	static int epocas = 0;			// Contador de épocas

	public static void main(String[] args) {
		try {
			Leitura leitura = new Leitura("dados/entrada/caracteres-limpo.csv");
			List<String[]> dadosPlanilha = leitura.dadosCSV();

			// Passo 0 - Estágio de Inicialização
			Rede rede = new Rede();
			rede.gerarCamadaSensorComPesos();
			rede.gerarCamadaOcultaComPesos();
			rede.gerarCamadaSaida();
			printaInformacoesInicias(rede.getCamadaSensor(), rede.getCamadaOculta(), rede.getCamadaSaida());

			// Passo 1 - Iterador de epocas
			while (epocas < alfa){
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

	public static void printaInformacoesInicias(CamadaSensor camadaSensor, CamadaOculta camadaOculta, CamadaSaida camadaSaida){
		try{
			Leitura leitura = new Leitura("dados/saida/valoresIniciais.txt");
			leitura.printaTexto("Alfa: "+ alfa);
			leitura.printaValoresInicias("\nCamada Sensor", camadaSensor);
			leitura.printaValoresInicias("\nCamada Oculta", camadaOculta);
			leitura.printaValoresInicias("\nCamada Saida", camadaSaida);

			Leitura leitura2 = new Leitura("dados/saida/pesosIniciais.txt");
			leitura2.printaPesos(camadaSensor.neuroniosSensores, "Pesos Camada Sensor","");
			leitura2.printaPesos(camadaOculta.neuroniosProcessadores, "Pesos Camada Escondida","");
			leitura2.printaBiasPesos(camadaSensor.bias, "Pesos Bias Camada Sensor", "");
			leitura2.printaBiasPesos(camadaOculta.bias, "Pesos Bias Camada Oculta", "");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void printaInformacoesFinais(CamadaSensor camadaSensor, CamadaOculta camadaOculta){
		try{
			Leitura leitura = new Leitura("dados/saida/");
			leitura.printaPesos(camadaSensor.neuroniosSensores, "","pesosFinaisSensor.txt");
			leitura.printaPesos(camadaOculta.neuroniosProcessadores, "","pesosFinaisOculta.txt");
			leitura.printaBiasPesos(camadaSensor.bias, "", "pesoBiasFinaisSensor.txt");
			leitura.printaBiasPesos(camadaOculta.bias, "", "pesoBiasFinaisOculta.txt");

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void printaErros(Double[] erros){
		try {
			Leitura leitura = new Leitura("dados/saida/erros.txt");
			leitura.printaErros(erros);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

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
