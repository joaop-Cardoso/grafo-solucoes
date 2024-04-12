package grafos;
import java.util.Scanner;

import br.com.davesmartins.api.Graph;

import java.io.IOException;
import java.util.ArrayList;

abstract public class CriaGrafo {

	static void CriarGrafo(Grafo grafo, Scanner scan, String leitura)
	{
		System.out.println("Qual o nome desse grafo?");
		
		grafo.setNome(scan.next());
		
//============================================Orientação=========================================//
		
		System.out.println("Este grafo é orientado ou não orientado? 1-Orientado, 0-Não Orientado");
		
		leitura = scan.next();
		if(leitura.equals("1"))
		{
			grafo.setOrientacao(true);
		}
		if(leitura.equals("0"))
		{
			grafo.setOrientacao(false);
		}
		
//=============================================Valoração=========================================//
		
		System.out.println("Este grafo é valorado ou não? 1-Sim, valorado 0-Não");
		
		leitura = scan.next();
		if(leitura.equals("1"))
		{
			grafo.setValorado(true);
		}
		if(leitura.equals("0"))
		{
			grafo.setValorado(false);
		}
		
//==============================================Vértices=========================================//
		
		System.out.println("Quais são os vértices desse grafo?");
		
		String entrada = "1";
		
		Vertice vertice = new Vertice(scan.next());
		grafo.setVertices(vertice);
		
		while(!entrada.equals("0"))
		{
			System.out.println("Próximo(obs: quando não houverem mais, teclar 0):");
			entrada = scan.next();
			
			if(entrada.equals("0"))
			{
				break;
			}
			Vertice vertice1 = new Vertice(entrada);
			grafo.setVertices(vertice1);
		}
		
		int entradaint;
		if(grafo.valorado == true)
		{
			for(int i =0; i<grafo.getVertices().size(); i++)
			{
				System.out.println("Qual o valor do vértice "+grafo.getVertices().get(i).getNome() + "? \n(Se os vértices não forem valorados, digitar -1)");
				entradaint = scan.nextInt();
				
				if(entradaint == -1)
				{
					break;
				}
				else
				{
						grafo.getVertices().get(i).setValor(entradaint);
				}
				
			}
		}
		

//=============================================Arestas===========================================//
		
		System.out.println("A quais vértices a primeira aresta se liga? Ex:(a*ENTER*b*ENTER*)");

		while (true) 
		{
			System.out.println("Digite o primeiro vértice da aresta (ou 0 para encerrar):");
			String primeiroVertice = scan.next();

			if (primeiroVertice.equals("0")) 
			{
				break; // Sai do loop se "0" for inserido como o primeiro vértice
			}

			System.out.println("Digite o segundo vértice (ou 0 para encerrar):");
			String segundoVertice = scan.next();

			if (segundoVertice.equals("0")) 
			{
				break; // Sai do loop se "0" for inserido como o segundo vértice
			}

			Vertice[] target = new Vertice[2];
			target[0] = new Vertice();
			target[1] = new Vertice();

			Aresta aresta = new Aresta();

			for (int i = 0; i < grafo.getVertices().size(); i++) // for percorrendo o tamanho da lista de vertices
			{
				if (grafo.getVertices().get(i).getNome().equals(primeiroVertice)) //se o valor do vertice-indice que ta guardado em grafo fo igual ao valor de primeiroVertice
				{ 
					try {
						target[0].SetVertice(grafo.getVertices().get(i).getNome());
					} catch (Exception e) {
						
						e.printStackTrace();
					}  //adiciona o vertice-indice que ta guardado em grafo que tem o valor igual a primeiroVertice em target[0]
				}

				if (grafo.getVertices().get(i).getNome().equals(segundoVertice)) 
				{
					try {
						target[1].SetVertice(grafo.getVertices().get(i).getNome());
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				}
			}

			if (target[0] != null && target[1] != null) 
			{
				aresta.setAresta(target);
				grafo.setArestas(aresta);
				
				/*if(grafo.isOrientado()) //Condicional que serve para adicionar a ordem da ligação de forma invertida se as arestas do grafo são unidirecionais
				{
					Vertice[] troca = new Vertice[2];
					troca[0] = new Vertice();
					troca[1] = new Vertice();
					
					troca[0] = target[1];
					troca[1] = target[0];
					
					Aresta aresta2 = new Aresta();
					
					aresta2.setAresta(troca);
					grafo.setArestas(aresta2);
				}*/
				
			}

			System.out.println("Próxima aresta (obs: quando não houverem mais, teclar 0):");
		}
		
		if(grafo.valorado == true)
		{
			for(int i =0; i<grafo.getArestas().size(); i++)
			{
				System.out.println("Qual o valor da aresta "+grafo.getArestas().get(i).getArestaNome() + "?"); 
				grafo.getArestas().get(i).setValor(scan.nextInt());			
			}
		}
		
//==============================================================================================================================
		//ArmazenaGrafo.add(grafo);
		grafo.salvaGrafo();
		
		
		try {
			Graph.testDotToPng();
			Graph.createFileDotToPng(grafo.getNome() + ".dot", grafo.getNome()+".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
