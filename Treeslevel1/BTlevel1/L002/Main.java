package Treeslevel1.BTlevel1.L002;
import java.io.*;
import java.util.*;

public class Main {
  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static class DPair{
    int dia;
    int ht;

    DPair(int ht, int dia)
    {
        this.ht=ht;
        this.dia=dia;
    }
  }

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }

  public static void printKLevelsDown(Node node, int k){
    if(k==0)
    {
        System.out.println(node.data);
        return;
    }
    if(node.left!=null)
    {
        printKLevelsDown(node.left, k-1);
    }
    if(node.right!=null)
    {
        printKLevelsDown(node.right, k-1);
    }
    
  }

  public static void printSingleChildNodes(Node node, Node parent){
    if(node==null)
    {
        return;
    }
     if(parent!=null)
     {
        if(parent.left==node && parent.right==null)
        {
            System.out.println(node.data);
        }
        if(parent.left==null && parent.right==node)
        {
            System.out.println(node.data);
        }
     }
     printSingleChildNodes(node.left, node);
     printSingleChildNodes(node.right, node);

  }

  public static Node removeLeaves(Node node){
    if(node==null)
    {
        return null;
    }

    if(node.left==null && node.right==null)
    {
        return null;
    }

    node.left= removeLeaves(node.left);
    node.right= removeLeaves(node.right);

    return node;
  }

  public static DPair diameter1(Node node) {
    if(node==null)
    {
        return new DPair(-1,-1);
    }

    DPair lpair= diameter1(node.left);
    DPair rpair= diameter1(node.right);

    int mydia= lpair.ht+rpair.ht+2;
    int myht= Math.max(lpair.ht,rpair.ht)+1;
    int odia= Math.max(mydia, Math.max(rpair.dia,lpair.dia));

    return new DPair(myht,odia);

  }
  public static int height(Node node)
  {
    if(node==null)
    {
        return -1;
    }
    int lht= height(node.left);
    int rht= height(node.right);

    return Math.max(lht,rht)+1;
  }


  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    Node root = construct(arr);

    DPair res = diameter1(root);
    System.out.println(res.dia);
  }

}