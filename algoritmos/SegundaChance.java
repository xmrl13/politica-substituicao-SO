package algoritmos;
import java.util.*;

public class SegundaChance {

    public static void executar(int numQuadros, int numPaginas, List<Integer> sequencia) {
        System.out.println("Número de quadros: " + numQuadros);
        System.out.println("Número total de páginas distintas: " + numPaginas);

        LinkedList<Integer> lista = new LinkedList<>();
        BitSet referenciadas = new BitSet(numQuadros);
        int pageFaults = 0;

        long startTime = System.currentTimeMillis();

        for (int pagina : sequencia) {
            if (!lista.contains(pagina)) {
                if (lista.size() >= numQuadros) {
                    int idx = encontrarPaginaParaSubstituir(referenciadas, lista);
                    lista.set(idx, pagina);
                    referenciadas.clear(idx);
                } else {
                    lista.add(pagina);
                }
                referenciadas.set(lista.indexOf(pagina));
                pageFaults++;
            } else {
                referenciadas.set(lista.indexOf(pagina));
            }
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Tempo de execução Segunda Chance em ms: " + executionTime );
        System.out.println("Número total de page faults (Segunda Chance): " + pageFaults);
    }

    private static int encontrarPaginaParaSubstituir(BitSet referenciadas, List<Integer> lista) {
        int idx = -1;
        while (true) {
            idx = (idx + 1) % lista.size();
            if (!referenciadas.get(idx)) {
                return idx;
            }
            referenciadas.clear(idx);
        }
    }
}