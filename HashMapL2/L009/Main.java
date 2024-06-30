package HashMapL2.L009;

import java.util.*;

public class Main {

    public static int subarrayswith012(int[] arr) {
       int ans=0;
       int countz=0;
       int counto=0;
       int countt=0;
       int delta10= counto-countz;
       int delta21= countt-counto;
       String key = delta10+"#"+delta21;
       HashMap<String,Integer> map = new HashMap<>();
       map.put(key,-1);
       for(int i=0;i<arr.length;i++)
       {
         if(arr[i]==0)
         {
            countz++;
         }
         else if(arr[i]==1)
         {
            counto++;
         }
         else
         {
            countt++;
         }
        delta10= counto-countz;
        delta21= countt-counto;
        key = delta10+"#"+delta21;

        if(map.containsKey(key))
        {
            int previdx= map.get(key);
            int len = i-previdx;
            ans= Math.max(ans,len);
        }
        else 
        {
            map.put(key,i);
        }

       }
       return ans;
    }
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        System.out.println(subarrayswith012(arr));
    }

}

