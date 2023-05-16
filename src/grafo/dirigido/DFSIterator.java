package grafo.dirigido;

import java.util.Stack;

public class DFSIterator<T> implements GrafoIterator<T>{

	private Stack<Vertice<T>> pilha;
    private boolean[] visited;
    private Grafo<T> grafo;

    public DFSIterator(Grafo<T> grafo, T carga) {
        this.grafo = grafo;
        this.pilha = new Stack<>();
        Vertice<T> verticeInicial = getVertice(carga);
        this.visited = new boolean[grafo.getVertices().size()];
        pilha.push(verticeInicial);
        visited[grafo.getVertices().indexOf(verticeInicial)] = true;
    }

    public boolean hasNext() {
        return !pilha.isEmpty();
    }

    public Vertice<T> getVertice( T carga){
        for (Vertice<T> u : grafo.getVertices()) {
            if ( u.getCarga().equals( carga ))
                return u;
        }
        return null;
    }

    public Vertice<T> next() {
    	
        Vertice<T> currentVertice = pilha.pop();
        
        int index = grafo.getVertices().indexOf(currentVertice);
        for (Aresta<T> aresta : grafo.getArestas()) {
            if (aresta.getOrigin().equals(currentVertice) && !visited[grafo.getVertices().indexOf(aresta.getDestiny())]) {
                visited[grafo.getVertices().indexOf(aresta.getDestiny())] = true;
                pilha.push(aresta.getDestiny());
            }
        }
        return currentVertice;
    }
}

