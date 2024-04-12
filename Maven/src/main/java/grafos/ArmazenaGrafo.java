package grafos;
import java.util.ArrayList;

abstract public class ArmazenaGrafo{
	
	static private ArrayList<Grafo> grafos = new ArrayList<Grafo>();
	
	static public void add(Grafo grafo)
	{
		grafos.add(grafo);
	}
	
	static public void replaceAllTo(Grafo grafo)
	{
		if(grafos.isEmpty())
		{
			grafos.add(grafo);
		}
		else
		{
			for(int i=0;i<grafos.size();i++)
			{
				grafos.remove(i);
			}
			grafos.add(grafo);
		}

	}
	
	static public Grafo getGraphZero()
	{
		return grafos.get(0);
	}

}
