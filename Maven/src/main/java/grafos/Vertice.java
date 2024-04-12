package grafos;

import java.util.List;
import java.util.ArrayList;

public class Vertice {
	private String vertice; //nome do vertice
	private int valor;
	private int id;
    private boolean visited;  // Adiciona um campo para rastrear se o vértice foi visitado.
    private List<Aresta> arestas;

	Vertice()
	{
		
	}
	
	Vertice(Vertice vertice)
	{
		try {
			this.SetVertice(vertice.getNome());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		if(vertice.getValor() > 0)
		{
			this.setValor(vertice.getValor());
		}
		
	}
	
	Vertice(String vertice)
	{
		try {
			SetVertice(vertice);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	Vertice(String vertice, int valor, int id)
	{
		try {
			SetVertice(vertice);
			 this.id = id;
		     this.visited = false;
		     this.arestas = new ArrayList<>();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}


	Vertice(String vertice, int valor)
	{
		try {
			SetVertice(vertice);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		setValor(valor);
	}
	
	public void SetVertice(String vertice) throws Exception
	{
		if(vertice.contains(" "))
		{
			throw new Exception("Não pode haver espaço no nome dos vértices");
		}
			
		if(vertice.length() >=4 )
		{
			throw new Exception("Nomes de vértices não podem ser maiores do que 3 caracteres");
		}
		if(!(vertice.length() >=4))
		{
		this.vertice = vertice;
		}
	}
	
	public void setValor(int valor)
	{
		this.valor = valor;
	}
	
	public String getNome()
	{
		return new String(this.vertice);
	}
	
	public int getValor()
	{
		int valor2 = this.valor;
		return valor2;
	}
	
	public int getId()
	{
		return this.id;
	}

	public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

	public int getID(){
		return id;
	}
}