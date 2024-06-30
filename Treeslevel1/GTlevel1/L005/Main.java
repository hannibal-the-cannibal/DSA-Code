package Treeslevel1.GTlevel1.L005;
import java.io.*;
import java.util.*;

public class Main {
  private static class Node {
    int data;
    ArrayList<Node> children = new ArrayList<>();
  }

  public static void display(Node node) {
    String str = node.data + " -> ";
    for (Node child : node.children) {
      str += child.data + ", ";
    }
    str += ".";
    System.out.println(str);

    for (Node child : node.children) {
      display(child);
    }
  }

  public static Node construct(int[] arr) {
    Node root = null;

    Stack<Node> st = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == -1) {
        st.pop();
      } else {
        Node t = new Node();
        t.data = arr[i];

        if (st.size() > 0) {
          st.peek().children.add(t);
        } else {
          root = t;
        }

        st.push(t);
      }
    }

    return root;
  }

  static Node predecessor;
  static Node successor;
  static int state;
  public static void predecessorAndSuccessor(Node node, int data) {
    if(state==0)
    {
        if(node.data==data)
        {
            state=1;
        }
        else
        {
            predecessor=node;
        }
    }
    else if(state==1)
    {
        successor=node;
        state =2;
    }
    for(Node child: node.children)
    {
        predecessorAndSuccessor(child, data);
    }
  }

  static int ceil;
  static int floor;
  public static void ceilAndFloor(Node node, int data) {
    if(node.data>data)
    {
        if(node.data<ceil)
        {
            ceil=node.data;
        }
    }

    if(node.data<data)
    {
        if(node.data>floor)
        {
            floor=node.data;
        }
    }
    for(Node child: node.children)
    {
        ceilAndFloor(child, data);
    }
  }

  public static int kthLargest(Node node, int k){
    floor=Integer.MIN_VALUE;
    int factor= Integer.MAX_VALUE; // finding floor of infinity 
    for(int i=0;i<k;i++)
    {
        ceilAndFloor(node, factor);
        factor=floor;
        floor=Integer.MIN_VALUE;
    }
    return factor;
  }
  static int maxnode=0;
  static int maxsum=Integer.MIN_VALUE;


  public static int maxsubtree(Node node)
  {
    int sum=0;

    for(Node child: node.children)
    {
        int csum=maxsubtree(child);
        sum=sum+csum;
    }
    sum=sum+node.data;

    if(sum>maxsum)
    {
        maxsum=sum;
        maxnode=node.data;
    }

    return sum;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(values[i]);
    }

    Node root = construct(arr);
    maxsubtree(root);
    System.out.println(maxsum+" @ "+ maxnode);
}

}