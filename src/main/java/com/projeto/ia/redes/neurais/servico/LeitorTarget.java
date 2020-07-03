package com.projeto.ia.redes.neurais.servico;

import com.projeto.ia.redes.neurais.arquivo.Leitura;
import com.projeto.ia.redes.neurais.enums.TiposTarget;
import java.util.List;

public class LeitorTarget {

    TiposTarget tiposTarget;
    String target;
    int [] targets;

    public LeitorTarget(TiposTarget tiposTarget) {
         this.tiposTarget = tiposTarget;
    }

    public int[] pegaTarget(int i){
        switch (tiposTarget){
            case CSV:
                return pegaLinhaTarget(i);
            case LETRA:
                return letraTarget(target);
        }
        return null;
    }

    public int [] pegaLinhaTarget(int i){
        int [] n = new int [1];
        n[0] = targets[i];
        return n;
    }

    public int [] csvTarget(){
        Leitura leitura = new Leitura("dados/entrada/ep3/y_train.csv");
        List<String[]> dados = leitura.dadosCSV();
        targets = new int[dados.size()];

        for (int i = 0; i < targets.length; i++) {
            targets[i] = Integer.parseInt(dados.get(i)[0].replaceAll("\\uFEFF", ""));
        }

        return targets;
    }

    /*
		Para cada linha do arquivo de entrada é gerado um vetor target, contendo
		os valores que o representam. (Se a linha corresponder a um A o target será
		[1, 0, 0, 0, 0, 0]. Este método é responsável por alterar o vetor target
		a cada linha do arquivo de entrada.
	 */
    public int [] letraTarget(String letra){
        int target []  = new int []{0,0,0,0,0,0,0};

        switch (letra){
            case "A":
                target[0]=1;
                return target;
            case "B":
                target[1]=1;
                return target;
            case "C":
                target[2]=1;
                return target;
            case "D":
                target[3]=1;
                return target;
            case "E":
                target[4]=1;
                return target;
            case "J":
                target[5]=1;
                return target;
            case "K":
                target[6]=1;
                return target;
            default:
                int numero =  Integer.parseInt(letra);
                return new int []{numero};
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

    public TiposTarget getTiposTarget() {
        return tiposTarget;
    }

    public void setTiposTarget(TiposTarget tiposTarget) {
        this.tiposTarget = tiposTarget;
    }
}
