package algoritmos;

import java.util.ArrayList;
import java.util.List;

public class NRU {

    public static void executar(int numQuadros, int numPaginas, List<Integer> sequencia) {
        System.out.println("Número de quadros: " + numQuadros);
        System.out.println("Número total de páginas distintas: " + numPaginas);

        List<Integer> lista = new ArrayList<>(numQuadros);
        List<Boolean> referenciadas = new ArrayList<>();
        List<Boolean> modificadas = new ArrayList<>();
        int pageFaults = 0;

        long startTime = System.currentTimeMillis();

        for (int pagina : sequencia) {
            int index = lista.indexOf(pagina);

            if (index == -1) {
                // Page fault: a página não está na memória
                if (lista.size() < numQuadros) {
                    // Há espaço disponível nos quadros
                    lista.add(pagina);
                    referenciadas.add(true);  // Inicializa referenciadas para a nova página
                    modificadas.add(false);  // Inicializa modificadas para a nova página
                } else {
                    // Nenhum espaço disponível, realizar substituição
                    int paginaSubstituir = escolherPaginaParaSubstituir(referenciadas, modificadas);
                    int indexSub = lista.indexOf(paginaSubstituir);

                    // Substituir a página
                    if (indexSub != -1) {
                        lista.set(indexSub, pagina);
                        referenciadas.set(indexSub, true);
                        modificadas.set(indexSub, false);
                    }
                }
                pageFaults++;
            } else {
                // Página está na memória
                referenciadas.set(index, true);
            }
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Tempo de execução NRU em ms: " + executionTime);
        System.out.println("Número total de page faults (NRU): " + pageFaults);
    }

    private static int escolherPaginaParaSubstituir(List<Boolean> referenciadas, List<Boolean> modificadas) {
        for (int i = 0; i < referenciadas.size(); i++) {
            if (!referenciadas.get(i) && !modificadas.get(i)) {
                return i; // Escolhe página não referenciada e não modificada
            }
        }
        for (int i = 0; i < referenciadas.size(); i++) {
            if (!referenciadas.get(i)) {
                return i; // Escolhe página não referenciada
            }
        }
        for (int i = 0; i < referenciadas.size(); i++) {
            if (i < referenciadas.size() && i < modificadas.size()) {
                referenciadas.set(i, false); // Reseta flags de referência para a próxima iteração
            }
        }
        return escolherPaginaParaSubstituir(referenciadas, modificadas);
    }
}
