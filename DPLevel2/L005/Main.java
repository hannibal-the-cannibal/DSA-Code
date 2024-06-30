package DPLevel2.L005;
import java.io.*;
import java.util.*;

public class Main {
    public static class Bridge implements Comparable<Bridge>{
        int n;
        int s;
        Bridge(int n, int s)
        {
            this.n=n;
            this.s=s;
        }
        public int compareTo(Bridge o)
        {
            if(this.n!=o.n){
                return this.n-o.n;
            }
            else
            {
                return this.s-o.s;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = Integer.parseInt(scn.nextLine());

        Bridge bdgs[]= new Bridge [n];
        for(int i=0;i<n;i++)
        {
            String line= scn.nextLine();
            String parts[]= line.split(" ");
            int north= Integer.parseInt(parts[0]);
            int south= Integer.parseInt(parts[1]);
            bdgs[i]= new Bridge(north, south);
        }

        Arrays.sort(bdgs);

        int mem[]= new int[n];
        int omax=0;
        for(int i=0;i<n;i++)
        {
            int max=0;
            for(int j=0;j<i;j++)
            {
                if(bdgs[i].s>=bdgs[j].s)
                {
                    if(mem[j]>max)
                    {
                        max=mem[j];
                    }
                }
            }
            mem[i]=max+1;
            if(mem[i]>omax)
            {
                omax=mem[i];
            }
        }

        System.out.println(omax);
    }

}
