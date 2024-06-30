package GraphL2.L006;
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws Exception {
        Scanner scn= new Scanner(System.in);
        int v= scn.nextInt();
        int e= scn.nextInt();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        ArrayList<ArrayList<Integer>> revgraph = new ArrayList<>();
        for(int i=0;i<v;i++)
        {
            graph.add(new ArrayList<>());
            revgraph.add(new ArrayList<>());
        }

        for(int i=0;i<e;i++)
        {
            int a= scn.nextInt()-1;
            int b= scn.nextInt()-1;
            graph.get(a).add(b);
            revgraph.get(b).add(a);  // creating a rev graph 
        }

        // random order dfs

        boolean vis[]= new boolean[v];
        Stack<Integer> st= new Stack<>();
        for(int i=0;i<vis.length;i++)
        {
            if(vis[i]==false)
            {
                dfs1(i,graph,st,vis);
            }
        }

        // remove from stack and apply dfs and calculate ans parallelly

        int ans=0;
        vis= new boolean[v];
        while(st.size()!=0)
        {
            int rem= st.pop();
            if(vis[rem]==false)
            {
                dfs2(rem, revgraph, vis);
                ans++;
            }
            

        }

        System.out.println(ans);

	}
    public static void dfs1(int i, ArrayList<ArrayList<Integer>> graph, Stack<Integer> st, boolean vis[])
    {
        vis[i]=true;
        ArrayList<Integer> nbrs= graph.get(i);
        for(int nbr: nbrs)
        {
            if(vis[nbr]==false)
            {
                dfs1(nbr,graph,st,vis);
            }
        }
        st.push(i);
    }

    public static void dfs2(int i, ArrayList<ArrayList<Integer>> graph, boolean vis[])
    {
        vis[i]=true;
        ArrayList<Integer> nbrs= graph.get(i);
        for(int nbr: nbrs)
        {
            if(vis[nbr]==false)
            {
                dfs2(nbr,graph,vis);
            }
        }
        
    }

}
