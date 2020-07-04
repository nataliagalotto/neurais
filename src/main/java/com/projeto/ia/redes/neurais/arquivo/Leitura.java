package com.projeto.ia.redes.neurais.arquivo;
import com.opencsv.CSVReader;
import com.projeto.ia.redes.neurais.enums.TiposTarget;
import com.projeto.ia.redes.neurais.servico.LeitorTarget;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
   Classe responsável por realizar a Leitura do arquivo contendo os dados
   de entrada. Esta classe possui métodos que manipulam os dados de entrada
   da rede, operando sobre os dados de entrada, pesos, target, etc.
 */

public class Leitura extends Arquivo{

    //Construtor da classe que recebe uma String
    //contendo o path do arquivo
    public Leitura(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public Leitura() {

    }

    /*
            Método responsável por realizar a leitura do arquivo CSV
            contendo os dados de entrada e armazená-los numa List de
            Strings, onde cada String armazena uma linha do arquivo
            CSV
         */
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

    /*
        Método responsável por realizar a leitura do arquivo CSV
        contendo os dados de entrada e armazená-los numa List de
        Strings, onde cada String armazena uma linha do arquivo
        CSV
     */
    public List<String[]> dadosCSV(String caminhoArquivo){
        try {
            Reader reader = new FileReader(caminhoArquivo);
            CSVReader csvReader = new CSVReader(reader);
            return csvReader.readAll();
        }catch (Exception e ){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public List<Double> gerarDadosEntrada(String [] linhas, LeitorTarget leitorTarget){
        switch (leitorTarget.getTiposTarget()){
            case LETRA:
                return gerarDadosEntradaLetra(linhas, leitorTarget);
            case CSV:
                return gerarDadosEntradaCSV(linhas);
        }
        return null;
    }

    /*
        Método que recebe a List acima e, para cada uma das
        linhas converte o valor que está em String para Double.
        Após isso armazena numa estrutura List de Doubles.
     */
    public List<Double> gerarDadosEntradaLetra(String [] linhas, LeitorTarget leitorTarget){
        List<Double> dados = new ArrayList<>();     //Estrutura responsável por receber os dados de entrada
        //System.out.println("LINHA 1 \n");
        for (int i = 0; i < linhas.length - 1 ; i++) {
            //dados.add(bias);
            Double numero = Double.parseDouble(linhas[i].replaceAll("\\uFEFF", ""));
            dados.add(numero);
            //System.out.println("linha["+i+"]: "+ dados.get(i));
        }
        leitorTarget.setTarget(linhas[linhas.length - 1]);
        return dados;
    }

    /*
       Método que recebe a List acima e, para cada uma das
       linhas converte o valor que está em String para Double.
       Após isso armazena numa estrutura List de Doubles.
    */
    public List<Double> gerarDadosEntradaCSV(String [] linhas){
        List<Double> dados = new ArrayList<>();     //Estrutura responsável por receber os dados de entrada
        //System.out.println("LINHA 1 \n");
        for (int i = 0; i < linhas.length ; i++) {
            //dados.add(bias);
            Double numero = Double.parseDouble(linhas[i].replaceAll("\\uFEFF", ""));
            dados.add(numero);
            //System.out.println("linha["+i+"]: "+ dados.get(i));
        }
        return dados;
    }

    /*
        Método utilizado pela Classe de Teste. Após treinada a rede
        os dados obtidos (pesos finais) são armazenados num arquivo
        txt. A Classe Teste recebe esses dados para gerar os pesos
        da rede "treinada"
     */
    public List<Double> gerarPesosEntrada(String caminhoArquivo){

        List<String[]> pesosPlanilha = dadosCSV(caminhoArquivo);

        List<Double> pesos = new ArrayList<>();

        for (String[] peso : pesosPlanilha){
            if(!peso[0].equals("")){
                Double numero = Double.parseDouble(peso[0].replaceAll("\\uFEFF", ""));
                pesos.add(numero);
            }
        }
        return pesos;
    }



    public void deletarDiretorioSaida(){
        File folder = new File("dados/saida/");
        if (folder.isDirectory()) {
            File[] sun = folder.listFiles();
            for (File toDelete : sun) {
                toDelete.delete();
            }
        }
    }
}
