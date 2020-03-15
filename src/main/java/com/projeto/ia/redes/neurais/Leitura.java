package com.projeto.ia.redes.neurais;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Leitura {

    String caminhoArquivo;
    List<Double> dados = new ArrayList<>();
    List<String> targets = new ArrayList<>();

    public Leitura(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public List<String[]> dadosCSV(){
        try {
            Reader reader = new FileReader(caminhoArquivo);
            CSVReader csvReader = new CSVReader(reader);
            return csvReader.readAll();
        }catch (Exception e ){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Double> gerarDadosEntrada(){
        List<String[]> lista = dadosCSV();
        //System.out.println("LINHA 1 \n");
        String [] linhas  = lista.get(0);
        for (int i = 0; i < linhas.length - 1 ; i++) {
            Double numero = Double.parseDouble(linhas[i].replaceAll("\\uFEFF", ""));
            dados.add(numero);
            //System.out.println("linha["+i+"]: "+ dados.get(i));
        }
        setTargets(linhas[linhas.length - 1]);
        return dados;
    }

    public void setTargets(String letra){
        targets.add(letra);
        System.out.println("Letra: "+letra);
    }


}
