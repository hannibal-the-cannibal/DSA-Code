package HashMapL2.L010;
import java.util.*;

public class Main {

    public static void smallestfreqsubarray(int[] arr) {
        HashMap<Integer,Integer> fmap= new HashMap<>();
        HashMap<Integer,Integer> smap= new HashMap<>();
        int hfreq=0;
        int si=0;
        int ei=0;
        int len= ei-si+1;
        for(int i=0;i<arr.length;i++)
        {
            if(fmap.containsKey(arr[i]))
            {
                fmap.put(arr[i], fmap.get(arr[i])+1);
            }
            else
            {
                fmap.put(arr[i],1);
                smap.put(arr[i],i);
            }

            if(fmap.get(arr[i])>hfreq)
            {
                hfreq=fmap.get(arr[i]);
                si=smap.get(arr[i]);
                ei=i;
                len=ei-si+1;
            }
            else if(fmap.get(arr[i])==hfreq)
            {
                int clen= i-smap.get(arr[i])+1;
                if(clen<len)
                {
                    hfreq=fmap.get(arr[i]);
                    si=smap.get(arr[i]);
                    ei=i;
                    len=ei-si+1;
                }
            }
        }

        System.out.println(arr[si]);
        System.out.println(si);
        System.out.println(ei);
    }
    
    public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		int[] num = new int[m];
		for (int i = 0; i < m; i++) {
			num[i] = scn.nextInt();
		}
		completeTask(n, m, num);
	}

    public static void completeTask(int n, int m, int[] arr) {
		HashSet<Integer> comp= new HashSet<>();
        for(int val: arr)
        {
            comp.add(val);
        }
        ArrayList<Integer> p1= new ArrayList<>();
        ArrayList<Integer> p2= new ArrayList<>();
        boolean flag=true;

        for(int i=1;i<=n;i++)
        {
            if(comp.contains(i)==false)
            {
                if(flag)
                {
                    p1.add(i);
                }
                else
                {
                    p2.add(i);
                }
                flag=!flag;
            }
        }

        for(int val: p1)
        {
            System.out.print(val+" ");
        }
        System.out.println();

        for(int val: p2)
        {
            System.out.print(val+" ");
        }

	}

}
