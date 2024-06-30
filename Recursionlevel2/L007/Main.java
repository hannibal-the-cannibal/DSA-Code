package Recursionlevel2.L007;
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);


        // int n = scn.nextInt();
		// HashSet<String> dict = new HashSet<>();
		// for(int i = 0  ; i  < n; i++){
		// 	dict.add(scn.next());
		// }
		// String sentence = scn.next();
		// wordBreak(sentence,"", dict);


        int[] arr = new int[scn.nextInt()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
		tugofwar(arr, 0, new ArrayList<>(), new ArrayList<>(), 0, 0);
		System.out.println(ans);

		// String str = scn.next();
		// int k = scn.nextInt();
		//  max = str;
		// findMaximum(str, k);
		// System.out.println(max);
	}
    
	static String max;
	public static void findMaximum(String str, int k) 
    {
        if(Integer.parseInt(str)>Integer.parseInt(max))
        {
            max=str;
        }
        if(k==0) // no swapped allowed 
        {
            return ;
        }
        for(int i=0;i<str.length()-1;i++)
        {
            for(int j=i+1;j<str.length();j++)
            {
                if(str.charAt(j)>str.charAt(i)) // swap only possible if j>i because no formed should be greatest 
                {
                    str= swap(str,i,j);
                    findMaximum(str, k-1);
                    str= swap(str,i,j); // backtrack
                }
            }
        }
	}
    public static String swap(String str, int i, int j)
    {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));
        return sb.toString();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    static int mindiff = Integer.MAX_VALUE;
	static String ans = "";
    public static void tugofwar(int[] arr, int vidx, ArrayList<Integer> set1, ArrayList<Integer> set2, int soset1, int soset2)
     {
        if(vidx==arr.length)
        {
            int delta= Math.abs(soset1-soset2);
            if(delta<mindiff)
            {
                mindiff=delta;
                ans= set1+ " "+ set2;
            }
            return ;
        }
		
        if(set1.size()<(arr.length+1)/2)
        {
            set1.add(arr[vidx]);
            tugofwar(arr, vidx+1, set1, set2, soset1+arr[vidx], soset2);
            set1.remove(set1.size()-1); //backtrack
        }

        if(set2.size()<(arr.length+1)/2)
        {
            set2.add(arr[vidx]);
            tugofwar(arr, vidx+1, set1, set2, soset1, soset2+arr[vidx]);
            set2.remove(set2.size()-1); //backtrack 
            
        }
	 }

     //////////////////////////////////////////////////////////////////////////////////////////////////////////////

     public static void wordBreak(String str, String ans, HashSet<String> dict)
     {
        if(str.length()==0)
        {
            System.out.println(ans);
            return ;
        }
		for(int i=0;i<str.length();i++)
        {
            String left = str.substring(0,i+1);
            if(dict.contains(left))
            {
                String right= str.substring(i+1);
                wordBreak(right, ans+left+" ", dict);
                // no backtrackig needed because first prefix is valid and will come in our ans
                // so no need to form same word again 
            }
        }
	 }

     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





}