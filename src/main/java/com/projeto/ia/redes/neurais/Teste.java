package com.projeto.ia.redes.neurais;

import java.util.List;

public class Teste {

    static Double alfa = 10.0;

    public static void main(String[] args) {
        try {

            double peso1 = getRandomDouble();
            double dado1 = -1.0;
            Neuronio neuronio1 = new Neuronio(peso1, dado1);

            double peso2 = getRandomDouble();
            double dado2 = -1.0;
            Neuronio neuronio2 = new Neuronio(peso2, dado2);

            double bias  = 1.0;
            double retorno;
            double target = 0.0;

            double somatorio = ((neuronio1.peso * neuronio1.dado
                    + neuronio2.peso * neuronio2.dado)
                    + bias);

            if ( somatorio >= 0 ){
                retorno = 1;
            } else {
                retorno = 0;
            }

            if(retorno != target ){
                neuronio1.peso = neuronio1.peso + (alfa * target * neuronio1.dado);
                neuronio2.peso = neuronio2.peso + (alfa * target * neuronio2.dado);
            }

        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
    }

    public static double getRandomDouble(){
        double min = -1;
        double max =  1;
        return ( Math.random() * ( (max-min) + 1 ) ) + min;
    }
}
