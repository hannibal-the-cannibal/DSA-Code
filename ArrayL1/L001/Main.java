package ArrayL1.L001;
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
    int k=scn.nextInt();
    // rotate(arr1,k);
    // reverse(arr1);
    // int n2= scn.nextInt();
    // int arr2[]= new int[n2];
    // for(int i=0;i<n2;i++)
    // {
    //     arr2[i]=scn.nextInt();
    // }
    // spanofarray(arr1);
    // sumofarray(arr1,arr2);
 }
 public static void spanofarray(int arr[])
 {
    int min=arr[0];
    int max=arr[0];
    for(int i=0;i<arr.length;i++)
    {
        if(arr[i]>max)
        {
            max=arr[i];
        }
        if(arr[i]<min)
        {
            min=arr[i];
        }
    }
    System.out.println(max-min);
 }

 ////////////////////////////////////////////////////////////////////////////////

 public static void sumofarray(int arr1[], int arr2[])
 {
    int n1=arr1.length;
    int n2= arr2.length;

    int i=n1-1;
    int j=n2-1;
    int sum[]= new int[Math.max(n1,n2)];
    int n3= sum.length;
    int k=n3-1;
    int carry=0;

    while(k>=0)
    {
        int d=carry;
        if(i>=0)
        {
            d+=arr1[i];
        }
        if(j>=0)
        {
            d+=arr2[j];
        }

        carry=d/10;
        d=d%10;

        sum[k]=d;

        i--;
        j--;
        k--;
    }
    if(carry!=0)
    {
        System.out.print(carry);
    }
    for(int val: sum)
    {
        System.out.print(val);
    }

 }

 ///////////////////////////////////////////////////////////////////////////////////////////


 public static void reverse(int arr1[])
 {
    int i=0;
    int j=arr1.length-1;
    while(i<=j)
    {
        int temp=arr1[i];
        arr1[i]=arr1[j];
        arr1[j]=temp;
        i++;
        j--;
    }
    
    for(int val: arr1)
    {
        System.out.println(val);
    }
 }

 ////////////////////////////////////////////////////////////////////////////////////////////////////





}