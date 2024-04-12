package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Malgrange {
    public int malgrange(Grafo grafo) {
        if (!grafo.isOrientado()) {
            throw new IllegalArgumentException("O algoritmo de Malgrange é aplicável apenas a grafos orientados.");
        }

        int numeroCircuitos = 0;

        for (Vertice vertice : grafo.getVertices()) {
            int grauEntrada = getGrauEntrada(grafo, vertice);
            int grauSaida = getGrauSaida(grafo, vertice);

            if (grauEntrada == grauSaida + 1) {
                numeroCircuitos += malgrangeRecursivo(grafo, vertice, new HashMap<>());
            }
        }

        return numeroCircuitos;
    }

    private static int malgrangeRecursivo(Grafo grafo, Vertice vertice, Map<Vertice, Boolean> visitados) {
        visitados.put(vertice, true);

        int numeroCircuitos = 1;

        for (Aresta aresta : grafo.getArestas()) {
            Vertice proximoVertice = aresta.getAresta()[1];

            if (!visitados.getOrDefault(proximoVertice, false)) {
                numeroCircuitos += malgrangeRecursivo(grafo, proximoVertice, visitados);
            }
        }

        visitados.put(vertice, false);

        return numeroCircuitos;
    }

    private static int getGrauEntrada(Grafo grafo, Vertice vertice) {
        int grauEntrada = 0;

        for (Aresta aresta : grafo.getArestas()) {
            if (aresta.getAresta()[1].equals(vertice)) {
                grauEntrada++;
            }
        }

        return grauEntrada;
    }

    private static int getGrauSaida(Grafo grafo, Vertice vertice) {
        int grauSaida = 0;

        for (Aresta aresta : grafo.getArestas()) {
            if (aresta.getAresta()[0].equals(vertice)) {
                grauSaida++;
            }
        }

        return grauSaida;
    }
  
}

