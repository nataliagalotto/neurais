package com.projeto.ia.redes.neurais;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileReader;
import java.io.Reader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Aprendizado {

	static Double alfa = 1.0;
	static CamadaSensor camadaSensor;
	static CamadaProcessador camadaProcessador;
	static CamadaSaida camadaSaida;
	static Utils utils = new Utils();

	//Area responsável por fazer o aprendizado da RedeNeural
	public static void main(String[] args) {
		try {

			Leitura leitura = new Leitura("dados/caracteres-limpo.csv");
			List<Double> dadosEntrada = leitura.gerarDadosEntrada();
			String letra = leitura.getTarget();
			int target [] = alteraTarget(letra);

			Rede rede = new Rede();
			CamadaSensor camadaSensor = rede.gerarCamadaSensor(dadosEntrada);
			CamadaProcessador camadaProcessador = rede.gerarCamadaOculta(camadaSensor);
			CamadaSaida camadaSaida = rede.gerarCamadaSaida(camadaProcessador);
			Double[][] deltao_JK  = camadaSaida.funcaoDeltao(target, alfa);
			List<Double> deltinhas_K = camadaSaida.getDeltinhas_K();
			funcaoDeltao(deltinhas_K);



			System.out.println("parou");

		}catch (Exception e ){
			System.out.println(e.toString());
			System.out.println(e.getMessage());
		}
	}

	public static void funcaoDeltao(List<Double> deltinhas_K){
		List<NeuronioPerceptron> neuroniosProcessadores = camadaProcessador.getNeuroniosProcessadores();
		List<NeuronioPerceptron> neuronioSensores = camadaSensor.getNeuroniosSensores();
		List<Double> deltinha_inJ = new ArrayList<>();
		List<Double> deltinha_J = new ArrayList<>();
		Double z_in;

		Double[][] deltao_vIJ = new Double[camadaProcessador.qtdNeuronios][camadaProcessador.qtdPesos];
		List<Double> deltinha_v0J = new ArrayList<>();

		for (int j = 0; j < camadaProcessador.qtdNeuronios ; j++) {
			for (int k = 0; k < camadaSaida.qtdNeuronios ; k++) {
				deltinha_inJ.add(deltinhas_K.get(k) * neuroniosProcessadores.get(k).getPeso(j));
			}

			z_in = neuroniosProcessadores.get(j).getSomatorio();
			deltinha_J.add(deltinha_inJ.get(j) * utils.funcaoDerivada(z_in));

			for (int i = 0; i < neuronioSensores.size() ; i++) {
				deltao_vIJ[j][i] = alfa * deltinha_J.get(j) * neuronioSensores.get(i).getDado();
			}
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
