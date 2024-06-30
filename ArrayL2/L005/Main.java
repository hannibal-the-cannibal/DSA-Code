package L005;
import java.util.*;

public class Main {
  public static void wiggleSort(int[] arr) {
     for(int i=0;i<arr.length-1;i++)
     {
        if(i%2==0)
        {
          // even indexes 
            if(arr[i]>arr[i+1])
            {
                swap(arr,i,i+1);
            }
        }
        else
        {
          // odd indexes
            if(arr[i+1]>arr[i])
            {
                swap(arr,i,i+1);
            }
        }
     }
  }
  public static void swap(int arr[], int l, int r)
  {
    int temp=arr[l];
    arr[l]=arr[r];
    arr[r]=temp;
  }
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);

    int n = scn.nextInt();
    int[] arr = new int[n];

    for (int i = 0; i < n; i++) {
      arr[i] = scn.nextInt();
    }

    wiggleSort(arr);

    for (int val : arr) {
      System.out.print(val + " ");
    }
    System.out.println();
  }

}
