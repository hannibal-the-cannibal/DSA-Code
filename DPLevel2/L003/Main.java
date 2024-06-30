package DPLevel2.L003;
import java.io.*;
import java.util.*;

public class Main {

    public static class Pair{
        int i;
        int j;
        String psf;

        public Pair(int i, int j, String psf){
            this.i = i;
            this.j = j;
            this.psf = psf;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] values = new int[n];
        String str1 = br.readLine();
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(str1.split(" ")[i]);
        }

        int[] wts = new int[n];
        String str2 = br.readLine();
        for (int i = 0; i < n; i++) {
            wts[i] = Integer.parseInt(str2.split(" ")[i]);
        }

        int cap = Integer.parseInt(br.readLine());
        printknapsack(values,wts,cap);

    }

    public static void printtargetsumsubset(int arr[], int tar)
    {
        boolean mem[][]= new boolean [arr.length+1][tar+1];
        for(int i=0;i<mem.length;i++)
        {
            for(int j=0;j<mem[0].length;j++)
            {
                if(j==0)
                {
                    mem[i][j]=true;
                }
                else if(i==0)
                {
                    mem[i][j]=false;
                }
                else
                {
                    if(mem[i-1][j]==true)
                    {
                        mem[i][j]=true;
                    }
                    else if(j>=arr[i-1])
                    {
                        if(mem[i-1][j-arr[i-1]]==true)
                        {
                            mem[i][j]=true;
                        }
                    }
                }
            }
        }

        System.out.println(mem[arr.length][tar]);


        ArrayDeque<Pair> qu= new ArrayDeque<>();
        qu.add(new Pair(arr.length,tar,""));
        while(qu.size()!=0)
        {
            Pair rem= qu.remove();
            if(rem.i==0 && rem.j==0)
            {
                System.out.println(rem.psf);
            }
            else
            {
                boolean exc= mem[rem.i-1][rem.j];
                if(exc)
                {
                    qu.add(new Pair(rem.i-1, rem.j,rem.psf));
                }
                if(rem.j>=arr[rem.i-1])
                {
                    boolean inc= mem[rem.i-1][rem.j-arr[rem.i-1]];
                    if(inc)
                    {
                        qu.add(new Pair(rem.i-1, rem.j-arr[rem.i-1], (rem.i-1)+" "+rem.psf));   
                    }
                }
            }

            
            
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void printknapsack(int[] values, int[] wts, int cap)
    {
        int mem[][]= new int[values.length+1][cap+1];
        for(int i=1;i<mem.length;i++)
        {
            for(int j=1;j<mem[0].length;j++)
            {
                mem[i][j]=mem[i-1][j];

                if(j>=wts[i-1])
                {
                    if(mem[i-1][j-wts[i-1]]+values[i-1]> mem[i-1][j])
                    {
                        mem[i][j]= mem[i-1][j-wts[i-1]]+values[i-1];
                    }
                }
            }
        }
        int ans= mem[values.length][cap];
        System.out.println(ans);

        ArrayDeque<Pair> qu= new ArrayDeque<>();
        qu.add(new Pair(values.length, cap,""));

        while(qu.size()!=0)
        {
            Pair rem = qu.remove();

            if(rem.i==0 || rem.j==0)
            {
                System.out.println(rem.psf);
            }
            else
            {
                int exc= mem[rem.i-1][rem.j];
                int inc=0;
                if(rem.j>=wts[rem.i-1])
                {
                    inc= mem[rem.i-1][rem.j-wts[rem.i-1]]+values[rem.i-1];
                }

                if(mem[rem.i][rem.j]==inc)
                {
                    qu.add(new Pair(rem.i-1, rem.j-wts[rem.i-1], rem.psf+(rem.i-1)+" "));
                }
                if(mem[rem.i][rem.j]==exc)
                {
                    qu.add(new Pair(rem.i-1,rem.j, rem.psf));
                }
               
            }
        }
    }
}


                        


                        