/*
* Dijkstra's shortest path algorithm
* Dependency : IndexMinPQ.java Edge.java WeightedGraph.java
*/
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Sp {

    private Edge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;


    public Sp(WeightedGraph G, int s){

        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<Double>(G.V());

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        pq.insert(s, distTo[s]);
        while (!pq.isEmpty())
        {
            int v = pq.delMin();
            for (Edge e : G.adj(v))
                relax(e, v);
            // System.out.println(Arrays.toString(distTo));

        }
    }


    private void relax(Edge e, int v) {
        int w = e.other(v);
               
        if (distTo[w] > distTo[v] + e.weight()){
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else   pq.insert(w, distTo[w]);
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public static void main(String[] args){
        String file = args[0];
        int s = Integer.parseInt(args[1]);
        int t = Integer.parseInt(args[2]);

        try {
            // System.out.println(file);
            List<String> lines = Files.readAllLines(Paths.get(file));
            WeightedGraph G = new WeightedGraph(lines);
            // System.out.println(G);

            // compute shortest paths
            Sp sp = new Sp(G, s);
            System.out.println(sp.distTo[t]);

        } catch (IOException e){
            e.printStackTrace();
        }
    }


    

}