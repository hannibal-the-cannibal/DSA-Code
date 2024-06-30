package HashMapL2.L011;

import java.io.*;
import java.util.*;

public class Main {

  public static int connectSticks(int[] sticks) {
    PriorityQueue<Integer> pq= new PriorityQueue<>();
    for(int val: sticks)
    {
        pq.add(val);
    }

    int cost=0;
    while(pq.size()>1)
    {
        int v1= pq.remove();
        int v2= pq.remove();
        int c= v1+v2;
        cost+=c;

        pq.add(c);
    }

    return cost;

  }

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();

    int[] sticks = new int[n];
    for (int i = 0; i < sticks.length; i++) {
      sticks[i] = scn.nextInt();
    }

    System.out.println(connectSticks(sticks));
  }

}