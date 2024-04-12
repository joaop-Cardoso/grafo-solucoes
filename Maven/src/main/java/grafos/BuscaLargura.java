package grafos;

import java.util.*;

public class BuscaLargura {
    public List<List<String>> bfs(Grafo grafo, Vertice origem) {
        List<List<String>> caminhosPercorridos = new ArrayList<>(); // Lista para armazenar os caminhos percorridos
        Queue<Vertice> fila = new LinkedList<>();
        Set<Vertice> visitados = new HashSet<>();

        fila.offer(origem);
        visitados.add(origem);

        List<String> origemList = new ArrayList<>();
        origemList.add("Vértice de origem encontrado: " + origem.getNome());
        caminhosPercorridos.add(origemList);

        List<String> visita = new ArrayList<>();
        visita.add("Ordem de visita ---> Visitado: " + origem.getNome()); // Lista para cada visita
        caminhosPercorridos.add(visita);

        while (!fila.isEmpty()) {
            Vertice verticeAtual = fila.poll();

            for (Aresta aresta : grafo.getArestas()) {
                Vertice verticeVizinho = aresta.getAresta()[1];
                if (!visitados.contains(verticeVizinho)) {
                    fila.offer(verticeVizinho);
                    visitados.add(verticeVizinho);

                    visita.add("Visitado: " + verticeVizinho.getNome()); // Adiciona o vértice visitado à lista
                }
            }
        }

        return caminhosPercorridos;
    }
}
