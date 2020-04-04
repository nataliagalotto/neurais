package com.projeto.ia.redes.neurais;
import java.util.ArrayList;
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

    public Rede() {
        this.camadaSensor = new CamadaSensor();
        this.camadaOculta = new CamadaOculta();
        this.camadaSaida = new CamadaSaida();
    }

    //Responsavel por gerar os neuronios Camada Sensor com pesos
    //aleatórios
    public  void gerarCamadaSensorComPesos(){
        camadaSensor.gerarListaNeuroniosComPesos();
    }

    //Responsavel por gerar os neuronios Camada Sensor com pesos
    //obtidos após o treinamento
    public  void gerarCamadaSensorComPesosTeste(List<Double> pesosEntrada){
        camadaSensor.gerarListaNeuroniosComPesosTeste(pesosEntrada);
    }

    //Responsavel por gerar e atualizar os dados da Camada saida
    public  void gerarDadosCamadaSensor(List<Double> dadosEntrada){
        camadaSensor.setDadosEntrada(dadosEntrada);
        camadaSensor.atualizaDadosNeuronios();
    }

    //Responsavel por atualizar os pesos da camada sensor
    public  CamadaSensor atualizaPesosCamadaSensor(Double [][] deltao_vIJ){
        camadaSensor.atualizaPesosVJ(deltao_vIJ);
        return camadaSensor;
    }

    //Responsavel por gerar os neuronios Camada Oculta com pesos
    //aleatórios
    public CamadaOculta gerarCamadaOcultaComPesos(){
        camadaOculta.gerarListaNeuroniosComPesos();
        return camadaOculta;
    }

    //Responsavel por gerar os neuronios Camada Oculta com pesosFinais
    //obtidos após o treinamento
    public void gerarCamadaOcultaComPesosTeste(List<Double> pesosEntrada){
        camadaOculta.gerarListaNeuroniosComPesosTeste(pesosEntrada);
    }

    //Responsavel por gerar a Camada oculta
    public void gerarDadosCamadaOculta(){
        camadaOculta.setNeuroniosSensores(camadaSensor.getNeuroniosSensores());
        camadaOculta.atualizaDadosNeuronios();
    }

    //Responsável por atualizar os pesos do Bias da camada oculta
    public void atualizaPesosBiasCamadaOculta(List<Double> deltao_biasVJ, Double [][] deltao_WJK){
        camadaOculta.atualizaBiasVJ(deltao_biasVJ);
        camadaOculta.atualizaPesosWK(deltao_WJK);
    }

    //Responsavel por gerar os neuronios Camada Saida
    public void gerarCamadaSaida(){
        camadaSaida.gerarListaNeuroniosSaida();
    }

    //Responsavel por atualizar o dados da Camada saida
    public void gerarDadosCamadaSaida(){
        camadaSaida.setNeuroniosProcessadores(camadaOculta.getNeuroniosProcessadores());
        camadaSaida.atualizaDadosNeuronios();
    }

    public void atualizaBiasCamadaSaida(List<Double> deltao_biasWK){
        camadaSaida.atualizaBiasWK(deltao_biasWK);
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
