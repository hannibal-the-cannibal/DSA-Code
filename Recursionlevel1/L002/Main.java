package Recursionlevel1.L002;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n=scn.nextInt();
        int arr[]= new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i]=scn.nextInt();
        }
        int x= scn.nextInt();
        // displayArr(arr, 0);
        // displayArrReverse(arr, 0);
        // System.out.println(maxOfArray(arr, 0));
        // System.out.println(firstIndex(arr, 0, x));
        // System.out.println(lastIndex(arr, 0, x));
        int ans []= allIndices(arr, x, 0, 0);
        for(int i=0;i<ans.length;i++)
        {
            System.out.print(ans[i]+" ");
        }

    }

    public static void displayArr(int[] arr, int idx){
        if(idx>=arr.length)
        {
            return;
        }
        System.out.println(arr[idx]);
        displayArr(arr, idx+1);
        
    }
    public static void displayArrReverse(int[] arr, int idx) {
        if(idx>=arr.length)
        {
            return ;
        }
        displayArrReverse(arr, idx+1);
        System.out.println(arr[idx]);

    }
    public static int maxOfArray(int[] arr, int idx){
        if(idx>=arr.length)
        {
            return Integer.MIN_VALUE;
        }
        int res= maxOfArray(arr, idx+1);
        return Math.max(arr[idx],res);
    }
    public static int firstIndex(int[] arr, int idx, int x){
        if(idx>=arr.length)
        {
            return -1;
        }
        if(arr[idx]==x)
        {
            return idx;
        }
        return firstIndex(arr, idx+1, x);
    }
    public static int lastIndex(int[] arr, int idx, int x){
       if(idx>=arr.length)
       {
        return -1;
       }
       int ans= lastIndex((arr), idx+1, x);
       if(ans==-1)
       {
        if(arr[idx]==x)
        {
            return idx;
        }
        else
        {
            return -1;
        }
       }
       else
       {
        return ans;
       }

    }

    public static int[] allIndices(int[] arr, int x, int idx, int fsf) {
        if(idx==arr.length)
        {
            return new int [fsf];
        }
        if(arr[idx]==x)
        {
            int res[]=allIndices(arr, x, idx+1, fsf+1);
            res[fsf]=idx;
            return res;
        }
        else
        {
            int res[]=allIndices(arr, x, idx+1, fsf);  
            return res; 
        }
    }

}
