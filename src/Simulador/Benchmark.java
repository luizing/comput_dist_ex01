package Simulador;

public class Benchmark {
    static public float run(ClusterModel c, int r){
        int upCount = 0;
        for (int i = 0; i < r; i++){
            if (c.isUp()){upCount++;};
            c.reRandom();
        }
        return (float) upCount /r;
    }

}
