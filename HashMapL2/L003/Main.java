package HashMapL2.L003;
import java.util.*;

public class Main {

	public static int subarray0sum(int[] arr) {
        HashMap<Integer,Integer> map= new HashMap<>();
        int i=-1;
        int sum=0;
        int maxlen=0;
        map.put(sum,i);

        while(i<arr.length-1)
        {
            i++;
            sum+=arr[i];

            if(!map.containsKey(sum))
            {
                map.put(sum,i);
            }
            else
            {
                int len= i-map.get(sum);
                maxlen=Math.max(maxlen,len);
            }
        }
        return maxlen;
	
	}

    ////////////////////////////////////////////////////////////////////////////////////////////


    public static int count0sum(int[] arr) {
        HashMap<Integer,Integer> map= new HashMap<>();
        int i=-1;
        int sum=0;
        int count=0;
        map.put(sum,1);
        while(i<arr.length-1)
        {
            i++;
            sum+=arr[i];

            if(map.containsKey(sum))
            {
                count+=map.get(sum);
                map.put(sum,map.get(sum)+1);
            }
            else
            {
                map.put(sum,1);
            }
        }

        return count;
	
	}
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scn.nextInt();
		}
		System.out.println(count0sum(arr));

	}

}
