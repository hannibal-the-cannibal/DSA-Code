package ArrayL1.L003;
import java.io.*;
import java.util.*;

public class Main{

public static void main(String[] args) throws Exception {
   Scanner scn = new Scanner (System.in);
   int n = scn.nextInt();
   int m= scn.nextInt();
   int arr[][]= new int[n][m];
   for(int i=0;i<n;i++)
   {
    for(int j=0;j<m;j++)
    {
        arr[i][j]=scn.nextInt();
    }
   }

   int k=0;
   while(k!=n)
   {
     for(int i=0, j=k;j<n;i++,j++)
     {
        System.out.print(arr[i][j]+" ");
     }
     k++;
   }

 }

}