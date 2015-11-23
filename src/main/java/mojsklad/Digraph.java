package mojsklad;

import java.util.LinkedList;

public class Digraph {
    private int V;
    private int E;
    private LinkedList<Integer>[] adj;
    private int[] indegree;

    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException();
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException();
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        E++;
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public void deleteEdge(int v, int w) {
        adj[v].remove(adj[v].indexOf(w));
        indegree[w]--;
        E--;
    }

}

