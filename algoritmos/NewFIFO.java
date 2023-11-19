package algoritmos;

import java.util.*;

public class NewFIFO {

    public static void executar(int numQuadros, int numPaginas, List<Integer> sequencia) {
        System.out.println("Número de quadros: " + numQuadros);
        System.out.println("Número total de páginas distintas: " + numPaginas);

        HashSet<Integer> conjunto = new HashSet<>(numQuadros);
        Queue<Integer> fila = new LinkedList<>();
        int pageFaults = 0;

        long startTime = System.currentTimeMillis();
        for (int pagina : sequencia) {
            if (conjunto.size() < numQuadros) {
                if (!conjunto.contains(pagina)) {
                    conjunto.add(pagina);
                    fila.add(pagina);
                    pageFaults++;
                    
                }
            } else {
                if (!conjunto.contains(pagina)) {
                    int paginaRemovida = fila.poll();
                    conjunto.remove(paginaRemovida);

                    conjunto.add(pagina);
                    fila.add(pagina);

                    pageFaults++;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Tempo de execução Fifo em ms: " + executionTime);
        System.out.println("Número total de page faults (FIFO): " + pageFaults);
    }


}
