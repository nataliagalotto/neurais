package com.projeto.ia.redes.neurais.arquivo;

import com.projeto.ia.redes.neurais.entidades.Bias;
import com.projeto.ia.redes.neurais.servico.CamadaBase;
import com.projeto.ia.redes.neurais.entidades.NeuronioPerceptron;

import java.io.FileWriter;
import java.util.List;

public class Escrita extends Arquivo{

    //Construtor da classe que recebe uma String
    //contendo o path do arquivo
    public Escrita(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
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
            FileWriter writer = geraArquivo(caminhoArquivo);
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
    public void printaValoresInicias(String texto, CamadaBase camadaBase){
        try{
            FileWriter writer = geraArquivo(caminhoArquivo);
            writer.write(texto+"\n");
            writer.write("qtdNeuronios: "+camadaBase.getQtdNeuronios()+"\n");
            writer.write("qtdPesos: "+camadaBase.getQtdPesos()+"\n");
            writer.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /*
        Método responsável por imprimir os pesos iniciais
        da rede
     */

    public void printaPesos(List<NeuronioPerceptron> neuronioPerceptrons, String texto, String nomeArquivo){
        try{
            FileWriter writer = geraArquivo(caminhoArquivo+nomeArquivo);
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

    public void printaBiasPesos(Bias bias, String texto, String nomeArquivo){
        try{
            FileWriter writer = geraArquivo(caminhoArquivo+nomeArquivo);
            writer.write(texto+"\n");

            for (Double peso : bias.getPesos()){
                writer.write(peso.toString()+"\n");
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
            FileWriter writer = geraArquivo(caminhoArquivo);
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
}
