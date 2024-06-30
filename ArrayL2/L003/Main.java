package L003;
import java.util.*;

public class Main {
    public static int minJumps(int x) {
       int jump=1;
       int sum=0;

       while(sum<x)
       {
         sum+=jump;
         jump++;
       }

       if((sum-x)%2==0)
       {
         return jump-1;
       }
       else if((sum+jump-x)%2==0)
       {
         return jump;
       }
       else
       {
         return jump+1;
       }
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int x = scn.nextInt();

        int jumps = minJumps(x);
        System.out.println(jumps);
    }
}