import java.util.List;
import java.util.Scanner;
import algoritmos.*; //Importando tudo do pacote "algoritimos"

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Captura do número de quadros
        System.out.print("Informe o número de quadros: ");
        int numQuadros = scanner.nextInt();

        // Captura do número total de páginas distintas
        System.out.print("Informe o número total de páginas distintas: ");
        int numPaginas = scanner.nextInt();

        // Geração de uma sequência de acessos aleatória (exemplo)
        System.out.print("Informe o tamannho da sequência aleatória: ");
        int numTamanhoSequenciaAleatoria = scanner.nextInt();
        List<Integer> paginasReferenciadas = gerarSequenciaAcessosAleatoria(numPaginas, numTamanhoSequenciaAleatoria);

        // Executando FIFO
        System.out.println("\n### FIFO ###");
        FIFO.executar(numQuadros, numPaginas, paginasReferenciadas);
        
        // Executando Segunda Chance
        System.out.println("\n### Algoritimo Segunda Chance ###");
        SegundaChance.executar(numQuadros, numPaginas,paginasReferenciadas);
        
        // Executando Relogio
        System.out.println("\n### Algoritimo Relógio ###");
        Relogio.executar(numQuadros, numPaginas, paginasReferenciadas);
        
        // Executando LRU
        System.out.println("\n### New LRU ###");
        LRU.executar(numQuadros, numPaginas, paginasReferenciadas);

        // Executando NRU
        System.out.println("\n### Algoritimo NRU ###");
        NRU.executar(numQuadros, numPaginas, paginasReferenciadas);
        
        scanner.close();
    }

    private static List<Integer> gerarSequenciaAcessosAleatoria(int numPaginas, int numTamanhoSequenciaAleatoria) {
        List<Integer> sequencia = new java.util.ArrayList<>();
        for (int i = 0; i < numTamanhoSequenciaAleatoria; i++) {
            sequencia.add((int) (Math.random() * numPaginas));
        }
        return sequencia;
    }
}