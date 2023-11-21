import java.util.List;
import java.util.Random;
import java.util.Scanner;
import algoritmos.*; //Importando tudo do pacote "algoritimos"

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Número de quadros: ");
        int numQuadros = scanner.nextInt();

        System.out.print("Número total de páginas distintas: ");
        int numPaginas = scanner.nextInt();

        System.out.print("Tamanho da sequência aleatória: ");
        int numTamanhoSequenciaAleatoria = scanner.nextInt();
        List<Integer> paginasReferenciadas = gerarSequenciaAcessosAleatoria(numPaginas, numTamanhoSequenciaAleatoria);
        List<Integer> paginasReferenciadasNormal = gerarSequenciaAcessosNormal(numPaginas, numTamanhoSequenciaAleatoria);

        // Executando FIFO
        System.out.println("\n# FIFO #");
        FIFO.executar(numQuadros, paginasReferenciadas);

        System.out.println("\n# FIFO dados normalizados #");
        FIFO.executar(numQuadros, paginasReferenciadasNormal);
        
        // Executando Segunda Chance
        System.out.println("\n# Segunda Chance #");
        SegundaChance.executar(numQuadros, paginasReferenciadas); 
        
        System.out.println("\n# Segunda Chance dados normalizados #");
        SegundaChance.executar(numQuadros, paginasReferenciadasNormal); 

        // Executando Relogio
        System.out.println("\n# Relógio #");
        Relogio.executar(numQuadros, paginasReferenciadas);
        
        System.out.println("\n# Relógio dados normalizados #");
        Relogio.executar(numQuadros, paginasReferenciadasNormal);

        // Executando LRU
        System.out.println("\n# LRU #");
        LRU.executar(numQuadros, paginasReferenciadas);

        System.out.println("\n# LRU dados Normalizados #");
        LRU.executar(numQuadros, paginasReferenciadasNormal);

       // Executando NRU
        System.out.println("\n# NRU #");
        NRU.executar(numQuadros, paginasReferenciadas);  

        System.out.println("\n# NRU dados normalizados #");
        NRU.executar(numQuadros, paginasReferenciadasNormal);


        ContadorRepetidos.contarNumeros(paginasReferenciadasNormal);

        scanner.close();
    }

    public static List<Integer> gerarSequenciaAcessosAleatoria(int numPaginas, int numTamanhoSequenciaAleatoria) {
        List<Integer> sequencia = new java.util.ArrayList<>();
        Random random = new Random();
    
        for (int i = 0; i < numTamanhoSequenciaAleatoria; i++) {
            int num1 = random.nextInt(numPaginas); // numPaginas garante que ficará dentro do intervalo de 0 a numPaginas
            sequencia.add(num1);
        }
        return sequencia;
    }

        public static List<Integer> gerarSequenciaAcessosNormal(int numPaginas, int numTamanhoSequenciaAleatoria) {
        List<Integer> sequencia = new java.util.ArrayList<>();
        Random random = new Random(); // instanciando a classe random
        
        
        for (int i = 0; i < numTamanhoSequenciaAleatoria; i++) {
            int num1 = random.nextInt(numPaginas); // numPaginas garante que ficará dentro do intervalo de 0 a numPaginas
            int num2 = random.nextInt(numPaginas);
            int num3 = random.nextInt(numPaginas);
            int num4 = random.nextInt(numPaginas);
            int soma = (num1 + num2 + num3 + num4);
            sequencia.add(Math.round(soma/4));
        }
        return sequencia;
    }
}