public class Formula {

    public static double distribuicaoBinomial(int n, int k, double p) {
        return combinacao(n, k)
                * java.lang.Math.pow(p, k)
                * java.lang.Math.pow(1 - p, n - k);
    }

    /** Probabilidade de o servico funcionar: ao menos k servidores ativos. */
    public static double probabilidadeServico(int n, int k, double p) {
        double resultado = 0;

        for (int servidoresAtivos = k; servidoresAtivos <= n; servidoresAtivos++) {
            resultado += distribuicaoBinomial(n, servidoresAtivos, p);
        }

        return resultado;
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
}
