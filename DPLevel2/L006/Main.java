package DPLevel2.L006;
import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] prices = new int[n];
    for (int i = 0; i < n; i++) {
      prices[i] = scn.nextInt();
    }
    System.out.println(rodcutting(prices));
  }

  public static int rodcutting(int[] prices) {
    int np[]= new int[prices.length+1];
    for(int i=0;i<prices.length;i++)
    {
        np[i+1]=prices[i];
    }

    int mem[]= new int[np.length];
    mem[0]=0;
    mem[1]=np[1];

    for(int i=2;i<mem.length;i++)
    {
        mem[i]=np[i];

        int li=1;
        int ri=i-1;
        while(li<=ri)
        {
            if(mem[li]+mem[ri]>mem[i])
            {
                mem[i]=mem[li]+mem[ri];
            }
        }
        li++;
        ri--;
    }
    return mem[mem.length-1];
    
  }

}
	