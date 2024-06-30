package Treeslevel1.BTlevel1.L001;
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

  public static int size(Node node) {
    if(node==null)
    {
        return 0;
    }

    int lsize= size(node.left);
    int rsize= size(node.right);

    return lsize+rsize+1;
  }

  public static int sum(Node node) {
    if(node==null)
    {
        return 0;
    }
    int lsum= sum(node.left);
    int rsum= sum(node.right);

    return lsum+rsum+node.data;
  }

  public static int max(Node node) {
    if(node ==null)
    {
        return Integer.MIN_VALUE;
    }

    int lmax= max(node.left);
    int rmax= max(node.right);

    return Math.max(Math.max(lmax,rmax),node.data);
  }

  public static int height(Node node) {
    if(node==null)
    {
        return -1;
    }

    int lht= height(node.left);
    int rht= height(node.right);

    return Math.max(lht,rht)+1;
  }

  public static void levelOrder(Node node) {
    Queue<Node> mq= new ArrayDeque<>();
    Queue<Node> hq= new ArrayDeque<>();
    mq.add(node);

    while(mq.size()>0)
    {
        Node rem= mq.remove();
        System.out.print(rem.data+" ");

        if(rem.left!=null)
        {
            hq.add(rem.left);
        }
        if(rem.right!=null)
        {
            hq.add(rem.right);
        }

        if(mq.size()==0)
        {
            System.out.println();
            mq=hq;
            hq= new ArrayDeque<>();
        }
    }
  }

  public static boolean find(Node node, int data){
    if(node.data==data)
    {
        return true;
    }
    if(node.left!=null)
    {
        boolean lrec= find(node.left,data);
        if(lrec==true)
        {
            return true;
        }
    }

    if(node.right!=null)
    {
        boolean rrec= find(node.right,data);
        if(rrec==true)
        {
            return true;
        }
    }
    return false;
  }

  public static ArrayList<Integer> nodeToRootPath(Node node, int data){
    if(node.data==data)
    {
        ArrayList<Integer> ans= new ArrayList<>();
        ans.add(node.data);
        return ans;
    }

    if(node.left!=null)
    {
        ArrayList<Integer> lrec= nodeToRootPath(node.left, data);
        if(lrec.size()>0)
        {
            lrec.add(node.data);
            return lrec;
        }
    }

    if(node.right!=null)
    {
        ArrayList<Integer> rrec= nodeToRootPath(node.right, data);
        if(rrec.size()>0)
        {
            rrec.add(node.data);
            return rrec;
        }
    }

    return new ArrayList<>();
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

    int data = Integer.parseInt(br.readLine());

    Node root = construct(arr);
    boolean found = find(root, data);
    System.out.println(found);

    ArrayList<Integer> path = nodeToRootPath(root, data);
    System.out.println(path);
  }

} 