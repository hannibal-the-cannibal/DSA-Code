package GraphL2.L008;
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

    dfs(0, graph);
    int ans=0;
    for(int i=0;i<arti.length;i++)
    {
        if(arti[i]==true)
        {
            ans++;
        }
    }

    System.out.println(ans);


  }
  public static void dfs(int u, ArrayList<ArrayList<Integer>> graph )
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
            dfs(nbr,graph);
            //backtracking now

            low[u]=Math.min(low[u], low[nbr]);
            // now marking articilation point 

            if(par[u]==-1)
            {
                // actual source
                if(count>=2)
                {
                    arti[u]=true;
                }
            }
            else
            {
                if(low[nbr]>=disc[u])
                {
                    arti[u]=true;
                }
            }
        }
     }


  }

}