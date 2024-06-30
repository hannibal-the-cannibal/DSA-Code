package DPLevel2.L004;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
    
    public static class Pair {
        int l;
        int i;
        int v;
        String psf;
        
        Pair(int l, int i, int v, String psf){
            this.l = l;
            this.i = i;
            this.v = v;
            this.psf = psf;
        }
    }
        
    public static void main(String []args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        int arr[] = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = scn.nextInt();
        }

        longestbitonic(arr);

        scn.close();
    }

    public static void longestsubsequence(int []arr)
     {
        int n=arr.length;
        int mem[]= new int[n];
        int overallmax=0;
        int overallidx=0;
        for(int i=0;i<n;i++)
        {
            int max=0;
            for(int j=0;j<i;j++)
            {
                if(arr[i]>arr[j])
                {
                    if(mem[j]>max)
                    {
                        max=mem[j];
                    }
                }
            }
            mem[i]=max+1;

            if(mem[i]>overallmax)
            {
                overallmax=mem[i];
                overallidx=i;
            }
        }
        System.out.println(overallmax);

        ArrayDeque<Pair> qu= new ArrayDeque<>();
        for(int i=0;i<mem.length;i++)
        {
            if(overallmax==mem[i])
            {
                qu.add(new Pair(overallmax, i,arr[i], arr[i]+" "));
            }
        }
       

        while(qu.size()!=0)
        {
            Pair rem = qu.remove();

            if(rem.l==1)
            {
                System.out.println(rem.psf);
            }

            for(int j=rem.i-1;j>=0;j--)
            {
                if(mem[j]==rem.l-1 && arr[rem.i]>arr[j])
                {
                    qu.add(new Pair(rem.l-1,j,arr[j],arr[j]+"-> "+rem.psf));
                }
            }
        }
     }

     ////////////////////////////////////////////////////////////////////////////////////////

     public static void longestbitonic(int arr[])
     {
        int n= arr.length;
        int lis[]= new int[n];
        for(int i=0;i<n;i++)
        {
            int max=0;
            for(int j=0;j<i;j++)
            {
                if(arr[i]>=arr[j])
                {
                    if(lis[j]>max)
                    {
                        max=lis[j];
                    }
                }
            }
            lis[i]=max+1;
        }

        int lds[]= new int[n];
        for(int i=n-1;i>=0;i--)
        {
            int max=0;
            for(int j=n-1;j>i;j--)
            {
                if(arr[i]>=arr[j])
                {
                    if(lds[j]>max)
                    {
                        max=lds[j];
                    }
                }
            }
            lds[i]=max+1;
        }
        int overallmax=0;

        for(int i=0;i<n;i++)
        {
            if(lis[i]+lds[i]-1>overallmax)
            {
                overallmax=lis[i]+lds[i]-1;
            }
        }

        System.out.println(overallmax);
     }

     /////////////////////////////////////////////////////////////////////////////////////////////////////////
     
}