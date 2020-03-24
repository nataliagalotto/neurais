package com.projeto.ia.redes.neurais;

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

    public Double funcaoDerivada(Double x){
        Double y = 1/(1 + (Math.exp(-x)));
        return y - Math.pow(y,2);
    }
}
