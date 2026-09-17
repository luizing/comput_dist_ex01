import Formula.DistribuicaBinomial;
import Simulador.SimuladorEstocastico;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // O arquivo inputs possui uma sequencia de testes com numero de processadores, processadores minimos e probabilidades pré definidos.
//        Path arquivo = Path.of("inputs");
//
//        try (BufferedReader leitor = Files.newBufferedReader(arquivo)) {
//            String linha;
//
//            while ((linha = leitor.readLine()) != null) {
//
//            }
//        } catch (IOException e) {
//            System.err.println("Não foi possível ler o arquivo inputs: " + e.getMessage());
//        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o numero de servidores (n): ");
        String n = scanner.nextLine();

        System.out.print("Digite a quantidade minima de servidores ativos (k): ");
        String k = scanner.nextLine();

        System.out.print("Digite a probabilidade de um servidor estar online (p): ");
        String p = scanner.nextLine();

        System.out.println("Digite a quantidade de rodadas que o simulador deve ser testado: ");
        String r = scanner.nextLine();


        System.out.println("Probabilidade do serviço estar disponivel via formula de Distribuição Binomial: ");
        DistribuicaBinomial.main(new String[]{n, k, p, "GRAPH"});

        System.out.println("Razão entre vezes que o serviço estava dispinvel e quantidade de testes: ");
        SimuladorEstocastico.main(new String[]{n, k, p, r});

    }

}
