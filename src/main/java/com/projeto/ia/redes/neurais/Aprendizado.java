package com.projeto.ia.redes.neurais;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileReader;
import java.io.Reader;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Aprendizado {

	//Area responsável por fazer o aprendizado da RedeNeural
	public static void main(String[] args) {
		try {

		Rede rede = new Rede();
		List<NeuronioPerceptron> neuronioSensoriais = rede.gerarCamadaSensor();
		List<NeuronioPerceptron> neuronioProcessadores = rede.gerarCamadaOculta(neuronioSensoriais);
		rede.gerarCamadaSaida(neuronioProcessadores);

		}catch (Exception e ){
			System.out.println(e.toString());
			System.out.println(e.getMessage());
		}
	}
}
