package GraphL2.L003;

import java.util.*;
import java.io.*;

public class Main {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] st = br.readLine().split(" ");
    int v = Integer.parseInt(st[0]);
    int e = Integer.parseInt(st[1]);

    int[] wells = new int[v];
    String[] words = br.readLine().split(" ");

    for (int i = 0; i < wells.length; i++) {
      wells[i] = Integer.parseInt(words[i]);
    }

    int[][] pipes = new int[e][3];
    for (int i = 0; i < e; i++) {
      String[] st1 = br.readLine().split(" ");
      pipes[i][0] = Integer.parseInt(st1[0]);
      pipes[i][1] = Integer.parseInt(st1[1]);
      pipes[i][2] = Integer.parseInt(st1[2]);

    }

    System.out.println(minCostToSupplyWater(v, wells, pipes));
  }

  static int[] parent;
  static int[] rank;

  public static class Pair implements Comparable<Pair> {
    int nbr;
    int wt;

    Pair(int nbr, int wt) {
      this.nbr = nbr;
      this.wt = wt;
    }

    @Override
    public int compareTo(Pair o) {
      return this.wt - o.wt;
    }
  }

  public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
    ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
    for(int i=0;i<=n;i++)
    {
        graph.add(new ArrayList<>());
    }
    for(int i=0;i<pipes.length;i++)
    {
        int u= pipes[i][0];
        int v= pipes[i][1];
        int wt= pipes[i][2];

        graph.get(u).add(new Pair(v, wt));
        graph.get(v).add(new Pair(u, wt));
    }
    for(int i=1;i<=wells.length;i++)
    {
        graph.get(i).add(new Pair(0, wells[i-1]));
        graph.get(0).add(new Pair(i, wells[i-1]));
    }

    int ans=0;
    PriorityQueue<Pair> pq= new PriorityQueue<>();
    pq.add(new Pair(0, 0));
    boolean vis[]= new boolean[n+1];
    while(pq.size()!=0)
    {
        Pair rem = pq.remove();
        if(vis[rem.nbr]==true)
        {
            continue;
        }
        ans+=rem.wt;
        vis[rem.nbr]=true;

        ArrayList<Pair> neighbours= graph.get(rem.nbr);
        for(Pair nb: neighbours)
        {
            if(vis[nb.nbr]==false)
            {
                pq.add(nb);
            }
        }
    }

    return ans;

  }
}
