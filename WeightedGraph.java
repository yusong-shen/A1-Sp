/*
 Use adjancency list with Hashmap to represent the graph
*/
import java.util.*;
import java.io.*;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WeightedGraph {
    private static final String NEWLINE = "\n";

    private int E;                      // number of edges in this digraph
    private int V;                      // number of vertex in this digraph
	private HashMap<Integer, ArrayList<Edge>> adj = new HashMap<Integer, ArrayList<Edge>>();    // adj.get(v) = adjacency list for vertex v
	private Set<Integer> vSet = new HashSet<Integer>();;

    public WeightedGraph(List<String> graph) {
        E = Integer.parseInt(graph.get(0));
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
        	String line = graph.get(i+1);
        	String[] parts = line.split(" ");
            int v = Integer.parseInt(parts[0]);
            int w = Integer.parseInt(parts[1]);
            // if (v < 0 || v >= V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
            // if (w < 0 || w >= V) throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (V-1));
            double weight = Double.parseDouble(parts[2]);
            addEdge(new Edge(v, w, weight));
        }
        V = adj.keySet().size();
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        if (adj.containsKey(v)){
        	adj.get(v).add(e);
    	} else {
    		ArrayList<Edge> adjList = new ArrayList<Edge>();
    		adj.put(v, adjList);
    		adj.get(v).add(e);
    	}
        if (adj.containsKey(w)){
            adj.get(w).add(e);
        } else {
            ArrayList<Edge> adjList = new ArrayList<Edge>();
            adj.put(w, adjList);
            adj.get(w).add(e);            
        }
    }

    public Iterable<Edge> adj(int v) {
        return adj.get(v);
    }

    public int V(){
    	return V;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            if (adj.containsKey(v)) {
	            for (Edge e : adj.get(v)) {
	                s.append(e + "  ");
	            }
	            s.append(NEWLINE);
        	}
        }
        return s.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println(args[0]);
            List<String> lines = Files.readAllLines(Paths.get(args[0]));
	        WeightedGraph G = new WeightedGraph(lines);
	        System.out.println(G);
        } catch (IOException e){
            e.printStackTrace();
        }

    }    
}