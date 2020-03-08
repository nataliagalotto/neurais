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


		}catch (Exception e ){
			System.out.println(e.getMessage());
		}
	}

	public void getEntrada(){
		Leitura leitura = new Leitura();
		List<String[]> lista = leitura.dados();
		for (String[] linhas : lista){
			for (int i = 0; i < linhas.length; i++) {
				if (i == 2){
					targets.add(linhas[i]);
				}else{
					dados.add(linhas[i]);
				}
			}
		}

	}


}
