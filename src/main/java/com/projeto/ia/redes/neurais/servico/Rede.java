package com.projeto.ia.redes.neurais.servico;
import java.util.List;
/*
    Classe responsável por construir a rede
    instanciando as classes com seus pesos
    e quantidade de neurônios
 */


//Responsavel por gerar as Camadas e administrar elas
public class Rede {

    CamadaSensor camadaSensor;
    CamadaOculta camadaOculta;
    CamadaSaida camadaSaida;

    public Rede(int neuroniosSensor,int neuroniosOculta,int neuroniosSaida) {
        this.camadaSensor = new CamadaSensor(neuroniosSensor,neuroniosOculta);
        this.camadaOculta = new CamadaOculta(neuroniosOculta,neuroniosSaida);
        this.camadaSaida = new CamadaSaida(neuroniosSaida);
    }

    //Responsavel por gerar os neuronios Camada Sensor com pesos
    //aleatórios
    public  void gerarCamadaSensorComPesos(){
        camadaSensor.gerarListaNeuroniosComPesos();
    }

    //Responsavel por gerar os neuronios Camada Sensor com pesos
    //obtidos após o treinamento
    public  void gerarCamadaSensorComPesosTeste(List<Double> pesosEntrada,List<Double> pesosBiasEntrada){
        camadaSensor.gerarListaNeuroniosComPesosTeste(pesosEntrada);
        camadaSensor.gerarBiasComPesosTeste(pesosBiasEntrada);
    }

    //Responsavel por gerar e atualizar os dados da Camada saida
    public  void gerarDadosCamadaSensor(List<Double> dadosEntrada){
        camadaSensor.setDadosEntrada(dadosEntrada);
        camadaSensor.atualizaDadosNeuronios();
    }

    //Responsavel por atualizar os pesos da camada sensor
    public  CamadaSensor atualizaPesosCamadaSensor(Double [][] deltao_vIJ, List<Double> deltao_biasVJ){
        camadaSensor.atualizaBiasVJ(deltao_biasVJ);
        camadaSensor.atualizaPesosVJ(deltao_vIJ);
        return camadaSensor;
    }

    //Responsavel por gerar os neuronios Camada Oculta com pesosInicias
    public CamadaOculta gerarCamadaOcultaComPesos(){
        camadaOculta.gerarListaNeuroniosComPesos();
        return camadaOculta;
    }

    //Responsavel por gerar os neuronios Camada Oculta com pesosFinais
    //obtidos após o treinamento
    public void gerarCamadaOcultaComPesosTeste(List<Double> pesosEntrada, List<Double> pesosBiasEntrada){
        camadaOculta.gerarListaNeuroniosComPesosTeste(pesosEntrada);
        camadaOculta.gerarBiasComPesosTeste(pesosBiasEntrada);
    }

    //Responsavel por gerar a Camada oculta
    public void gerarDadosCamadaOculta(){
        camadaOculta.setNeuroniosSensores(camadaSensor.getNeuroniosSensores());
        camadaOculta.atualizaDadosNeuronios(camadaSensor.getBias());
    }

    //Responsável por atualizar os pesos do Bias da camada oculta
    public void atualizaPesosBiasCamadaOculta(Double [][] deltao_WJK, List<Double> deltao_biasWK){
        camadaOculta.atualizaBiasWK(deltao_biasWK);
        camadaOculta.atualizaPesosWK(deltao_WJK);
    }

    //Responsavel por gerar os neuronios Camada Saida
    public void gerarCamadaSaida(){
        camadaSaida.gerarListaNeuroniosSaida();
    }

    //Responsavel por atualizar o dados da Camada saida
    public void gerarDadosCamadaSaida(){
        camadaSaida.setNeuroniosProcessadores(camadaOculta.getNeuroniosProcessadores());
        camadaSaida.atualizaDadosNeuronios(camadaOculta.getBias());
    }

    //Getters da classe
    public CamadaSensor getCamadaSensor() {
        return camadaSensor;
    }

    public CamadaOculta getCamadaOculta() {
        return camadaOculta;
    }

    public CamadaSaida getCamadaSaida() {
        return camadaSaida;
    }
}
