package Simulador;

import java.util.Scanner;

public class SimuladorEstocastico {

    public static void main(String[] args) {
        int n;
        int k;
        double p;
        int rounds = 0;

        if (args.length == 3) {
            n = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);
            p = Double.parseDouble(args[2]);

        } else if (args.length == 4) {
            n = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);
            p = Double.parseDouble(args[2]);
            rounds = Integer.parseInt(args[3]);

        } else if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Digite o numero de servidores (n): ");
            n = scanner.nextInt();

            System.out.print("Digite a quantidade minima de servidores ativos (k): ");
            k = scanner.nextInt();

            System.out.print("Digite a probabilidade de um servidor estar online (p): ");
            p = scanner.nextDouble();

            System.out.print("Digite a quantidade de rodadas para testes: ");
            rounds = scanner.nextInt();

            scanner.close();
        } else {
            System.out.println("Uso: java SimuladorEstocastico <n> <k> <p>");
            return;
        }

        ClusterModel c = new ClusterModel(n, k, p);
        c.show();
        System.out.println((Benchmark.run(c,rounds)));
    }
}