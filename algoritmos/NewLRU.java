package algoritmos;

import java.util.*;

public class NewLRU {

    public static void executar(int numQuadros, int numPaginas, List<Integer> sequencia) {
        System.out.println("Número de quadros: " + numQuadros);
        System.out.println("Número total de páginas distintas: " + numPaginas);

        LinkedHashMap<Integer, Integer> mapa = new LinkedHashMap<>(numQuadros, 0.75f, true);
        int pageFaults = 0;

        long startTime = System.currentTimeMillis(); //iniciando contador de tempo

        for (int pagina : sequencia) {
            if (!mapa.containsKey(pagina)) {
                if (mapa.size() >= numQuadros) {
                    int paginaRemovida = mapa.entrySet().iterator().next().getKey();
                    mapa.remove(paginaRemovida);
                }
                mapa.put(pagina, 0);
                pageFaults++;

            } else {
                // Atualizar a contagem de uso para indicar que a página foi acessada recentemente
                mapa.put(pagina, mapa.get(pagina) + 1);
            }
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execução LRU em ms: " + executionTime);
        System.out.println("Número total de page faults (LRU): " + pageFaults);
    }}
