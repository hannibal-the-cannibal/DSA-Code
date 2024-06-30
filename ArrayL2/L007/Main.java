package L007;
import java.util.*;

public class Main {

  public static int findPlatform(int[] arr, int[] dep) {
    Arrays.sort(arr);
    Arrays.sort(dep);
    int i=0;
    int j=0;
    int maxtrain =0;
    int plat=0;
    while(i<arr.length && j<arr.length)
    {
        if(arr[i]<=dep[j])
        {
            maxtrain++;//arrival
            i++;
        }
        else
        {
            maxtrain--;//departure
            j++;
        }
        plat=Math.max(plat,maxtrain);
    }
    return plat;
  }

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    int[] dep = new int[n];

    for (int i = 0; i < n; i++) {
      arr[i] = scn.nextInt();
    }

    for (int i = 0; i < n; i++) {
      dep[i] = scn.nextInt();
    }

    int plateforms = findPlatform(arr, dep);
    System.out.println(plateforms);
  }
}