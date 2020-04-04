package com.projeto.ia.redes.neurais;
import com.opencsv.CSVReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
   Classe responsável por realizar a Leitura do arquivo contendo os dados
   de entrada. Esta classe possui métodos que manipulam os dados de entrada
   da rede, operando sobre os dados de entrada, pesos, target, etc.
 */

public class Leitura {

    String caminhoArquivo;                      //Variável contendo o path do arquivo
    List<Double> dados = new ArrayList<>();     //Estrutura responsável por receber os dados de entrada
    String target;                              //Estrutura responsável por armazenar os rótulos dos dados


    //Construtor da classe que recebe uma String
    //contendo o path do arquivo
    public Leitura(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
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
        Método que recebe a List acima e, para cada uma das
        linhas converte o valor que está em String para Double.
        Após isso armazena numa estrutura List de Doubles.
     */
    public List<Double> gerarDadosEntrada(String [] linhas){
        //System.out.println("LINHA 1 \n");
        for (int i = 0; i < linhas.length - 1 ; i++) {
            //dados.add(bias);
            Double numero = Double.parseDouble(linhas[i].replaceAll("\\uFEFF", ""));
            dados.add(numero);
            //System.out.println("linha["+i+"]: "+ dados.get(i));
        }
        setTarget(linhas[linhas.length - 1]);
        return dados;
    }

    /*
        Método utilizado pela Classe de Teste. Após treinada a rede
        os dados obtidos (pesos finais) são armazenados num arquivo
        txt. A Classe Teste recebe esses dados para gerar os pesos
        da rede "treinada"
     */
    public List<Double> gerarPesosEntrada(List<String []> pesosPlanilha){
        List<Double> pesos = new ArrayList<>();

        for (String[] peso : pesosPlanilha){
            Double numero = Double.parseDouble(peso[0].replaceAll("\\uFEFF", ""));
            pesos.add(numero);
        }
        return pesos;
    }

    /*
        Método auxiliar responsável criar um arquivo txt
     */
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

    /******************************************************/
    /*   Métodos auxiliares utilizados para gerar arquivo */
    /*   com descrição da arquitetura inicial da rede    */
    /****************************************************/

    /*
        Método que imprime o alfa definido inicialmente
        na rede
     */
    public void printaTexto(String texto){
        try{
            FileWriter writer = geraArquivo();
            writer.write(texto+"\n");
            writer.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /*
        Imprime os valores iniciais no arquivo: quantidade
        de neurônios em cada uma das camadas e quantidade
        de pesos em cada neurônio
     */
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

    /*
        Método responsável por imprimir os pesos iniciais
        da rede
     */
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

    /*
        Método auxiliar utilizado para gerar
        arquivo com os erros cometidos pela rede
     */
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


    //Getters e Setters da classe
    public void setTarget(String letra){
        this.target = letra;
        //System.out.println("Letra: "+letra);
    }

    public String getTarget() {
        return target;
    }


}
