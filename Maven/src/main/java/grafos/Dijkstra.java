package grafos;

import java.util.*;

public class Dijkstra {
    public Map<String, Integer> dijkstra(Grafo grafo, Vertice origem) {
        Map<String, Integer> distancia = new HashMap<>();
        Map<Vertice, Vertice> predecessores = new HashMap<>();
        Set<Vertice> verticesNaoVisitados = new HashSet<>(grafo.getVertices());

        // Inicialização
        for (Vertice vertice : grafo.getVertices()) {
            distancia.put(vertice.getNome(), Integer.MAX_VALUE);
            predecessores.put(vertice, null);
        }

        distancia.put(origem.getNome(), 0);

        while (!verticesNaoVisitados.isEmpty()) {
            Vertice verticeAtual = obterVerticeComMenorDistancia(verticesNaoVisitados, distancia);

            verticesNaoVisitados.remove(verticeAtual);

            for (Aresta aresta : grafo.getArestas()) {
                if (aresta.getAresta()[0] == verticeAtual) {
                    Vertice verticeVizinho = aresta.getAresta()[1];
                    int pesoAresta = aresta.getValor();
                    int distanciaDoAtualAoVizinho = distancia.get(verticeAtual.getNome()) + pesoAresta;

                    if (distanciaDoAtualAoVizinho < distancia.get(verticeVizinho.getNome())) {
                        distancia.put(verticeVizinho.getNome(), distanciaDoAtualAoVizinho);
                        predecessores.put(verticeVizinho, verticeAtual);
                    }
                }
            }
        }

        return distancia;
    }

    private static Vertice obterVerticeComMenorDistancia(Set<Vertice> vertices, Map<String, Integer> distancia) {
        Vertice verticeMenorDistancia = null;
        int menorDistancia = Integer.MAX_VALUE;

        for (Vertice vertice : vertices) {
            int dist = distancia.get(vertice.getNome());
            if (dist < menorDistancia) {
                menorDistancia = dist;
                verticeMenorDistancia = vertice;
            }
        }

        return verticeMenorDistancia;
    }
}

    
