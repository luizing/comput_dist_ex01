package Simulador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClusterModel {
    int n; // numero total de servidores
    int k; // numero minimo de servidores funcionando para o serviço funcionar
    double p; // probabilidade de cada servidor estar funcionando

    List<ServidorModel> serverList = new ArrayList<>();
    int upServices;
    boolean isServiceUp;

    public ClusterModel(int n, int k, double p){
        this.n = n;
        this.k = k;
        this.p = p;
        buildServer();
        isServiceUp = isUp();
    }

    private void buildServer(){
        for (int i = 0; i < n; i++){
            serverList.add(new ServidorModel(p));
        }
    }

    public boolean isUp(){
        int running = 0;
        for (ServidorModel servidor : serverList){
            if (servidor.isUp){
                running++;
            }
        }
        upServices = running;
        return (running >= k);
    }

    public void reRandom(){
        for (ServidorModel s : serverList){
            s.randomize();
        }
        isUp();
    }

    public void show(){
       if (isServiceUp) {
           System.out.println("O serviço está rodando com " + upServices + " servidores funcionando");
       }
       else {
           System.out.println("O serviço caiu com apenas " + upServices + " servidores funcionando");
       }
        int i = 1;
        for (ServidorModel s : serverList){
            if (s.isUp) {
                System.out.println("Servidor " + i + " : Ativo");
            }
            else {
                System.out.println("Servidor " + i + " : Caiu");
            }
            i++;
        }
    }

}
