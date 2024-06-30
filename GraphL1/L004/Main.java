package GraphL1.L004;
import java.io.*;
import java.util.*;

public class Main {
   static class Edge {
      int src;
      int nbr;

      Edge(int src, int nbr) {
         this.src = src;
         this.nbr = nbr;
      }
   }
   public static class Pair{
    int vtx;
    String psf;
    Pair(int vtx, String psf)
    {
        this.vtx=vtx;
        this.psf=psf;
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
         graph[v1].add(new Edge(v1, v2));
         graph[v2].add(new Edge(v2, v1));
      }

    //   int src = Integer.parseInt(br.readLine());
    //   bfs(graph, src);
    // System.out.println(isgraphcyclic(graph));
   }
   public static void bfs(ArrayList<Edge>[]graph, int src)
   {
     Queue<Pair> qu= new ArrayDeque<>();
     qu.add(new Pair(src, ""+src));
     boolean visited[]=new boolean[graph.length];

     while(qu.size()!=0)
     {
        Pair rem= qu.remove();
        if(visited[rem.vtx]==false)
        {
            visited[rem.vtx]=true;
            System.out.println(rem.vtx+"@"+rem.psf);
            for(Edge e: graph[rem.vtx])
            {
                if(visited[e.nbr]==false)
                {
                    qu.add(new Pair(e.nbr, rem.psf+e.nbr));
                }
            }
        }
     }
   }

   ////////////////////////////////////////////////////////////////////////////////////////////

   public static boolean isgraphcyclic(ArrayList<Edge>graph[])
   {
     boolean[] vis= new boolean[graph.length];
     for(int i=0;i<vis.length;i++)
     {
        if(vis[i]==false)
        {
            boolean rec=iscompcyclic(graph,i,vis);
            if(rec)
            {
                return true;
            }
        }
     }
     return false;
   }
   public static boolean iscompcyclic(ArrayList<Edge>graph[], int vtx, boolean vis[])
   {
     Queue<Integer> qu= new ArrayDeque<>();
     qu.add(vtx);
     while(qu.size()!=0)
     {
        int rem= qu.remove();
        if(vis[rem]==true)
        {
            return true;
        }
        else
        {
            vis[rem]=true;
            for(Edge e: graph[rem])
            {
                if(vis[e.nbr]==false)
                {
                    qu.add(e.nbr);
                }
            }
        }
     }
     return false;
   }

   ///////////////////////////////////////////////////////////////////////////////////////////////////

   

}


