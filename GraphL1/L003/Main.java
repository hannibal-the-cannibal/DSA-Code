package GraphL1.L003;
import java.beans.Visibility;
import java.io.*;
import java.util.*;

public class Main {
   static class Edge {
      int src;
      int nbr;
      int wt;

      Edge(int src, int nbr, int wt) {
         this.src = src;
         this.nbr = nbr;
         this.wt = wt;
      }
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      System.out.println(getallcomp(graph));
   }
   public static ArrayList<ArrayList<Integer>> getallcomp(ArrayList<Edge>[]graph)
   {
     ArrayList<ArrayList<Integer>> bigans = new ArrayList<>();
     boolean[]visited= new boolean[graph.length];

     for(int i=0;i<visited.length;i++)
     {
        if(visited[i]==false)
        {
            ArrayList<Integer> smallans= new ArrayList<>();
            getcomp(smallans,graph,visited,i);
            bigans.add(smallans);
        }
     }
     return bigans;
   }
   public static void getcomp(ArrayList<Integer>smallans, ArrayList<Edge>[]graph, boolean[]visited, int src )
   {
      visited[src]=true;
      smallans.add(src);
      for(Edge e: graph[src]) 
      {
         if(visited[e.nbr]==false)
         {
               getcomp(smallans, graph, visited, e.nbr);
         }
      }
   }

   ////////////////////////////////////////////////////////////////////////////////////////////////////////////

   
}