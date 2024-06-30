package SortingL1.L001;
import java.io.*;
import java.util.*;

public class Main {

  public static void bubbleSort(int[] arr) {
    for(int i=1;i<=arr.length-1;i++)
    {
        for(int j=0;j<arr.length-i;j++)
        {
            if(isSmaller(arr, j+1, j))
            {
                swap(arr,j+1, j);
            }
        }
    }
    
  }

  public static void insertionSort(int[] arr) {
    for(int i=1;i<arr.length;i++)
    {
        for(int j=i-1; j>=0;j--)
        {
            if(isGreater(arr, j, j+1))
            {
                swap(arr, j, j+1);
            }
            else
            {
                break;
            }
        }
    }
  }

  public static void selectionSort(int[] arr) {
    for(int i=0;i<arr.length-1;i++)
    {
        int mi=i;
        for(int j=i+1;j<arr.length;j++)
        {
            if(isSmaller(arr, j, mi))
            {
                mi=j;
            }
        }
        swap(arr, i, mi);
    }

  }

  public static int partition(int[] arr, int pivot, int lo, int hi) {
    System.out.println("pivot -> " + pivot);
    int i = lo, j = lo;
    while (i <= hi) {
      if (arr[i] <= pivot) {
        swap(arr, i, j);
        i++;
        j++;
      } else {
        i++;
      }
    }
    System.out.println("pivot index -> " + (j - 1));
    return (j - 1);
  }

  public static void quickSort(int[] arr, int lo, int hi) {
    if(lo>hi)
    {
        return ;
    }
    int pivot= arr[hi];
    int pi=partition(arr, pivot, lo, hi);
    quickSort(arr, lo, pi-1);
    quickSort(arr, pi+1, hi);
    
  }

 

  // used for swapping ith and jth elements of array
  public static void swap(int[] arr, int i, int j) {
    System.out.println("Swapping " + arr[i] + " and " + arr[j]);
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static boolean isGreater(int[] arr, int j, int i) {
    System.out.println("Comparing " + arr[i] + " and " + arr[j]);
    if (arr[i] < arr[j]) {
      return true;
    } else {
      return false;
    }
  }

  // return true if ith element is smaller than jth element
  public static boolean isSmaller(int[] arr, int i, int j) {
    System.out.println("Comparing " + arr[i] + " and " + arr[j]);
    if (arr[i] < arr[j]) {
      return true;
    } else {
      return false;
    }
  }

  public static void print(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }
  }

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scn.nextInt();
    }
    // bubbleSort(arr);
    // selectionSort(arr);
    // insertionSort(arr);


    // int pivot = scn.nextInt();
    // partition(arr,pivot);

    // quickSort(arr, 0, arr.length - 1); 
    // print(arr);

    int k = scn.nextInt();
    // System.out.println(quickSelect(arr,0,arr.length - 1,k - 1));

    
  }

}