import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class DistribuicaBinomial {

    public static double distribuicaoBinomial(int n, int k, double p) {
        return combinacao(n, k)
                * Math.pow(p, k)
                * Math.pow(1 - p, n - k);
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

    public static void main(String[] args) {

        // Modo interativo
        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite o numero de servidores (n): ");
            int n = scanner.nextInt();

            System.out.print("Digite a quantidade minima de servidores ativos (k): ");
            int k = scanner.nextInt();

            System.out.print("Digite a probabilidade de um servidor estar online (p): ");
            double p = scanner.nextDouble();

            double resultado = probabilidadeServico(n, k, p);

            System.out.println("Probabilidade do servico estar disponivel: " + resultado);

            System.out.print("Exibir gráfico? (y/N)");
            String s = scanner.nextLine();

            if (Objects.equals(s, "y")){
                Graph.exibir(n, k, p);
            }

            scanner.close();
            return;
        }

        // Modo por argumentos
        if (args.length == 4 ) {
            int n = Integer.parseInt(args[0]);
            int k = Integer.parseInt(args[1]);
            double p = Double.parseDouble(args[2]);
            String g = args[3];

            double resultado = probabilidadeServico(n, k, p);

            System.out.println("Probabilidade do servico estar disponivel: " + resultado);

            if (g.toUpperCase(Locale.ROOT).equals("GRAPH")){Graph.exibir(n, k, p);}

            return;
        }

        // Argumentos invalidos
        System.out.println("Uso:");
        System.out.println("  java DistribuicaBinomial");
        System.out.println("  java DistribuicaBinomial --binomial <n> <k> <p>");
    }
}