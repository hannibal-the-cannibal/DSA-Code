package ArrayL1.L002;
import java.io.*;
import java.util.*;

public class Main{

public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n1= scn.nextInt();
    int arr1[]= new int[n1];
    for(int i=0;i<n1;i++)
    {
        arr1[i]=scn.nextInt();
    }
    int data=scn.nextInt();

    // subarray(arr1);
    brokeneco(arr1,data);
 }
 public static void subarray(int arr1[])
 {
    for(int i=0;i<arr1.length;i++)
    {
        for(int j=i;j<arr1.length;j++)
        {
            for(int k=i;k<=j;k++)
            {
                System.out.print(arr1[k]+" ");
            }
            System.out.println();
        }
    }
 }
 //////////////////////////////////////////////////////////////////
 
 public static void brokeneco(int arr[], int data)
 {
    int l=0;
    int h=arr.length-1;
    int ceil=0;
    int floor=0;
    while(l<=h)
    {
        int mid=(l+h)/2;
        if(data>arr[mid])
        {
            l=mid+1;
            floor=arr[mid];
        }
        else if(data<arr[mid])
        {
            h=mid-1;
            ceil=arr[mid];
        }
        else
        {
            floor=arr[mid];
            ceil=arr[mid];
            break;
        }
    }
    System.out.println(ceil+"  "+ floor);
 }

}