package com.projeto.ia.redes.neurais;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Leitura {

    String caminhoArquivo;
    List<Double> dados = new ArrayList<>();
    String target;

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

    public List<Double> gerarDadosEntrada(String [] linhas){
        //System.out.println("LINHA 1 \n");
        for (int i = 0; i < linhas.length - 1 ; i++) {
            Double numero = Double.parseDouble(linhas[i].replaceAll("\\uFEFF", ""));
            dados.add(numero);
            //System.out.println("linha["+i+"]: "+ dados.get(i));
        }
        setTarget(linhas[linhas.length - 1]);
        return dados;
    }

    public void setTarget(String letra){
        this.target = letra;
        System.out.println("Letra: "+letra);
    }

    public String getTarget() {
        return target;
    }

    public FileWriter geraArquivo(){
        try{
            File file = new File(caminhoArquivo);

            //Create the file
            if (file.createNewFile())
            {
                //System.out.println("File is created!");
            } else {
                //System.out.println("File already exists.");
            }

            //Write Content
            FileWriter writer = new FileWriter(file, true);

           return writer;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void printaTexto(String texto){
        try{
            FileWriter writer = geraArquivo();
            writer.write(texto+"\n");
            writer.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void printaErros(Double[] valores){
        try{
            FileWriter writer = geraArquivo();
            writer.write("Errors\n");
            for (int i = 0; i < valores.length; i++) {
                writer.write(valores[i]+"\n");
            }

            writer.write("===============================\n");
            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void printaValoresInicias(String texto,CamadaBase camadaBase){
        try{
            FileWriter writer = geraArquivo();
            writer.write(texto+"\n");
            writer.write("qtdNeuronios: "+camadaBase.qtdNeuronios+"\n");
            writer.write("qtdPesos: "+camadaBase.qtdPesos+"\n");
            writer.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void printaPesosInicias(List<NeuronioPerceptron> neuronioPerceptrons, String texto){
        try{
            FileWriter writer = geraArquivo();
            writer.write(texto+"\n");

            for (NeuronioPerceptron neuronio : neuronioPerceptrons){
                for (Double peso : neuronio.getPesos()){
                    writer.write(peso.toString()+"\n");
                }
            }
            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
