package grafos;

import java.util.ArrayList;

public class Aresta {
    private Vertice[] aresta = new Vertice[2];
    private int valor;

    Aresta()
    {
    	
    }
    Aresta(Vertice vertices[]) {
        setAresta(vertices);
    }
    
    Aresta(Vertice vertice1, Vertice vertice2) {
        setAresta(vertice1, vertice2);
    }
    
    public Aresta(Vertice vertice1, Vertice vertice2, int valor) {
        this.aresta[0] = vertice1;
        this.aresta[1] = vertice2;
        this.valor = valor;
    }
    
    Aresta(Vertice vertices[], int valor) {
        setAresta(vertices);
    }

    //========================Sets============================//

    public void setAresta(Vertice vertices[]) {
        this.aresta[0] = vertices[0];
        this.aresta[1] = vertices[1];
    }
    
    public void setAresta(Vertice vertice1, Vertice vertice2) {
        this.aresta[0] = vertice1;
        this.aresta[1] = vertice2;
    }
    
    public void setValor(int valor)
    {
    	this.valor = valor;
    }
    
    //========================Gets============================//
    
    public Vertice[] getAresta()
    {
    	return this.aresta;
    }
    
    public int getValor()
    {
    	return this.valor;
    }
    
    public String getArestaNome()
    {
    	String aresta = this.aresta[0].getNome() + this.aresta[1].getNome(); 	
    	return aresta;
    }

    public boolean contemVertice(Vertice vertice) {
        return this.aresta[0] == vertice || this.aresta[1] == vertice;
    }

    public Vertice getVertice1() {
        return aresta[0];
    }

    public Vertice getVertice2() {
        return aresta[1];
    }



    // Implementação do método para obter o outro vértice em relação a um vértice fornecido.
    public Vertice getOutroVertice(Vertice vertice) {
        if (vertice.equals(aresta[0])) {
            return aresta[1];
        } else if (vertice.equals(aresta[1])) {
            return aresta[0];
        } else {
            throw new IllegalArgumentException("O vértice fornecido não pertence a esta aresta.");
        }
    }

}