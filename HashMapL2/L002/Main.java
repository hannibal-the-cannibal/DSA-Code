package HashMapL2.L002;
import java.util.*;

public class Main {

	public static ArrayList<Integer> distinctelement(int[] arr, int k) {
		ArrayList<Integer> ans= new ArrayList<>();
        HashMap<Integer, Integer> map= new HashMap<>();

        for(int i=0;i<k-1;i++)
        {
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1); // creating k-1 window intially 
        }

        for(int j=0,i=k-1;i<arr.length; )
        {
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1); // acquring kth element 
            ans.add(map.size()); // doing work 
            
            int freq= map.get(arr[j]); // removing the jth element now 
            if(freq==1)
            {
                map.remove(arr[j]);
            }
            else
            {
                map.put(arr[j], freq-1);
            }
            i++;
            j++;
        }
        return ans;
	}
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int[] arr = new int[scn.nextInt()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
		int k = scn.nextInt();
		ArrayList<Integer> ans = distinctelement(arr,k);
		for(int a : ans){
			System.out.print(a + " ");
		}
	}


}

