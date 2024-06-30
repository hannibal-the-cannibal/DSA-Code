package L006;
import java.util.*;


public class Main {

  public static List<List<Integer>> kSum(int[] arr, int target, int k) {
    Arrays.sort(arr);
    return ksumhelper(arr,target,k,0);
  }
  public static List<List<Integer>> twosum(int arr[], int target, int si)
  {
     int n =arr.length;
     List<List<Integer>> ans= new ArrayList<>();
     if(n-si<2)
     {
        return ans;
     }

     int li=si;
     int ri=n-1;
     while(li<ri)
     {
        if(li!=si && arr[li]==arr[li-1])
        {
            li++;
            continue;
        }
        int sum=arr[li]+arr[ri];
        if(sum==target)
        {
            List<Integer> subans= new ArrayList<>();
            subans.add(arr[li]);
            subans.add(arr[ri]);
            ans.add(subans);
        }
        else if(sum>target)
        {
            ri--;
        }
        else
        {
            li++;
        }
     }
     return ans;
  }
  public static List<List<Integer>> ksumhelper(int arr[], int target, int k , int si)
  {
    if(k==2)
    {
        return twosum(arr,target,si);
    }
     List<List<Integer>> ans= new ArrayList<>();
     int n=arr.length;
     if(n-si<k)
     {
        return ans;
     }

     for(int i=si;i<=n-k;i++)
     {
        int val1=arr[i];
        if(i!=si && arr[i]==arr[i-1])
        {
            continue;
        }
        List<List<Integer>> subans= ksumhelper(arr, target-val1, k-1, i+1);
        for(List<Integer> vals: subans )
        {
            vals.add(val1);
            ans.add(vals);
        }
     }

     return ans;

  }

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scn.nextInt();
    }
    int target = scn.nextInt();
    int k = scn.nextInt();
    List<List<Integer>> res = kSum(arr, target, k);
    ArrayList<String> finalResult = new ArrayList<>();
    for (List<Integer> list : res) {
      Collections.sort(list);
      String ans = "";
      for (int val : list) {
        ans += val + " ";
      }
      finalResult.add(ans);
    }
    Collections.sort(finalResult);
    for (String str : finalResult) {
      System.out.println(str);
    }
  }

}