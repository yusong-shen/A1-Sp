/*
 The Edge class represent an edge in an weighted graph
*/
import java.util.*;

public class Edge {
	private final int v;
	private final int w;
	private final double weight;

    public Edge(int v, int w, double weight) {
        if (v < 0) throw new IndexOutOfBoundsException("Vertex names must be nonnegative integers");
        if (w < 0) throw new IndexOutOfBoundsException("Vertex names must be nonnegative integers");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }
    

    public double weight() {
    	return weight;
    }

    public String toString() {
        return v + "-" + w + " " + String.format("%5.2f", weight);
    }    

    public static void main(String[] args) {
        Edge e = new Edge(12, 34, 5.67);
        Edge e1 = new Edge(123, 345, 45.67);
        System.out.println(e);
        ArrayList<Edge> list = new ArrayList<Edge>();
        list.add(e);
        list.add(e1);
        for (Edge item : list){
        	System.out.println(item);
        }
    }    
}