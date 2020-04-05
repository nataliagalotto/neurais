package com.projeto.ia.redes.neurais;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public Double getRandomDouble(){
        double min = -1;
        double max =  1;
        double numero = ( Math.random() * ( (max-min) + 1 ) ) + min;
        if (numero > max || numero < min){
            return 1.0;
        }
        return numero;
    }

    public List<Double> listaRandomDouble(int qtd){
        List<Double> numeros = new ArrayList<>();

        for (int i = 0; i < qtd ; i++) {
            numeros.add(getRandomDouble());
        }
        return numeros;
    }

}
