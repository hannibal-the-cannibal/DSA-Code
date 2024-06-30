package GraphL1.L001;
import java.io.*;
import java.util.*;

public class Main {
   static class Edge {
      int src;
      int nbr;
      int wt;

      Edge(int src, int nbr, int wt){
         this.src = src;
         this.nbr = nbr;
         this.wt = wt;
      }
   }
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for(int i = 0; i < vtces; i++){
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for(int i = 0; i < edges; i++){
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      int src = Integer.parseInt(br.readLine());
      int dest = Integer.parseInt(br.readLine());
    //   System.out.println(haspath(graph,src,dest, new boolean[vtces]));
    printallpath(graph, src, dest, new boolean[vtces], ""+src);


    }

    public static boolean haspath(ArrayList<Edge>[]graph, int src, int dest, boolean[] visted)
    {
        if(src==dest)
        {
            return true;
        }

        visted[src]=true;

        for(Edge e: graph[src])
        {
            if(visted[e.nbr]==false)
            {
                boolean rec= haspath(graph, e.nbr, dest, visted);
                if(rec)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printallpath(ArrayList<Edge>[]graph, int src, int dest, boolean[] visited, String asf)
    {
        if(src==dest)
        {
            System.out.println(asf);
            return;
        }
        visited[src]=true;
        for(Edge e: graph[src])
        {
            if(visited[e.nbr]==false)
            {
                printallpath(graph, e.nbr, dest, visited, asf+e.nbr);
            }
        }
        visited[src]=false; // backtracking 
    }

}