package GraphL2.L009;
import java.util.*;
import java.io.*;

class Main {
   static int par[];
   static int low[];
    static int disc[];
   static boolean arti[];
   static  boolean vis[];
    static int time;

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int v= scn.nextInt();
    int e= scn.nextInt();

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for(int i=0;i<v;i++)
    {
        graph.add(new ArrayList<>());
    }

    for(int i=0;i<e;i++)
    {
        int a=scn.nextInt()-1;
        int b= scn.nextInt()-1;

        graph.get(a).add(b);
        graph.get(b).add(a);

    }

    par= new int[v];
    low=new int[v];
    time=0;
    disc= new int[v];
    arti= new boolean[v];
    vis= new boolean[v];

    par[0]=-1; // src parent
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();


    dfs(0, graph,ans);

    for(ArrayList<Integer> a: ans)
    {
        System.out.println(a);
    }
    


  }
  public static void dfs(int u, ArrayList<ArrayList<Integer>> graph, ArrayList<ArrayList<Integer>> ans )
  {
     // intialaize low and disc
     low[u]=disc[u]=time;
     time++;
     vis[u]=true; //caliing dfs

     int count=0;

     ArrayList<Integer> nbrs =graph.get(u);
     for(int i=0;i<nbrs.size();i++)
     {
        int nbr= nbrs.get(i);

        // nbr can be of 3 categories 
        if(par[u]==nbr)
        {
            continue;
        }
        else if(vis[nbr]==true)
        {
            low[u]= Math.min(low[u], disc[nbr]);
        }
        else
        {
            par[nbr]=u;
            count++;
            dfs(nbr,graph,ans);
            //backtracking now

            low[u]=Math.min(low[u], low[nbr]);
            // now marking articilation point 

          
                if(low[nbr]>disc[u])
                {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(u);
                    temp.add(nbr);
                    ans.add(temp);
                }
            
        }
     }


  }

}