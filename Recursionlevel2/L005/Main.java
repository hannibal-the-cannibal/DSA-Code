package Recursionlevel2.L005;
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);



        String str = scn.next();
		HashMap<Character, Integer> fmap = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(fmap.containsKey(ch))
            {
                int of= fmap.get(ch);
                fmap.put(ch,of+1);
            }
            else
            {
                fmap.put(ch,1);
            }
		}
        Character oddc= null;
        int odds=0;
        int len=0;
        for(char ch: fmap.keySet())
        {
            int freq= fmap.get(ch);

            if(freq%2==1)
            {
                odds++;
                oddc=ch;
            }
            fmap.put(ch,freq/2);
            len+=freq/2;
        }
        
        if(odds>1)
        {
            System.out.println("-1");
            return ;
        }
        generatepw(1, len, fmap, oddc, "");



		// int n = scn.nextInt();
		// int k = scn.nextInt();
		// ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		// for(int i  = 0; i < k; i++) {
		// 	ans.add(new ArrayList<>());
		// }
		// kpartition(1, n, k, 0, ans);


        int n = scn.nextInt();
		int[] arr = new int[n];
		int sum = 0;
		for(int i =  0 ; i < arr.length; i++) {
			arr[i] = scn.nextInt();
			sum += arr[i];
		}
		int k = scn.nextInt();
		// if k is equal to 1, then whole array is your answer 
		if(k == 1) {
			System.out.print("[");
			for(int i = 0 ; i  < arr.length; i++) {
				System.out.print(arr[i] + ", ");
			}
			System.out.println("]");
			return;
		}
		//if there are more subsets than no. of elements in array or sum of all elements is not divisible by k
		if(k > n || sum % k != 0) {
			System.out.println("-1");
			return;
		}
		int[] subsetSum = new int[k];
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		for(int i = 0; i < k; i++) {
			ans.add(new ArrayList<>());
		}
		kequalsum(arr,0,n,k,subsetSum,0,ans);

	}

    static int counter=0;
    public static void kpartition(int i, int n, int k, int setsofar, ArrayList<ArrayList<Integer>> ans) 
    {
        if(i>n)
        {
            if(setsofar==k)
            {
                counter++;
                System.out.print(counter+".");
                for(ArrayList<Integer> set: ans)
                {
                    System.out.print(set+" ");
                }
                System.out.println();
            }
            return;
        }
		for(int j=0;j<ans.size();j++)
        {
            if(ans.get(j).size()>0) // already fromed set 
            {
                ans.get(j).add(i);
                kpartition(i+1, n, k, setsofar, ans);
                ans.get(j).remove(ans.get(j).size()-1); //backtracking
            }
            else
            {
                ans.get(j).add(i); // forming new set 
                kpartition(i+1, n, k, setsofar+1, ans);
                ans.get(j).remove(ans.get(j).size()-1); //backtracking
                break;   //so that the number gets added only on first nonempty set and not on remaining non empty sets to avoid permu
            }
        }
	}


    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void kequalsum(int[] arr, int vidx,int n , int k,int[] subsetSum,int setsofar, ArrayList<ArrayList<Integer>> ans)
     { 
        if(vidx==arr.length)
        {
            if(setsofar==k)
            {
                boolean flag=true;
                for(int i=0;i<subsetSum.length-1;i++)
                {
                    if(subsetSum[i]!=subsetSum[i+1])
                    {
                        flag=false;
                        break;
                    }
                }
                if(flag)
                {
                    for(ArrayList<Integer> set:ans)
                    {
                        System.out.print(set+" ");
                    }
                }

            }
            return ;
        }
		
        for(int i=0;i<ans.size();i++)
        {
            if(ans.get(i).size()>0)
            {
                ans.get(i).add(arr[vidx]); // getting added in existing set 
                subsetSum[i]=subsetSum[i]+arr[vidx];
                kequalsum(arr, vidx+1, n, k, subsetSum, setsofar, ans);
                subsetSum[i]=subsetSum[i]-arr[vidx];
                ans.get(i).remove(ans.get(i).size()-1);
            }
            else
            {
                ans.get(i).add(arr[vidx]);// new empty set formed  
                subsetSum[i]=subsetSum[i]+arr[vidx];
                kequalsum(arr, vidx+1, n, k, subsetSum, setsofar+1, ans);
                subsetSum[i]=subsetSum[i]-arr[vidx];
                ans.get(i).remove(ans.get(i).size()-1);
                break;
            }
        }
	}

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void generatepw(int cs, int ts, HashMap<Character, Integer> fmap, Character oddc, String asf)
     {
       if(cs>ts)
       {
         String rev="";
         for(int i=asf.length()-1;i>=0;i--)
         {
            rev=rev+asf.charAt(i);
         }
         String res= asf;
         if(oddc!=null)
         {
            res=res+oddc;
         }
         res=res+rev;
         System.out.println(res);
         return ;
       }


        for(char ch: fmap.keySet())
        {
            int freq= fmap.get(ch);
            if(freq>0)
            {
                fmap.put(ch,freq-1);
                generatepw(cs+1, ts, fmap, oddc, asf+ch);
                fmap.put(ch,freq); //backtrack
            }
        }
	 }






}