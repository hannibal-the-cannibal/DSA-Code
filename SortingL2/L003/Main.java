package SortingL2.L003;
import java.util.*;

public class Main {

    public static int findMaxSteps(int[]arr) {
       int maxstep=0;
       int currstep=0;
       for(int i=0;i<arr.length-1;i++)
       {
        if(arr[i]<arr[i+1])
        {
            currstep++;
        }
        else
        {
            maxstep=Math.max(maxstep,currstep);
            currstep=0;
        }
       }
       return Math.max(maxstep,currstep);
    }

    public static void main(String[]args) {
        //input work
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int[]arr = new int[n];
        for(int i = 0 ; i < n; i++) {
            arr[i] = scn.nextInt();
        }

        int ans = findMaxSteps(arr);
        System.out.println(ans);
    }
}