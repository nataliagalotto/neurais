package com.projeto.ia.redes.neurais;

import com.projeto.ia.redes.neurais.arquivo.Leitura;
import com.projeto.ia.redes.neurais.enums.TiposTarget;
import com.projeto.ia.redes.neurais.servico.LeitorTarget;
import com.projeto.ia.redes.neurais.servico.Rede;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/*
	Classe responsável por paralelizar o Aprendizado do Teste
 */
@SpringBootApplication
public class MLP {

    static Double alfa = 0.5;		// Alfa, critério de aprendizado
    static int epocas = 0;			// Contador de épocas
    static int epocaFinal = 5000;	// Limitador de épocas
    static String arquivoAprendizado = "dados/entrada/ep3/x_train_ant.csv";
    static String arquivoTargetAprendizado="dados/entrada/ep3/y_train_ant.csv";
    static String arquivoTeste = "dados/entrada/ep3/x_test_ant.csv";
    static String arquivoTargetTeste = "dados/entrada/ep3/y_test_ant.csv";

    public static void main(String[] args) {

        Rede rede = new Rede(25,40,1);

        List<String[]> dadosAprendizado = gerarDados(arquivoAprendizado);
        LeitorTarget ltAprendizado = gerarDadosTarget(arquivoTargetAprendizado);
        List<String[]> dadosTeste = gerarDados(arquivoTeste);
        LeitorTarget ltTeste = gerarDadosTarget(arquivoTargetTeste);

        // Passo 0 - Estágio de Inicialização
        Aprendizado aprendizado = new Aprendizado(alfa,rede);
        aprendizado.geraCamadasComPesos();

        Teste teste = new Teste();

        //Passo 1 - Iterador de epocas
        // o laço será executado enquanto
        // epocas for menor do que o limite
        // definido no início do programa.
        while (epocas < epocaFinal){
            System.out.println("Epoca: "+ epocas);                                          // Imprime a epoca atual na tela

            rede = aprendizado.principal(dadosAprendizado, ltAprendizado);
            teste.principal(dadosTeste, ltTeste, rede);
            epocas++;
        }
    }

    public static List<String[]> gerarDados(String arquivo) {
        // Informações que devem ser alteradas de acordo com o problema
        Leitura leitura = new Leitura(arquivo);  // Chama a classe Leitura para abrir o arquivo com os dados de entrada
        List<String[]> dadosPlanilha = leitura.dadosCSV();                                    // Armazena os dados do arquivo em uma List de String

        return dadosPlanilha;
    }

    public static LeitorTarget gerarDadosTarget(String arquivo) {
        LeitorTarget lt = new LeitorTarget(TiposTarget.CSV);
        lt.csvTarget(arquivo);
        return lt;
    }
}
