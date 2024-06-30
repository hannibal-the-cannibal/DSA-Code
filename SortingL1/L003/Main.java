package SortingL1.L003;
import java.io.*;
import java.util.*;

public class Main {

  public static void sortDates(String[] arr) {
    countSort(arr,1000000,100,32); //days
    countSort(arr,1000,100,13);//monts
    countSort(arr,1,1000,2501);//years
  }

  public static void countSort(String[] arr,int div, int mod, int range) {
    int farr[]= new int[range];
   for(int i=0;i<arr.length;i++)
   {
     int idx= Integer.parseInt(arr[i],10)/div%mod;
     farr[idx]++;
   }
   // prefix sum array 

   for(int i=1;i<farr.length;i++)
   {
     farr[i]=farr[i]+farr[i-1];
   }

   // now creating ans by appling reverse loop 

   String ans[]= new String [arr.length];
   for(int i=arr.length-1;i>=0;i--)
   {
      int pos= farr[Integer.parseInt(arr[i],10)/div%mod]-1;
      ans[pos]=arr[i];
      farr[Integer.parseInt(arr[i],10)/div%mod]--;
   }

   for(int i=0;i<ans.length;i++)
   {
     arr[i]=ans[i];
   }

  //  System.out.print("After sorting on " + exp + " place -> ");
    print(arr);
  }

  public static void print(String[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }
  }

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    String[] arr = new String[n];
    for (int i = 0; i < n; i++) {
      String str = scn.next();
      arr[i] = str;
    }
    sortDates(arr);
    print(arr);
  }

}