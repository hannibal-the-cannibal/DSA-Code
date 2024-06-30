package Treeslevel1.BSTlevel1.L001;
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
    if(node==null)
    {
        return Integer.MIN_VALUE;
    }
    if(node.right!=null)
    {
        return max(node.right);
    }
    else
    {
        return node.data;
    }
    
  }

  public static int min(Node node) {
    if(node==null)
    {
        return Integer.MAX_VALUE;
    }

    if(node.left!=null)
    {
        return min(node.left);
    }
    else
    {
        return node.data;
    }
    
  }

  public static boolean find(Node node, int data){
    if(node==null)
    {
        return false;
    }
    if(node.data==data)
    {
        return true;
    }

    if(data<node.data)
    {
        return find(node.left,data);
    }
    else
    {
        return find(node.right,data);
    }
    
  }  

  public static Node add(Node node, int data) {
    if(node==null)
    {
        return new Node(data, null,null);
    }

    if(data<node.data)
    {
        node.left=add(node.left,data);
    }
    else if(node.data<data)
    {
        node.right= add(node.right,data);
    }
    return node;

  }

  public static Node remove(Node node, int data) {
    if(node==null)
    {
        return null;
    }
    else if(data>node.data)
    {
        node.right=remove(node.right,data);
        return node;
    }
    else if( data<node.data)
    {
        node.left= remove(node.left, data);
        return node;
    }
    else
    {
      // node found 
        Node lchild= node.left;
        Node rchild= node.right;

        if(lchild==null && rchild==null)
        {
            return null;
        }
        else if(lchild==null)
        {
            return rchild;
        }
        else if(rchild==null)
        {
            return lchild;
        }
        else
        {
            int maxval= max(lchild);
            node.data=maxval;
            node.left= remove(lchild, maxval);
            return node;
        }
    }
  }

  public static int lca(Node node, int d1, int d2) {
    if(d1>node.data && d2>node.data)
    {
        return lca(node.right,d1,d2);
    }
    else if(d1<node.data && d2<node.data)
    {
        return lca(node.left,d1,d2);
    }
    else
    {
        return node.data;
    }
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

    int d1 = Integer.parseInt(br.readLine());
    int d2 = Integer.parseInt(br.readLine());

    Node root = construct(arr);
    int lca = lca(root, d1, d2);
    System.out.println(lca);
  }

}