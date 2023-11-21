package algoritmos;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContadorRepetidos {

    public static Map<Integer, Integer> contarNumeros(List<Integer> lista) {
        Map<Integer, Integer> contador = new HashMap<>();

        for (int numero : lista) {
            if (contador.containsKey(numero)) {
                contador.put(numero, contador.get(numero) + 1);
            } else {
                contador.put(numero, 1);
            }
        }
        salvarContadorEmArquivo(contador, "dados.txt");
        return contador;
    }

    public static void imprimirContador(Map<Integer, Integer> contador) {
        for (Map.Entry<Integer, Integer> entry : contador.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static void salvarContadorEmArquivo(Map<Integer, Integer> contador, String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (Map.Entry<Integer, Integer> entry : contador.entrySet()) {
                writer.println(entry.getValue());
            }
            System.out.println("Resultado salvo em " + nomeArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}