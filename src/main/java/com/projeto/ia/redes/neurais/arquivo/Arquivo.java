package com.projeto.ia.redes.neurais.arquivo;

import java.io.File;
import java.io.FileWriter;

public class Arquivo {

    String caminhoArquivo;                      //Variável contendo o path do arquivo


    /*
       Método auxiliar responsável criar um arquivo txt
    */
    public FileWriter geraArquivo(String caminhoArquivo){
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
}
