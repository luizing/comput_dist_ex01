import java.util.Locale;
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

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        System.out.print("Numero de servidores: ");
        int n = scanner.nextInt();
        System.out.print("Numero de servidores funcionando para o serviço funcionar (0 > k > n): ");
        int k = scanner.nextInt();
        System.out.print("Probabilidade do servidor está funcionando (0 > p > 1): ");
        double p = scanner.nextDouble();
        scanner.close();

        if (p > 1 || k > n || p < 0 || k < 1 ) {
            System.out.println("Valores Invalidos");
        }else {
            double probabilidade = Formula.probabilidadeServico(n, k, p);
            System.out.println("Probabilidade de o servico funcionar: " + probabilidade);
            Graph.exibir(n, k, p);
        }
    }

}
