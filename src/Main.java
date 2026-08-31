public class Main{

    public static double distribuicaoBinomial(int n, int k, double p) {
        return combinacao(n, k)
                * Math.pow(p, k)
                * Math.pow(1 - p, n - k);
    }

    public static long combinacao(int n, int k) {
        return fatorial(n) / (fatorial(k) * fatorial(n - k));
    }

    public static long fatorial(int n) {
        long resultado = 1;

        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }

        return resultado;
    }

    static void main() {
        System.out.println(distribuicaoBinomial(10,3,0.8));
        System.out.println(distribuicaoBinomial(10,8,0.8));
        System.out.println(distribuicaoBinomial(5,3,0.9));
        System.out.println(distribuicaoBinomial(10,9,0.1));
    }
}