package com.projeto.ia.redes.neurais;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileReader;
import java.io.Reader;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class NeuraisApplication {

	public static void main(String[] args) {
		try {
			Neuronio neuronio = new Neuronio();

		}catch (Exception e ){
			System.out.println(e.getMessage());
		}
	}


}
