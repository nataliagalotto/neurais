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
			camadaSaida.funcaoDeltinha(target);

			System.out.println("parou");

		}catch (Exception e ){
			System.out.println(e.toString());
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
