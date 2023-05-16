package grafo.dirigido;

import java.util.LinkedList;
import java.util.Queue;

public class BFSIterator<T> implements GrafoIterator<T> {
	
	private Queue<Vertice<T>> fila;
    private boolean[] visited;
    private Grafo<T> grafo;
    
    public BFSIterator(Grafo<T> grafo, T carga) {
        this.grafo = grafo;
        Vertice<T> verticeInicial = getVertice(carga);
        this.fila = new LinkedList<>();
        this.visited = new boolean[grafo.getVertices().size()];
        fila.add(verticeInicial);
        visited[grafo.getVertices().indexOf(verticeInicial)] = true;
    }
	
	@Override
	public boolean hasNext() {
		return  !fila.isEmpty();
	}

	@Override
    public Vertice<T> next() {
		
        Vertice<T> currentVertice = fila.remove();
        
        int index = grafo.getVertices().indexOf(currentVertice);
        for (Aresta<T> aresta : grafo.getArestas()) {
            if (aresta.getOrigin().equals(currentVertice) && !visited[grafo.getVertices().indexOf(aresta.getDestiny())]) {
                visited[grafo.getVertices().indexOf(aresta.getDestiny())] = true;
                fila.add(aresta.getDestiny());
            }
        }
        return currentVertice;
	}

	
	
	public Vertice<T> getVertice(T carga){
		
        for (Vertice<T> u : grafo.getVertices()) {
            if (u.getCarga().equals(carga))
                return u;
        }
        
        return null;
    }
	
	

}
