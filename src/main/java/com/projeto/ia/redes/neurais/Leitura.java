package com.projeto.ia.redes.neurais;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Leitura {

    String caminhoArquivo;

    public Leitura(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public CSVReader getCSV(){
        try {
            Reader reader = new FileReader(caminhoArquivo);
            CSVReader csvReader = new CSVReader(reader);
            return csvReader;
        }catch (Exception e ){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<String[]> dados() {
        try {
            CSVReader csvReader = getCSV();
            List<String[]> list = csvReader.readAll();
            return list;
        }catch (Exception e ){
            System.out.println(e.getMessage());
            return null;
        }
    }


}
