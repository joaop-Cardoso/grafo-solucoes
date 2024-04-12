package grafos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Grafo {
	
	private String nome;
	private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
	private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
	boolean orientado;
	boolean valorado;
	//==================================Constructor===================================//
	
	Grafo()
	{
		
	}
	
	Grafo (Grafo grafoOut)
	{
		this.setOrientacao(grafoOut.isOrientado());
		this.setValorado(grafoOut.isValorado());
		this.setNome(grafoOut.getNome());
		
		for(Vertice vertice: grafoOut.getVertices())
		{
			this.setVertices(vertice);
		}
		
		for(Aresta aresta: grafoOut.getArestas())
		{
			this.setArestas(aresta);
		}
	}
	
	//=====================================Sets=======================================//
	
	public void setOrientacao(boolean orientacao)
	{
		this.orientado = orientacao;
	}
	
	public void setValorado(boolean valorado)
	{
		this.valorado = valorado;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public void setVertices(Vertice... vertice) throws RuntimeException
	{
		
		for(int i=0; i<this.getVertices().size(); i++)
		{
			
				if(this.getVertices().get(i).getNome().equals(vertice[0].getNome()))
				{
					throw new RuntimeException("Vértice já presente. Escolha outro nome.");
				}
			
		}
		
		for(Vertice each: vertice)
		{
			this.vertices.add(each);
		}
	}
	

	public void setArestas(Aresta aresta)
	{
		this.arestas.add(aresta);
	}
	
	public void setArestas(Aresta... aresta)
	{
		for(Aresta each: aresta)
		{
		this.arestas.add(each);
		}
	}
	
	//======================================Gets======================================//
	
	public boolean isOrientado() 
	{
		boolean orientado2 = this.orientado;
		return orientado2;
	}
	
	public boolean isValorado()
	{
		return this.valorado;
	}
	
	public String getNome()
	{
		return new String(this.nome);
	}
	
	public Vertice getVertice(String vertice)
	{
		//if(this.getVertices().contains())
		for(int i=0; i<this.getVertices().size(); i++)
		{
			if(this.getVertices().get(i).getNome().equals(vertice))
			{
				return this.getVertices().get(i);
			}
		}
		throw new RuntimeException("Esse vértice não existe. Retornando nulo.");
	}
	
	public Vertice getVertice(Vertice vertice)
	{
		//if(this.getVertices().contains())
		for(int i=0; i<this.getVertices().size(); i++)
		{
			if(this.getVertices().get(i).getNome().equals(vertice))
			{
				return this.getVertices().get(i);
			}
		}
		throw new RuntimeException("Esse vértice não existe. Retornando nulo.");
	}
	
	public ArrayList<Vertice> getVertices()
	{
		ArrayList<Vertice> vertices2 = new ArrayList<Vertice>();
		
		for(Vertice vertice: this.vertices)
		{
			vertices2.add(vertice);
		}
		return vertices2;
	}

	public Vertice getVertice(int id) {
    for (Vertice vertice : vertices) {
        if (vertice.getId() == id) {
            return vertice;
        }
    }
    throw new RuntimeException("Esse vértice não existe. Retornando nulo.");
}

	public void removeAresta(Aresta aresta) throws RuntimeException
	{
		if(this.arestas.contains(aresta))
		{
			arestas.remove(aresta);
		}
		else
		{
			throw new RuntimeException("Aresta informada não contida neste grafo.");
		}
	}
	
	public ArrayList<Aresta> getArestas()
	{
		ArrayList<Aresta> arestas2 = new ArrayList<Aresta>();
		
		for(Aresta aresta: this.arestas)
		{
			arestas2.add(aresta);
		}
		return arestas2;
	}
	
	public void removeVertice(Vertice vertice) throws RuntimeException
	{
		if(this.vertices.contains(vertice))
		{
			// Remove as arestas que saem deste vértice
			if (this.orientado) {
				removeArestasSaida(vertice);
			} else {
				removeArestas(vertice);
			}

			// Remove o vértice da lista de vértices
			this.vertices.remove(vertice);
		}
		else
		{
			throw new RuntimeException("Vertice informado não contido neste grafo.");
		}
	}

	private void removeArestas(Vertice vertice)
	{
		ArrayList<Aresta> arestasParaRemover = new ArrayList<>();

		for (Aresta aresta : this.arestas) {
			if (aresta.contemVertice(vertice)) {
				arestasParaRemover.add(aresta);
			}
		}

		for (Aresta aresta : arestasParaRemover) {
			this.arestas.remove(aresta);
		}
	}

	private void removeArestasSaida(Vertice vertice)
	{
		ArrayList<Aresta> arestasParaRemover = new ArrayList<>();

		for (Aresta aresta : this.arestas) {
			if (aresta.getAresta()[0] == vertice) {
				arestasParaRemover.add(aresta);
			}
		}

		for (Aresta aresta : arestasParaRemover) {
			this.arestas.remove(aresta);
		}
	}

	
	public int getOrdem() 
	{
        return this.vertices.size();
    }
	
	public int getGrau()
	{
		ArrayList<Aresta> clone = new ArrayList<Aresta>();
		clone = this.arestas;
		
		ArrayList<Vertice> vertices = new ArrayList<Vertice>();
		
		for(int i = 0; i<clone.size(); i++)
		{
			Vertice[] vertice = clone.get(i).getAresta();
			vertices.add(vertice[0]);
			vertices.add(vertice[1]);
		}
		
		int max = 0;
		
		for (int i = 0; i<vertices.size(); i++)
		{
			int qtd = 0;
			for (int j = 0; j<vertices.size(); j++)
			{
				if(vertices.get(i).getNome().equals((vertices.get(j).getNome())))
				{
					qtd++;
				}
				
				if(qtd > max)	
				{
					max = qtd;
				}
			}
			
		}
		return max;
	}
	
	public void salvaGrafo(){
		String path = "";
		String pathArchive = path + this.getNome() + ".dot";
		File archive = new File(pathArchive);

		try{
			boolean saved = archive.createNewFile();
			
				editaGrafo(archive, this.getNome());
				System.out.println("\nO Grafo " + this.getNome() + " foi salvo com sucesso!");
			
		} catch(IOException e){
			System.out.println("Houve um erro ao salvar o grafo: " + e.getMessage());
		}
	}

	public void editaGrafo(File arquivo, String stringGrafo){
		try {
			FileWriter fw = new FileWriter(arquivo);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(formatarStringGrafo());
			bw.close();
			fw.close();
		} catch(IOException e) {
			System.err.println("Erro ao salvar grafo: " + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getOrigem() throws RuntimeException 
	{
		if (this.isOrientado()) 
		{
			String origem = "";

			ArrayList<Aresta> clone = new ArrayList<Aresta>();
			clone = this.arestas;

			Vertice[] target = new Vertice[2];
			target[0] = new Vertice();
			target[1] = new Vertice();

			ArrayList<Vertice[]> targetList = new ArrayList<Vertice[]>();

			for (int i = 0; i < clone.size(); i++) 
			{
				target = clone.get(i).getAresta();
				targetList.add(target);
			}

			for (int i = 0; i < clone.size(); i++) 
			{
				int conta = 0;

				for (int j = 0; j < clone.size(); j++) 
				{
					if (!targetList.get(i)[0].equals(targetList.get(j)[1])) 
					{
						conta++;

						if (conta == clone.size()) 
						{
							origem = origem + targetList.get(i)[0].getNome();
						}
					}
				}
			}
			
			StringBuilder resultado = new StringBuilder();
			
	        for (int i = 0; i < origem.length(); i++) 
	        {
	            char caractere = origem.charAt(i);
	            
	            if (resultado.indexOf(String.valueOf(caractere)) == -1) 
	            {
	                resultado.append(caractere);
	                resultado.append(" ");
	            }
	        }
			
			return resultado.toString();
		}
		else
		{
			throw new RuntimeException("Grafos não-orientados não possuem vértices-origem.");
		}
	}
	
	public String getSumidouro() throws RuntimeException 
	{
		if (this.isOrientado()) 
		{
			String origem = "";

			ArrayList<Aresta> clone = new ArrayList<Aresta>();
			clone = this.arestas;

			Vertice[] target = new Vertice[2];
			target[0] = new Vertice();
			target[1] = new Vertice();

			ArrayList<Vertice[]> targetList = new ArrayList<Vertice[]>();

			for (int i = 0; i < clone.size(); i++) 
			{
				target = clone.get(i).getAresta();
				targetList.add(target);
			}

			for (int i = 0; i < clone.size(); i++) 
			{
				int conta = 0;

				for (int j = 0; j < clone.size(); j++) 
				{
					if (!targetList.get(i)[1].equals(targetList.get(j)[0])) 
					{
						conta++;

						if (conta == clone.size()) 
						{
							origem = origem + targetList.get(i)[1].getNome();
						}
					}
				}
			}
			
			StringBuilder resultado = new StringBuilder();
			
	        for (int i = 0; i < origem.length(); i++) 
	        {
	            char caractere = origem.charAt(i);
	            
	            if (resultado.indexOf(String.valueOf(caractere)) == -1) 
	            {
	                resultado.append(caractere);
	                resultado.append(" ");
	            }
	        }
			
			return resultado.toString();
		}
		else
		{
			throw new RuntimeException("Grafos não-orientados não possuem vértices-sumidouro.");
		}
	}

	public String getFTransDireto(String ftrans) throws RuntimeException

	{
		try {
			Vertice vertice = new Vertice();

			for(int i=0; i<this.getArestas().size(); i++)
			{
				if(this.getVertices().get(i).getNome().equals(ftrans))
				{
					vertice = this.getVertices().get(i);
					break; 
				}
			}

			if(!vertice.getNome().equals(ftrans))
			{
				throw new RuntimeException ("O vértice" + ftrans + " não existe em "+this.getNome());
			}
			
			if(this.getSumidouro().contains(vertice.getNome()))
			{
				return "O vértice | "+vertice.getNome()+"| só pode chegar nele mesmo.";
			}

			ArrayList<Aresta> conexoes = new ArrayList<Aresta>();

			for(int i=0; i<this.getArestas().size(); i++)
			{
				if(this.getArestas().get(i).getAresta()[0].equals(vertice))
				{
					conexoes.add(getArestas().get(i));
				}
			}

			for(int i=0; i<conexoes.size(); i++)
			{
				for(int k=0; k<this.getArestas().size(); k++)
				{
					if(this.getArestas().get(k).getAresta()[0].equals(conexoes.get(i).getAresta()[1]))
					{
						conexoes.add(this.getArestas().get(k));
					}
				}
			}

			Set<Character> conjuntoUnico = new HashSet<>();
			String ligacoes = "";
			String vertices = "";

			for (int i=0; i<conexoes.size(); i++) 
			{

				for (char c : conexoes.get(i).getArestaNome().toCharArray()) 
				{
					conjuntoUnico.add(c);
				}
			}
			StringBuilder stringUnica = new StringBuilder();
			for (char c : conjuntoUnico) 
			{
				stringUnica.append(c);
				stringUnica.append(" | ");
			}
			vertices = stringUnica.toString() + " ";

			for(int i=0; i<conexoes.size(); i++)
			{
				String seta = conexoes.get(i).getAresta()[0].getNome() + "->" + conexoes.get(i).getAresta()[1].getNome();
				ligacoes = ligacoes + seta + " | ";
			}

			vertices = "Vértices que o vértice "+ vertice.getNome() + " consegue chegar: \n| "+vertices+ " \n"+"Arestas caminho:\n| "+ligacoes+"\n";

			return vertices;}
		catch(RuntimeException a)
		{
			a.printStackTrace();
			return "Esse vértice não existe no grafo "+this.getNome();
		}
	}

	public String getFTransIndireto(String ftrans) throws RuntimeException

	{
		try {
			Vertice vertice = new Vertice();

			for(int i=0; i<this.getArestas().size(); i++)
			{
				if(this.getVertices().get(i).getNome().equals(ftrans))
				{
					vertice = this.getVertices().get(i);
					break; 
				}
			}

			if(!vertice.getNome().equals(ftrans))
			{
				throw new RuntimeException ("O vértice" + ftrans + " não existe em "+this.getNome());
			}
			
			if(this.getOrigem().contains(vertice.getNome()))
			{
				return "Nenhum vértice chega a | "+vertice.getNome()+"|, exceto ele mesmo.";
			}

			ArrayList<Aresta> conexoes = new ArrayList<Aresta>();

			for(int i=0; i<this.getArestas().size(); i++)
			{
				if(this.getArestas().get(i).getAresta()[1].equals(vertice))
				{
					conexoes.add(getArestas().get(i));
				}
			}

			for(int i=0; i<conexoes.size(); i++)
			{
				for(int k=0; k<this.getArestas().size(); k++)
				{
					if(this.getArestas().get(k).getAresta()[1].equals(conexoes.get(i).getAresta()[0]))
					{
						conexoes.add(this.getArestas().get(k));
					}
				}
			}

			Set<Character> conjuntoUnico = new HashSet<>();
			String ligacoes = "";
			String vertices = "";

			for (int i=0; i<conexoes.size(); i++) 
			{

				for (char c : conexoes.get(i).getArestaNome().toCharArray()) 
				{
					conjuntoUnico.add(c);
				}
			}
			StringBuilder stringUnica = new StringBuilder();
			for (char c : conjuntoUnico) 
			{
				stringUnica.append(c);
				stringUnica.append(" | ");
			}
			vertices = stringUnica.toString() + " ";

			for(int i=0; i<conexoes.size(); i++)
			{
				String seta = conexoes.get(i).getAresta()[0].getNome() + "->" + conexoes.get(i).getAresta()[1].getNome();
				ligacoes = ligacoes + seta + " | ";
			}

			vertices = "Vértices que chegam até "+ vertice.getNome() + ":\n| "+vertices+ " \n"+"Arestas caminho:\n| "+ligacoes+"\n";

			return vertices;}
		catch(RuntimeException a)
		{
			a.printStackTrace();
			return "Esse vértice não existe no grafo "+this.getNome();
		}
	}

	public String getArvoreGeradora() throws RuntimeException {
	    try {
	        if (this.isOrientado()) 
	        {
	            throw new RuntimeException("Impossível achar árvore geradora, pois este grafo é orientado.");
	        } else if (!this.isValorado()) 
	        {
	            throw new RuntimeException("Impossível achar árvore geradora, pois este grafo não é valorado.");
	        } else 
	        {
	            ArrayList<Aresta> arvore = new ArrayList<>();
	            ArrayList<Vertice> verticesVisitados = new ArrayList<>();
	            ArrayList<Aresta> lista = this.getArestas();
	            
	            // Ordena as arestas pelo valor
	            lista.sort(Comparator.comparingInt(Aresta::getValor));
	            
	            // Adiciona o primeiro vértice arbitrariamente 
	            verticesVisitados.add(lista.get(0).getAresta()[0]);
	            
	            // Enquanto todos os vértices não estiverem na árvore
	            while (verticesVisitados.size() < this.getVertices().size()) {
	                // Procura a menor aresta conectando vértices na árvore a vértices fora dela
	                for (Aresta aresta : lista) {
	                    Vertice vertice1 = aresta.getAresta()[0];
	                    Vertice vertice2 = aresta.getAresta()[1];
	                    
	                    if (verticesVisitados.contains(vertice1) && !verticesVisitados.contains(vertice2)) {
	                        // Adiciona a aresta à árvore geradora
	                        arvore.add(aresta);
	                        verticesVisitados.add(vertice2);
	                        break; // Sai do loop após adicionar a aresta
	                    } else if (verticesVisitados.contains(vertice2) && !verticesVisitados.contains(vertice1)) {
	                        // Adiciona a aresta à árvore geradora
	                        arvore.add(aresta);
	                        verticesVisitados.add(vertice1);
	                        break; // Sai do loop após adicionar a aresta
	                    }
	                }
	            }
	            
	            // Monta a representação da árvore geradora mínima como string
	            StringBuilder arvere = new StringBuilder();
	            for (Aresta a : arvore) {
	                arvere.append(a.getArestaNome()).append(" ").append(a.getValor()).append(" ");
	            }
	            return arvere.toString();
	        }
	    } catch (Exception e) {
	        return "Erro: " + e.getMessage();
	    }
	}

			
	
    public String formatarStringGrafo() throws Exception{
    	
        ArrayList<Aresta> arestas = this.getArestas();
        
        String[] grafoComponents = new String[arestas.size()];
        String orientacao = "";
        String grafoString = "";
        
        if (this.isOrientado())
        {
        	orientacao = "digraph ";
        }
        else
        {
        	orientacao = "graph ";
        }
        for(int i=0; i<arestas.size(); i++)
        {
            String nomeEmissor = arestas.get(i).getAresta()[0].getNome();
            String nomeReceptor = arestas.get(i).getAresta()[1].getNome();
            String peso = Integer.toString(arestas.get(i).getValor());
            String valor1 = Integer.toString(arestas.get(i).getAresta()[0].getValor());
            String valor2 = Integer.toString(arestas.get(i).getAresta()[1].getValor());


            if(this.isValorado() && this.isOrientado() ){
                grafoComponents[i] = (nomeEmissor + "->" + nomeReceptor + "[label= " + peso + "]" + ";\n" );
            }
            else if
            (!this.isValorado() && this.isOrientado()) {
                grafoComponents[i] = (nomeEmissor + "->" + nomeReceptor + ";\n");
            }
            else if
            (this.isValorado() && !this.isOrientado()) {
                grafoComponents[i] = (nomeEmissor + "--" + nomeReceptor + "[label= " + peso + "]" + ";\n" );
            }
            else if
            (!this.isValorado() && !this.isOrientado()) {
                grafoComponents[i] = (nomeEmissor + "--" + nomeReceptor +  ";\n");
            }
        }
        
           grafoString = orientacao + this.getNome() + "{\n";
            
           for(int i = 0; i<arestas.size(); i++)
           {
        	   grafoString = grafoString +grafoComponents[i];
           }
           
           grafoString = grafoString + "}";
           return grafoString;
        }
        
    }