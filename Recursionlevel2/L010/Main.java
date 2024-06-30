package Recursionlevel2.L010;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        int amt = Integer.parseInt(br.readLine());

        // boolean[] used = new boolean[coins.length];
        
        
        // coinChange1(0, coins, 0, amt, "");
        // // coinChange2(0, coins, 0, amt, "");
        // coinChange3(coins, 0, amt, "", used);
        coinChange4(coins, 0, amt, "");
    }

    public static void coinChange1(int i, int[] coins, int amtsf, int tamt, String asf)
    {
        if(i==coins.length)
        {
            if(amtsf==tamt)
            {
                System.out.println(asf+" .");
            }
            return ;
        }
        coinChange1(i+1, coins, amtsf+coins[i], tamt, asf+coins[i]+"-");
        coinChange1(i+1, coins, amtsf, tamt, asf);
    }

    /////////////////////////////////////////////////////////////////////////////////////

    public static void coinChange2(int i, int[] coins, int amtsf, int tamt, String asf) 
    {
        if(amtsf==tamt)
        {
            System.out.println(asf);
            return;
        }

        if(i>=coins.length)
        {
            return ;
        }
       
        if(amtsf+coins[i]<=tamt)
        {
            coinChange2(i, coins, amtsf+coins[i], tamt, asf+coins[i]+"-");
        }
       
        coinChange2(i+1, coins, amtsf, tamt, asf);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static void coinChange3(int[] coins, int amtsf, int tamt, String asf, boolean[] used)
    {
        if(amtsf>tamt)
        {
            return ;
        }
        else if(amtsf==tamt)
        {
            System.out.println(asf);
            return ;
        }
        for(int i=0;i<coins.length;i++)
        {
            if(used[i]==false)
            {
                used[i]=true;
                coinChange3(coins, amtsf+coins[i], tamt, asf+coins[i]+"-", used);
                used[i]=false;
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public static void coinChange4(int[] coins, int amtsf, int tamt, String asf) 
    {
        if(amtsf>tamt)
        {
            return ;
        }
        else if(tamt==amtsf)
        {
            System.out.println(asf);
            return ;
        }
      for(int i=0;i<coins.length;i++)
      {
        coinChange4(coins, amtsf+coins[i], tamt, asf+coins[i]+"-");
      }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////





}
