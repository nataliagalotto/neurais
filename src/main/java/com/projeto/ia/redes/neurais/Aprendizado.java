package com.projeto.ia.redes.neurais;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@SpringBootApplication
public class Aprendizado {

	static Double alfa = 30.0;
	static int epocas = 0;
	static CamadaSensor camadaSensor;
	static CamadaOculta camadaOculta;
	static CamadaSaida camadaSaida;

	//Area responsável por fazer o aprendizado da RedeNeural
	public static void main(String[] args) {
		try {
			Leitura leitura = new Leitura("dados/entrada/caracteres-limpo.csv");
			List<String[]> dadosPlanilha = leitura.dadosCSV();
//			for ( epocas = 0; epocas < alfa ; epocas++) {
//			}

			while (epocas < alfa){
				// Iterador de epocas
				for (int i = 0; i < 63; i++) {

					List<Double> dadosEntrada = leitura.gerarDadosEntrada(dadosPlanilha.get(i));
					String letra = leitura.getTarget();

					// Estagio feedforward
					Rede rede = new Rede();
					camadaSensor = rede.gerarCamadaSensor(dadosEntrada);
					camadaOculta = rede.gerarCamadaOculta(camadaSensor);
					camadaSaida = rede.gerarCamadaSaida(camadaOculta);

					if(i == 0 && epocas == 0) {
						printaInformacoesInicias();
					}

					// Estagio Backpropagation
					int target[] = alteraTarget(letra);
					camadaSaida.funcaoDeltao(target, alfa);
					camadaSaida.funcaoBias(alfa);
					camadaOculta.funcaoDeltao(camadaSaida, alfa);
					camadaOculta.funcaoBias(alfa);

					printaErros();

					// Estagio de Atualização de Pesos
					camadaOculta.atualizaPesosBias();
					camadaSensor.atualizaPesosBias();
				}

				epocas++;
			}

			printaInformacoesFinais();

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

	public static void printaInformacoesInicias(){
		try{
			Leitura leitura = new Leitura("dados/saida/valoresIniciais.txt");
			leitura.printaTexto("Alfa: "+ alfa);
			leitura.printaValoresInicias("\nCamada Sensor", camadaSensor);
			leitura.printaValoresInicias("\nCamada Oculta", camadaOculta);
			leitura.printaValoresInicias("\nCamada Saida", camadaSaida);

			Leitura leitura2 = new Leitura("dados/saida/pesosIniciais.txt");
			leitura2.printaPesosInicias(camadaSensor.neuroniosSensores, "Pesos Camada Sensor");
			leitura2.printaPesosInicias(camadaOculta.neuroniosProcessadores, "Pesos Camada Escondida");

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void printaInformacoesFinais(){
		try{
			Leitura leitura = new Leitura("dados/saida/pesosFinais.txt");
			leitura.printaPesosInicias(camadaSensor.neuroniosSensores, "Pesos Camada Sensor");
			leitura.printaPesosInicias(camadaOculta.neuroniosProcessadores, "Pesos Camada Escondida");

		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void printaErros(){
		try {
			Leitura leitura = new Leitura("dados/saida/erros.txt");
			leitura.printaErros(camadaSaida.getErro());
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
