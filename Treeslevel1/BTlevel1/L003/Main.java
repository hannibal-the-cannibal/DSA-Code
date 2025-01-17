package Treeslevel1.BTlevel1.L003;
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

  public static void printKNodesFar(Node node, int data, int k) {
    ArrayList<Node> path = nodetorootpath(node, data);
    for(int i=0;i<path.size();i++)
    {
        printklevelsdown(path.get(i), k-i, i==0? null:path.get(i-1) );
    }
  }

  public static void printklevelsdown(Node node, int k , Node blocker)
  {
    if(node==null || k<0 || node==blocker)
    {
        return ;
    }
    if(k==0)
    {
        System.out.println(node.data);
    }

    printklevelsdown(node.left, k-1, blocker);
    printklevelsdown(node.right, k-1, blocker);
  }
  public static ArrayList<Node> nodetorootpath(Node node, int data)
  {
    if(node.data==data)
    {
        ArrayList<Node> base= new ArrayList<>();
        base.add(node);
        return base;
    }

    if(node.left!=null)
    {
        ArrayList<Node> lres= nodetorootpath(node.left, data);
        if(lres.size()>0)
        {
            lres.add(node);
            return lres;
        }
    }
    if(node.right!=null)
    {
        ArrayList<Node> rres= nodetorootpath(node.right, data);
        if(rres.size()>0)
        {
            rres.add(node);
            return rres;
        }
    }
    return new ArrayList<>();
  }

  public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi){
    if(node==null)
    {
        return ;
    }
    if(node.left==null&& node.right==null)
    {
        sum=sum+node.data;
        if(sum>=lo && sum<=hi)
        {
            System.out.println(path+node.data);
        }
        return ;
    }
    pathToLeafFromRoot(node.left, path+node.data+" ", sum+node.data, lo, hi);
    pathToLeafFromRoot(node.right, path+node.data+" ", sum+node.data, lo, hi);
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

    int lo = Integer.parseInt(br.readLine());
    int hi = Integer.parseInt(br.readLine());

    Node root = construct(arr);
    pathToLeafFromRoot(root, "", 0, lo, hi);
  }

}