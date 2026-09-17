package Simulador;

import java.util.concurrent.ThreadLocalRandom;

public class ServidorModel {
    double p; // propabilidade de estar funcionando
    boolean isUp;

    public ServidorModel(double p){
        this.p = p;
        randomize();
    }

    public void randomize(){
        int r = ThreadLocalRandom.current().nextInt(0, 101);
        this.isUp = p * 100 > r;
    }

}
