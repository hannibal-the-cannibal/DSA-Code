package SortingL1.L002;
import java.io.*;
import java.util.*;

public class Main {

  public static void countSort(int[] arr,  int exp) {
   int farr[]= new int[10];
   for(int i=0;i<arr.length;i++)
   {
     int idx= arr[i]/exp %10;
     farr[idx]++;
   }
   // prefix sum array 

   for(int i=1;i<farr.length;i++)
   {
     farr[i]=farr[i]+farr[i-1];
   }

   // now creating ans by appling reverse loop 

   int ans[]= new int[arr.length];
   for(int i=arr.length-1;i>=0;i--)
   {
      int val= arr[i]/exp %10;
      int pos= farr[val];
      int idx=pos-1;
      ans[idx]=val;
      farr[val]--;
   }

   for(int i=0;i<ans.length;i++)
   {
     arr[i]=ans[i];
   }

   System.out.print("After sorting on " + exp + " place -> ");
    print(arr);
   
  }
  public static void print(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }
  }

  public static void radixSort(int[] arr) {
    int max = Integer.MIN_VALUE;
    for(int val: arr)
    {
      max=Math.max(max,val);
    }

    int exp = 1;
    while (exp<=max) {
        countSort(arr, exp);
        exp = exp * 10;
    }
  }
  

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      arr[i] = scn.nextInt();
      max = Math.max(max, arr[i]);
      min = Math.min(min, arr[i]);
    }
    // countSort(arr,min,max);
    // print(arr);

    for (int i = 0; i < n; i++) {
        arr[i] = scn.nextInt();
      }
      radixSort(arr);
      print(arr);
  }

}