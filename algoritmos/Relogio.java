package algoritmos;

import java.util.ArrayList;
import java.util.List;

class Pagina {
    int id;
    boolean bitR;

    public Pagina(int id) {
        this.id = id;
        this.bitR = false;
    }
}

public class Relogio {
    private List<Pagina> paginas;
    private int ponteiro;
    private int pageFaults;

    public Relogio(int tamanho) {
        paginas = new ArrayList<>(tamanho);
        for (int i = 0; i < tamanho; i++) {
            paginas.add(new Pagina(i));
        }
        ponteiro = 0;
        pageFaults = 0;
    }

    public void referenciarPagina(int id) {
        for (Pagina pagina : paginas) {
            if (pagina.id == id) {
                pagina.bitR = true;
                return;
            }
        }
    }

    public void substituirPagina(int idPaginaReferenciada) {
        while (true) {
            Pagina paginaAtual = paginas.get(ponteiro);
            if (paginaAtual.bitR) {
                paginaAtual.bitR = false;
            } else {
                pageFaults++;
                break;
            }

            ponteiro = (ponteiro + 1) % paginas.size();
        }
    }

    public int getPageFaults() {
        return pageFaults;
    }

    public static void executar(int numQuadros, int numPaginas, List<Integer> paginasReferenciadas) {
        Relogio algoritmo = new Relogio(numQuadros);

        long startTime = System.currentTimeMillis();

        for (int idPaginaReferenciada : paginasReferenciadas) {
            algoritmo.referenciarPagina(idPaginaReferenciada);

            // Verificar se a página está na memória
            if (!verificarPaginaNaMemoria(algoritmo.paginas, idPaginaReferenciada)) {
                algoritmo.substituirPagina(idPaginaReferenciada);
            }
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime -  startTime;

        System.out.println("Tempo de execução Relógio em ms: " + executionTime);
        System.out.println("Número de Page Faults: " + algoritmo.getPageFaults());
    }

    private static boolean verificarPaginaNaMemoria(List<Pagina> paginas, int idPagina) {
        for (Pagina pagina : paginas) {
            if (pagina.id == idPagina) {
                return true;
            }
        }
        return false;
    }
}
