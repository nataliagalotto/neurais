package com.projeto.ia.redes.neurais;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileReader;
import java.io.Reader;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Aprendizado {

	Double alfa;
	List<String> dados;
	List<String> targets;

	public static void main(String[] args) {
		try {

		Rede rede = new Rede();
		rede.gerarCamadaOculta();

		}catch (Exception e ){
			System.out.println(e.toString());
			System.out.println(e.getMessage());
		}
	}




}
