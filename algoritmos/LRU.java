package algoritmos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LRU {

    static class Pagina {
        int id;
        long tempoAcesso;

        Pagina(int id, long tempoAcesso) {
            this.id = id;
            this.tempoAcesso = tempoAcesso;
        }
    }

    public static void executar(int numQuadros, List<Integer> sequencia) {
        Map<Integer, Pagina> mapa = new HashMap<>(numQuadros);
        LinkedList<Pagina> ordemAcesso = new LinkedList<>();
        int pageFaults = 0;
        long tempo = 0;

        long startTime = System.currentTimeMillis(); // iniciando contador de tempo

        for (int pagina : sequencia) {
            if (!mapa.containsKey(pagina)) {
                if (ordemAcesso.size() >= numQuadros) {
                    Pagina paginaRemovida = ordemAcesso.removeFirst();
                    mapa.remove(paginaRemovida.id);
                }
                mapa.put(pagina, new Pagina(pagina, tempo++));
                ordemAcesso.addLast(mapa.get(pagina));
                pageFaults++;
            } else {
                // Atualizar a ordem de acesso (remover e adicionar ao final)
                Pagina paginaAtual = mapa.get(pagina);
                ordemAcesso.remove(paginaAtual);
                paginaAtual.tempoAcesso = tempo++;
                ordemAcesso.addLast(paginaAtual);
            }
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tempo de execução em ms: " + executionTime);
        System.out.println("Número total de page faults: " + pageFaults);
    }
}
