package GraphL2.L007;
import java.io.*;
import java.util.*;


public class Main{
    	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] st = br.readLine().split(" ");
		int n = Integer.parseInt(st[0]);
		int m = Integer.parseInt(st[1]);

		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = br.readLine().split(" ");
			int u = Integer.parseInt(st[0]) - 1;
			int v = Integer.parseInt(st[1]) - 1;
			graph.get(u).add(v);
		}

		System.out.println(findMotherVertex(n, graph));
	}
    public static int findMotherVertex(int N, ArrayList<ArrayList<Integer>> graph){
        Stack<Integer> st = new Stack<>();
        boolean vis[]= new boolean[N];
        for(int i=0;i<vis.length;i++)
        {
            if(vis[i]==false)
            {
                dfs1(i, graph, st, vis);
            }
        }

        int ans= st.pop();
        vis= new boolean[N];
        count=0;
        dfs2(ans, graph, vis);

        if(count==N)
        {
            return ans+1;
        }
        else
        {
            return -1;
        }


			
    }
    static int count;
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
        count++;
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