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

        // Executando FIFO
        System.out.println("\n# FIFO #");
        FIFO.executar(numQuadros, numPaginas, paginasReferenciadas);
        
        // Executando Segunda Chance
        System.out.println("\n# Segunda Chance #");
        SegundaChance.executar(numQuadros, numPaginas,paginasReferenciadas);
        
        // Executando Relogio
        System.out.println("\n# Relógio #");
        Relogio.executar(numQuadros, numPaginas, paginasReferenciadas);
        
        // Executando LRU
        System.out.println("\n# LRU #");
        LRU.executar(numQuadros, numPaginas, paginasReferenciadas);

        // Executando NRU
        System.out.println("\n# NRU #");  
        NRU.executar(numQuadros, numPaginas, paginasReferenciadas);
        
        System.out.println(paginasReferenciadas);
        scanner.close();
    }

    public static List<Integer> gerarSequenciaAcessosAleatoria(int numPaginas, int numTamanhoSequenciaAleatoria) {
        List<Integer> sequencia = new java.util.ArrayList<>();
        Random random = new Random(); // instanciando a classe random
        int intervalo = numPaginas/9; //necessário dividir numPaginas pela quantidade que compoem a soma para que não exceda o tamanho de numPaginas
        //uma concessão necessária para gerar uma distribuição dos dados próxima da curva de gauss
        for (int i = 0; i < numTamanhoSequenciaAleatoria; i++) {
            int num1 = random.nextInt(intervalo); // numPaginas garante que ficará dentro do intervalo de 0 a numPaginas
            int num2 = random.nextInt(intervalo);
            int num3 = random.nextInt(intervalo);
            int num4 = random.nextInt(intervalo);
            int num5 = random.nextInt(intervalo);
            int num6 = random.nextInt(intervalo);
            int num7 = random.nextInt(intervalo);
            int num8 = random.nextInt(intervalo);
            int num9 = random.nextInt(intervalo);
            int soma = (num1 + num2 + num3 + num4+ num5 + num5 + num6 + num7 + num8 + num9);
            sequencia.add(soma);
        }
        return sequencia;
    }
}