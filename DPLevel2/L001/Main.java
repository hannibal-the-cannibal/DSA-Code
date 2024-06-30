package DPLevel2.L001;
import java.io.*;
import java.util.*;

public class Main {
    public static class Pair{
        int i;
        int j;
        int s;
        String psf;
        Pair(int i, int s ,int j, String psf)
        {
            this.i=i;
            this.j=j;
            this.s=s;
            this.psf=psf;
        }
    }
	
	public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++)
            arr[i] = scn.nextInt();

        minjumppath(arr);
        scn.close();
	}
    
	public static int largestsubmatrix(int[][] arr)
    {
        int mem[][]= new int[arr.length][arr[0].length-1];
        int ans=0;

        for(int i=mem.length-1;i>=0;i--)
        {
            for(int j=mem[0].length-1;j>=0;j--)
            {
                if(i==mem.length-1 && j==mem[0].length-1)
                {
                    mem[i][j]=arr[i][j];
                }
                else if(i==mem.length-1)
                {
                    mem[i][j]=arr[i][j];
                }
                else if(j==mem[0].length-1)
                {
                    mem[i][j]=arr[i][j];
                }
                else
                {
                    if(arr[i][j]==0)
                    {
                        mem[i][j]=0;
                    }
                    else
                    {
                        mem[i][j]=Math.min(Math.min(mem[i+1][j],mem[i][j+1]),mem[i+1][j+1])+1;
                        ans= Math.max(ans, mem[i][j]);
                    }
                }
            }
        }
        return ans;
	}

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static void minjumppath(int arr[])
    {
        int n=arr.length;
        int mem[]= new int [arr.length];
        mem[n-1]=0;

        for(int stair=n-2;stair>=0;stair--)
        {
            int maxjump=arr[stair];
            if(maxjump==0)
            {
                mem[stair]=Integer.MAX_VALUE;
            }
            else
            {
                int min =Integer.MAX_VALUE;
                for(int jump=1;jump<=maxjump && stair+jump<n; jump++)
                {
                    min=Math.min(min,mem[stair+jump]);
                }
                if(min!=Integer.MAX_VALUE)
                {
                    mem[stair]=min+1;
                }
                else
                {
                    mem[stair]=Integer.MAX_VALUE;
                }
            }
        }
        System.out.println(mem[0]);

        ArrayDeque<Pair> qu= new ArrayDeque<>();
        qu.add(new Pair(0, arr[0], mem[0], 0 +" "));

        while(qu.size()!=0)
        {
            Pair rem= qu.remove();
            if(rem.j==0)
            {
                System.out.println(rem.psf+" ");
            }

            for(int j=1; j<=rem.s && rem.i+j<arr.length; j++)
            {
                int curr= rem.i+j;
                if(mem[curr]!=Integer.MAX_VALUE && mem[curr]==rem.j-1)
                {
                    qu.add(new Pair(curr, arr[curr], mem[curr], rem.psf+"-> "+ curr));
                }
            }
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}