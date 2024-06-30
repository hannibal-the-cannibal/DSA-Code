package Recursionlevel2.L009;
import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int nboxes = Integer.parseInt(br.readLine());
    int ritems = Integer.parseInt(br.readLine());
    // permutations1(new int[nboxes], 1, ritems);
    // combinations1(1, nboxes, 0, ritems, "");
  }

  public static void permutations1(int[] boxes, int ci, int ti)
  {
    if(ci>ti)
        {
            for(int i=0;i<boxes.length;i++)
            {
                System.out.print(boxes[i]);
            }
            System.out.println();
            return ;
        }
    for(int i=0;i<boxes.length;i++)
    {
        if(boxes[i]==0)
        {
            boxes[i]=ci;
            permutations1(boxes, ci+1, ti);
            boxes[i]=0; //backtrack
        }
    }
  }
  /////////////////////////////////////////////////////////////////////////////////////

  public static void combinations1(int cb, int tb, int ssf, int ts, String asf)
  {
    if(cb>tb)
    {
        if(ssf==ts)
        {
            System.out.println(asf);
        }
        return ;
    }
    combinations1(cb+1, tb, ssf+1, ts, asf+"i");
    combinations1(cb+1, tb, ssf, ts, asf+"-");
  }

  ///////////////////////////////////////////////////////////////////////////////////////





}