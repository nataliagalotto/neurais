package com.projeto.ia.redes.neurais;

import java.util.List;

public class Teste {

    public static void main(String[] args) {

        try {
            Leitura leitura = new Leitura("dados/entrada/caracteres-ruido.csv");
            Leitura leituraPesos = new Leitura("dados/saida/pesosFinais.txt");
            List<String[]> dadosPlanilha = leitura.dadosCSV();
            List<String[]> pesosPlanilha = leituraPesos.dadosCSV();
            List<Double> pesosEntrada = leitura.gerarPesosEntrada(pesosPlanilha);


            System.out.println(pesosEntrada);

            // Passo 0 - Estágio de Inicialização
            Rede rede = new Rede();
            rede.gerarCamadaSensorComPesosTeste(pesosEntrada);
            rede.gerarCamadaOcultaComPesosTeste(pesosEntrada);
//            rede.gerarCamadaOcultaComPesos();
//            rede.gerarCamadaSaida();
//
//            // Passo 1 - Iterador de epocas
//
//
//                for (int i = 0; i < dadosPlanilha.size(); i++) {
//
//                    List<Double> dadosEntrada = leitura.gerarDadosEntrada(dadosPlanilha.get(i));
//                    String letra = leitura.getTarget();
//
//                    // Passo 3, 4 e 5 - Estagio feedforward
//                    rede.gerarDadosCamadaSensor(dadosEntrada);
//                    rede.gerarDadosCamadaOculta();
//                    rede.gerarDadosCamadaSaida();
//
//                    CamadaSensor camadaSensor= rede.getCamadaSensor();
//                    CamadaOculta camadaOculta= rede.getCamadaOculta();
//                    CamadaSaida camadaSaida = rede.getCamadaSaida();
//
//                    // Passo 6 e 7 - Estagio Backpropagation
//                    int target[] = alteraTarget(letra);
//                    Calcula calcula = new Calcula(camadaSensor.getNeuroniosSensores(),
//                            camadaOculta.getNeuroniosProcessadores(),
//                            camadaSaida.getNeuroniosSaida());
//
//                    Double [][] deltaoWJK = calcula.funcaoDeltaoWJK(target, alfa);
//                    Double [][] deltaoVIJ = calcula.funcaoDeltaoVIJ(alfa);
//                    List<Double> deltao_biasWK = calcula.funcaoBiasWK(alfa);
//                    List<Double> deltao_biasVJ = calcula.funcaoBiasVJ(alfa);
//
//
//                    // Passo 8 - Estagio de Atualização de Pesos
//                    rede.atualizaPesosCamadaSensor(deltaoVIJ);
//                    rede.atualizaPesosBiasCamadaOculta(deltao_biasVJ,deltaoWJK);
//                    rede.atualizaBiasCamadaSaida(deltao_biasWK);
//                }
            } catch (Exception e ){
            for (StackTraceElement tk: e.getStackTrace()) {
                System.err.println(tk.getClassName());
                System.err.println(tk.getFileName());
                System.err.println(tk.getLineNumber());
                System.err.println(tk.getMethodName());
                System.err.println(tk.toString());
            }
            System.err.println(e.getMessage());
            System.err.println(e.getCause().getMessage());
        }
    }

    public static int [] alteraTarget(String letra){
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
        }

        return null;
    }
}
