package GraphL2.L010;
import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] st = br.readLine().split(" ");
    int m = Integer.parseInt(st[0]);
    int n = Integer.parseInt(st[1]);
    int q = Integer.parseInt(st[2]);

    int[][] pos = new int[q][2];
    for (int i = 0; i < q; i++) {
      st = br.readLine().split(" ");
      pos[i][0] = Integer.parseInt(st[0]);
      pos[i][1] = Integer.parseInt(st[1]);
    }

    System.out.println(numIslands2(m, n, pos));
  }

  public static List<Integer> numIslands2(int m, int n, int[][] positions) {
    ArrayList<Integer> ans = new ArrayList<>();
    int count=0;
    int par[]= new int[m*n];
    int rank[]= new int[m*n];
    int dir[][]={{0,1},{0,-1},{1,0},{-1,0}};

    Arrays.fill(par, -1);

    for(int i=0;i<positions.length;i++)
    {
        int row= positions[i][0];
        int col= positions[i][1];

        int cell= row*n+col;
        if(par[cell]!=-1)
        {
            ans.add(count);
            continue;
        }
        par[cell]=cell;
        rank[cell]=1;
        count++;

        for(int k=0;k<4;k++)
        {
            int rd= row+dir[k][0];
            int cd= col+dir[k][1];

            int celld= rd*n+cd;

            if(rd<0 ||rd>=m || cd<0 || cd>=n || par[celld]==-1)
            {
                continue;
            }

            int lx= find(cell, par);
            int ly = find(celld, par);

            if(lx!=ly)
            {
                if(rank[lx]>rank[ly])
                {
                    par[ly]=lx;
                }
                else if(rank[lx]<rank[ly])
                {
                    par[lx]=ly;
                }
                else
                {
                    par[lx]=ly;
                    rank[ly]++;
                }
                count--;
            }

        }
        ans.add(count);


    }
    return ans;

  }

  public static int find(int x, int par[])
  {
    if(par[x]==x)
    {
        return x;
    }
    int temp= find(par[x],par);
    par[x]=temp;
    return temp;
  }

}