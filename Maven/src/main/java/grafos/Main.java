package grafos;
import java.util.Scanner;
import java.util.regex.Pattern;

import br.com.davesmartins.api.Graph;

import java.util.regex.Matcher;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws RuntimeException {
/*
		Grafo grafo = new Grafo();
		grafo.setNome("teste");
		
		Vertice a = new Vertice("a");
		Vertice b = new Vertice("b");
		Vertice c = new Vertice("c");
		Vertice d = new Vertice("d");
		Vertice e = new Vertice("e");
		Vertice f = new Vertice("f");
		
		Aresta ab = new Aresta(a,b);
		Aresta ac = new Aresta(a,c);
		Aresta eb = new Aresta(e,b);
		Aresta ce = new Aresta(c,e);
		Aresta dc = new Aresta(d,c);
		Aresta df = new Aresta(d,f);
		Aresta cf = new Aresta(c,f);
		Aresta ef = new Aresta(e,f);

		grafo.setVertices(a,b,c,d,e,f);
		grafo.setArestas(ab, ac, eb, ce, df, cf, ef);
		grafo.setValorado(false);
		grafo.setOrientacao(true);
		
		Grafo grafo2 = new Grafo();
		grafo.setNome("teste2");
		
		ab.setValor(5);
		ac.setValor(2);
		eb.setValor(3);
		ce.setValor(1);
		df.setValor(7);
		cf.setValor(2);
		ef.setValor(4);
		

		grafo2.setVertices(a,b,c,d,e,f);
		grafo2.setArestas(ab, ac, eb, ce, df, cf, ef);
		grafo2.setValorado(true);
		grafo2.setOrientacao(false);
		
		for(int i = 0; grafo2.getArestas().size() > i ; i++)
		{
			System.out.print(grafo2.getArestas().get(i).getArestaNome() + grafo2.getArestas().get(i).getValor()+ " " );
		}
		
		System.out.println("\n" + grafo2.getArvoreGeradora());
	
}*/

	/*
		System.out.println("Características do grafo teste:");
		System.out.println("Vértice(s) de origem:"+ grafo.getOrigem());
		System.out.println("Vértice(s) sumidouro(s):"+grafo.getSumidouro());
		System.out.println("Ordem:"+grafo.getOrdem());
		System.out.println("Grau máximo:"+grafo.getGrau());
		System.out.println("--------------------------------");*/
		
		
		//ImportaGrafo.importarGrafo();
		Scanner scan = new Scanner(System.in);
		String leitura = "0";
		String leitura2= "0";
		while(true)
		{
			leitura = Menu(scan, leitura);

			if(leitura.equals("1"))
			{
				CriaGrafo.CriarGrafo(new Grafo(),scan,leitura);
			}
			else
			if(leitura.equals("2"))
			{
				String[] nomesDosArquivos = escolhaGrafo();
				leitura = Menu2(scan, leitura, nomesDosArquivos);
				Grafo importado = new Grafo(ArmazenaGrafo.getGraphZero());

				while(!leitura.equals("0"))
				{	
					leitura = Menu21(scan, leitura, importado);
					leitura2 = leitura;
					if(leitura.equals("1"))
					{
						System.out.println("Características do grafo "+importado.getNome()+":");
						System.out.println("Ordem:"+importado.getOrdem());
						System.out.println("Grau máximo:"+importado.getGrau());
						try {
							System.out.println("Vértice(s) de origem:"+ importado.getOrigem());
						}catch(RuntimeException excessao)
						{
							System.err.println("Erro: grafos não-orientados não possuem vértices-origem.");
						}
						
						try {
							System.out.println("Vértice(s) de fim:"+ importado.getSumidouro());
						}catch(RuntimeException excessao2)
						{
							System.err.println("Erro: grafos não-orientados não possui vértices-sumidouro.");
						}
						
						System.out.println("--------------------------------");
					}
					
					if(leitura.equals("2"))
					{
					
						while(leitura2.equals("2"))
						{
							System.out.println("1-Adicionar vértice\n2-Adicionar aresta\n3-Remover vértice\n4-Remover aresta\n5-Fecho transitivo direto\n6-Fecho transitivo indireto\n0-Voltar:");
							leitura2 = scan.next();
							leitura2 = Menu22(scan, leitura2, importado);
						}
					}
					
					if(leitura.equals("3"))
					{
						String leitura3 = null;
						System.out.println("1-Menor caminho(Dijkstra) \n2-Busca em largura \n3-Reduçao(Malgrange) \n4-Arvore geradora mínima(Prim)\n0-Voltar:");
						leitura3 = scan.next();
						if(leitura3.equals("1"))
						{
							Dijkstra dikstra = new Dijkstra();
							System.out.println("Qual será o vértice de origem? digitar índice:");
							for(int i = 0; i<importado.getVertices().size(); i++)
							{
								System.out.println(i+1+" - "+importado.getVertices().get(i).getNome());
							}
							System.out.println(dikstra.dijkstra(importado, importado.getVertices().get(scan.nextInt()-1)));
						}
						if(leitura3.equals("2"))
						{
							BuscaLargura busca = new BuscaLargura();
							
							System.out.println("Qual será o vértice de origem? digitar índice:");
							for(int i = 0; i<importado.getVertices().size(); i++)
							{
								System.out.println(i+1+" - "+importado.getVertices().get(i).getNome());
							}
							System.out.println(busca.bfs(importado,importado.getVertices().get(scan.nextInt()-1)));
						}
						if(leitura3.equals("3"))
						{
							Malgrange malgrange = new Malgrange();
							System.out.println(malgrange.malgrange(importado));
						}
						if(leitura3.equals("4"))
						{
							System.out.println(importado.getArvoreGeradora());
						}
					}
					if(leitura.equals("4"))
					{
						String leitura4 = null;
						System.out.println("1-Salvar este grafo com outro nome\n2-Substituir\n0-Voltar:");
						leitura4 = scan.next();
						
						if(leitura4.equals("1"))
						{
							System.out.println("Digite o novo nome do grafo:");	
							importado.setNome(scan.next());
							importado.salvaGrafo();
						}
						if(leitura4.equals("2"))
						{
							importado.salvaGrafo();
						}
						if(leitura4.equals("0"))
						{
							
						}
						
						
						try {
							Graph.createFileDotToPng(importado.getNome() + ".dot", importado.getNome()+".png");
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					}
				}
				
			}
					
		}
		
	static public String Menu(Scanner scan, String leitura)
	{		
		System.out.println("1-Novo Grafo");
		System.out.println("2-Abrir Grafo");
		leitura = scan.next();	
		return leitura;
	}
	
	static public String Menu1(Scanner scan, String leitura)
	{
		System.out.println("1-Criar Grafo");
		System.out.println("2-Informacoes do Grafo");
		System.out.println("0-Voltar");
		leitura = scan.next();	
		return leitura;
	}
	
	static public String Menu2(Scanner scan, String leitura, String[] nomesDosArquivos) 
	{
		int index;
		
		System.out.println("Qual grafo? digite somente o índice:");
		 for(int i=0; i<nomesDosArquivos.length; i++)
		 {
			 System.out.println(nomesDosArquivos[i]);
		 }
		 index = scan.nextInt();
		 
		importaGrafo(obterArquivo(nomesDosArquivos[index].replace(index+" - ", "")));
		return leitura = "";
	}
	
	static public String Menu21(Scanner scan, String leitura, Grafo grafo)
	{
		System.out.println("1-Informações do grafo\n2-Operações\n3-Problemas\n4-Salvar grafo\n0-Voltar:");
		leitura = scan.next();
		return leitura;
	}
	
	static public String Menu22(Scanner scan, String leitura2, Grafo grafo) 
	{
		if(leitura2.equals("1"))
		{
			System.out.println("Qual será o nome do vértice a ser adicionado?");
			try {
				Vertice vertice = new Vertice(scan.next());
				grafo.setVertices(vertice);
				System.out.println(
						"Vértice " + vertice.getNome() + " adicionado com sucesso ao grafo " + grafo.getNome() + ".");
			} catch (RuntimeException a) {
				a.printStackTrace();
			}
		}
//----------------------------------------------------------------------------------------------//
		if(leitura2.equals("2"))
		{
			Aresta aresta = new Aresta();
			System.out.println("Digite o nome de dois desses vértices ");
			for(int i=0; i<grafo.getVertices().size(); i++)
			{
				System.out.println("|"+grafo.getVertices().get(i).getNome()+"|");
			}
			System.out.println("para criar uma nova aresta entre eles. Primeiro:");
			
			try {
				Vertice vertice1 = new Vertice(grafo.getVertice(scan.next()));

				System.out.println("Segundo:");

				Vertice vertice2 = new Vertice(grafo.getVertice(scan.next()));
				
				if (vertice1 != null && vertice2 != null) 
				{
					aresta.setAresta(grafo.getVertice(vertice1.getNome()), grafo.getVertice(vertice2.getNome()));
					grafo.setArestas(aresta);
					
					if(grafo.isValorado())
					{
						System.out.println("Qual o valor da aresta "+aresta.getArestaNome()+"?");
						aresta.setValor(scan.nextInt());
					}
					System.out.println("Aresta " + aresta.getArestaNome() + " criada com sucesso!");
				}

			} catch (RuntimeException a) {
				a.printStackTrace();
			}

		}
//----------------------------------------------------------------------------------------------//
		if(leitura2.equals("3"))
		{
			System.out.println("O grafo "+grafo.getNome()+" apresenta os seguinte vértices:");
			
			for(int i=0; i<grafo.getVertices().size(); i++)
			{
				System.out.print(grafo.getVertices().get(i).getNome()+" | ");
			}
			System.out.println("\nQual deles deseja apagar?");
			
			String nomevertice = scan.next();
			
			try {
				for (int i=0; i<grafo.getVertices().size(); i++) 
				{
					if (grafo.getVertices().get(i).getNome().equals(nomevertice)) 
					{
						grafo.removeVertice(grafo.getVertices().get(i));
						System.out.println("Vértice "+ nomevertice +" removido com sucesso do grafo "+grafo.getNome());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//----------------------------------------------------------------------------------------------//
		
		if(leitura2.equals("4"))
		{
			System.out.println("O grafo "+grafo.getNome()+" apresenta as seguintes arestas:");
			
			for(int i=0; i<grafo.getArestas().size(); i++)
			{
				System.out.println("|"+grafo.getArestas().get(i).getArestaNome()+"|Índice->"+i);
			}
			
			System.out.println("Qual delas deseja apagar? (digitar apenas o índice)");
			int index = scan.nextInt();
			
			try 
			{
				grafo.removeAresta(grafo.getArestas().get(index));
			} 
			catch(RuntimeException e) 
			{
				e.getStackTrace();
				throw new RuntimeException("Aresta não existente.");

			}
			for(int i=0; i<grafo.getArestas().size(); i++)
			{
				System.out.println("|"+grafo.getArestas().get(i).getArestaNome()+"| ->"+i);
			}			
		}
		
//----------------------------------------------------------------------------------------------//
		if(leitura2.equals("5"))
		{
			System.out.println("Aqui uma lista com todos os vértices do grafo "+grafo.getNome()+":");
			for(Vertice each: grafo.getVertices())
			{
				System.out.print(each.getNome()+" | ");
			}
			System.out.println("\nDe qual deles deseja saber o f transitivo direto?");
			
			String ftrans = scan.next();
			
			System.out.println(grafo.getFTransDireto(ftrans));	
		}
		
		if(leitura2.equals("6"))
		{
			System.out.println("Aqui uma lista com todos os vértices do grafo "+grafo.getNome()+":");
			for(Vertice each: grafo.getVertices())
			{
				System.out.print(each.getNome()+" | ");
			}
			System.out.println("\nDe qual deles deseja saber o f transitivo direto?");
			
			String ftrans = scan.next();
			
			System.out.println(grafo.getFTransIndireto(ftrans));	
		}
		return leitura2;
	}

	public static String[] escolhaGrafo() //Lista os arquivos grafo.txt na pasta, os coloca num vetor de String e retorna essa String
	{
	  
	    File pasta = new File("."); //nome do diretorio
	    
	    if (pasta.exists() && pasta.isDirectory()) {
	        
	        File[] arquivos = pasta.listFiles((dir, nomeArquivo) -> nomeArquivo.toLowerCase().endsWith(".dot"));// Lista os arquivos no diretório que atendem ao critério do filtro .dot

	        if (arquivos != null && arquivos.length > 0) {
	            String[] grafotxt = new String[arquivos.length];

	            for (int i = 0; i < arquivos.length; i++) 
	            {
	                String nomeArquivo = i+ " - "+arquivos[i].getName();
		                grafotxt[i] = nomeArquivo.substring(0, nomeArquivo.lastIndexOf(".dot"));
	            }
	            return grafotxt;
	        } else {
	            System.out.println("Nenhum arquivo .txt encontrado na pasta.");
	        }
	    } else {
	        System.out.println("O diretório atual não existe ou não é uma pasta.");
	    }
	    return null;
	}

	public static File obterArquivo(String nomeArquivo) //Obtem o arquivo .dot de nome escolhido
	{
	        File pasta = new File(".");
	        File[] arquivos = pasta.listFiles((dir, nome) -> nome.equalsIgnoreCase(nomeArquivo + ".dot"));
	        
	        if (arquivos != null && arquivos.length > 0) {
	            return arquivos[0];
	        } else {
	            System.out.println("Arquivo " + nomeArquivo + ".dot não encontrado.");
	            return null;
	        }
	    }

	public static void importaGrafo(File grafodot) //Cria um novo grafo dentro do sistema a partir do arquivo .dot que contém um grafo .dot
	{
		Grafo grafo = new Grafo();
		StringBuilder grafostringbuilder= new StringBuilder();
		
		 try (BufferedReader br = new BufferedReader(new FileReader(grafodot))) {
		        String line;
		        while ((line = br.readLine()) != null) {
		            grafostringbuilder.append(line).append("\n");
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		 
		String nomegrafo = grafodot.toString().replaceFirst(".\\\\", "").replace(".dot", "");
		grafo.setNome(nomegrafo);
		
		String grafostring = grafostringbuilder.toString();
		
		if(grafostring.contains("graph"))
		{
			grafo.setOrientacao(false);
		}
			
		if(grafostring.contains("digraph"))
		{
			grafo.setOrientacao(true);
		}
		if(grafostring.contains("label"))
		{
			grafo.setValorado(true);
		}else
		{
			grafo.setValorado(false);
		}
		
		 ArrayList<String> esquerda = new ArrayList<>();
	     ArrayList<String> direita = new ArrayList<>();

	        // Padrão para encontrar elementos na esquerda e na direita
	     Pattern regex = Pattern.compile("([a-zA-Z]+)\\s*(--|->)\\s*([a-zA-Z]{1,2})");

	     Matcher matcher = regex.matcher(grafostring);
	       
	     while (matcher.find()) 
	     	{
	    	    String elementoEsquerda = matcher.group(1);
	    	    String elementoDireita = matcher.group(3);
	    	    esquerda.add(elementoEsquerda);
	    	    direita.add(elementoDireita);
	    	}
	        	       	      
	        ArrayList<String> elementosUnicos = new ArrayList<>();
	        HashSet<String> elementosSet = new HashSet<>();
	        elementosSet.addAll(esquerda);
	        elementosSet.addAll(direita);
	        elementosUnicos.addAll(elementosSet);
	        ArrayList<String> elementosUnicosList = new ArrayList<>(elementosSet);
	        
	        for(String elemento: elementosUnicosList)
	        {
	        	Vertice vertice = new Vertice(elemento);
	        	grafo.setVertices(vertice);
	        	
	        }  
	        
	        Vertice[][] verticeMatrix = new Vertice[2][esquerda.size()];
	        
	        int esquerdacount =0;
	        int direitacount =0;
	        for(int i=0; i<esquerda.size(); i++)
	        {
	        		for(int k=0; k<grafo.getVertices().size(); k++)
	        		{
	        			if(verticeMatrix[0][esquerdacount] != null)
	        			{
	        				break;
	        			}
	        			else if(esquerda.get(i).equals(grafo.getVertices().get(k).getNome()))
		        		{
	        				verticeMatrix[0][esquerdacount] = grafo.getVertices().get(k);
		        		}
	        		}
	        		
	        		for(int k=0; k<grafo.getVertices().size(); k++)
	        		{
	        			if(verticeMatrix[1][direitacount] != null)
	        			{
	        				break;
	        			}
	        			else if(direita.get(i).equals(grafo.getVertices().get(k).getNome()))
		        		{
	        				verticeMatrix[1][direitacount] = grafo.getVertices().get(k);
		        		}
	        		} 	
	        		
	        		esquerdacount++;
	        		direitacount++;
	        }
	         	        	        
	        for(int i =0; i<esquerda.size(); i++)
	        {  	
	        	Aresta aresta = new Aresta(verticeMatrix[0][i], verticeMatrix[1][i]);
	        	grafo.setArestas(aresta);
	        }
       	        
	        //------------------------------------------------------------------------------------------
        	
        	ArrayList<Integer> labelvalor = new ArrayList<>();
        	Integer target;
        	if(grafo.isValorado())
        	{
        		regex = Pattern.compile("label=\\s*(\\d+)");
        	     matcher = regex.matcher(grafostring);
        	     
        	     while (matcher.find()) 
     	     	{
        	    	 target = Integer.parseInt(matcher.group(1));
        	    	 labelvalor.add(target);
        	    	
     	    	}
        	     
        	    for(int i=0; i<labelvalor.size(); i++)
        	    {
        	    	grafo.getArestas().get(i).setValor(labelvalor.get(i));
        	    }
        	     
        	}
        	
        //ArmazenaGrafo.add(grafo);
        ArmazenaGrafo.replaceAllTo(grafo);
        System.out.println("grafo "+grafo.getNome()+" aberto com sucesso!\n");
	}
}