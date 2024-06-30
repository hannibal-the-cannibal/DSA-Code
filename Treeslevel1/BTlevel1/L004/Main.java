package Treeslevel1.BTlevel1.L004;
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

  public static int height(Node node) {
    if (node == null) {
      return -1;
    }

    int lh = height(node.left);
    int rh = height(node.right);

    int th = Math.max(lh, rh) + 1;
    return th;
  }
  public static class BSTPair{
    int max;
    int min ;
    boolean isBST;
    Node root;
    int size;
  }

  static int tilt = 0;
  public static int tilt(Node node){
    if(node==null)
    {
        return 0;
    }

    int ltilt= tilt(node.left);
    int rtilt= tilt(node.right);

    tilt=tilt+Math.abs(ltilt-rtilt);
    return ltilt+rtilt+node.data;

  }

  public static BSTPair BSTtree(Node node)
  {
    if(node==null)
    {
        BSTPair base= new BSTPair();
        base.min= Integer.MAX_VALUE;
        base.max= Integer.MIN_VALUE;
        base.isBST=true;
        base.root=null;
        base.size=0;
        return base;
    }

    BSTPair lpair= BSTtree(node.left);
    BSTPair rpair= BSTtree(node.right);

    BSTPair mypair= new BSTPair();

    mypair.isBST= lpair.isBST && rpair.isBST &&(node.data>=lpair.max && node.data<=rpair.min);
    mypair.min= Math.min(node.data, Math.min(lpair.min, rpair.min));
    mypair.max= Math.max(node.data,Math.max(lpair.max,rpair.max));

    if(mypair.isBST)
    {
      mypair.root=node;
      mypair.size=lpair.size+rpair.size+1;
    }
    else if(lpair.size>rpair.size)
    {
      mypair.root=lpair.root;
      mypair.size=lpair.size;
    }
    else
    {
      mypair.root= rpair.root;
      mypair.size=rpair.size;
    }

    return mypair;
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

    BSTPair bp = BSTtree(root);
    System.out.println(bp.root.data+"@"+bp.size);
  }

}