package com.projeto.ia.redes.neurais;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.List;

public class CamadaSensor {

    List<Double> dados = new ArrayList<>();
    List<String> targets = new ArrayList<>();
    List<NeuronioPerceptron> neuroniosSensores = new ArrayList<>();

    public List<NeuronioPerceptron> gerarListaNeuronios(){
        NeuronioFactory neuronioFactory = new NeuronioFactory();
        for(Double dado : dados){
            NeuronioPerceptron neuronioPerceptron = neuronioFactory.getNeuronio();
            neuronioPerceptron.setDado(dado);
            neuronioPerceptron.getPesos();
            neuroniosSensores.add(neuronioPerceptron);
        }
        return neuroniosSensores;
    }

    public void getEntrada(){
        Leitura leitura = new Leitura("dados/caracteres-limpo.csv");
        List<String[]> lista = leitura.dados();
        //System.out.println("LINHA 1 \n");
        String [] linhas  = lista.get(0);

        for (int i = 0; i < linhas.length - 1 ; i++) {
            Double numero = Double.parseDouble(linhas[i].replaceAll("\\uFEFF", ""));
            dados.add(numero);
            //System.out.println("linha["+i+"]: "+ dados.get(i));
        }

        setTargets(linhas[linhas.length - 1]);
    }

    public void setTargets(String letra){
        targets.add(letra);
        System.out.println("Letra: "+letra);
    }

    public static Double getRandomDouble(){
        double min = -1;
        double max =  1;
        double numero = ( Math.random() * ( (max-min) + 1 ) ) + min;
        if (numero > max || numero < min){
            return 1.0;
        }
        return numero;
    }
}
