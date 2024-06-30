package GraphL1.L005;
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
   public static class Pair{
    int v;
    int lvl;
    Pair(int v, int lvl)
    {
        this.v=v;
        this.lvl=lvl;
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
      int []visited= new int[graph.length];
      Arrays.fill(visited,-1);

      for(int i=0;i<visited.length;i++)
      {
        if(visited[i]==-1)
        {
            boolean rec= isbipartite(graph,i,visited);
            if(rec==false)
            {
                System.out.println("false");
                return ;
            }
        }
      }
      System.out.println("true");
   }

   public static boolean isbipartite(ArrayList<Edge>graph[], int src, int[]visited)
   {
      Queue<Pair> qu= new ArrayDeque<>();
      qu.add(new Pair(src, 0));

      while(qu.size()!=0)
      {
        Pair rem= qu.remove();
        if(visited[rem.lvl]!=-1)
        {
            if(rem.lvl!=visited[rem.v])
            {
                return false;
            }
        }
        else
        {
            visited[rem.v]=rem.lvl;
        }
        for(Edge e: graph[rem.v])
        {
            if(visited[e.nbr]==-1)
            {
                qu.add(new Pair(e.nbr, rem.lvl+1));
            }
        }
      }
      return true;

   }
}