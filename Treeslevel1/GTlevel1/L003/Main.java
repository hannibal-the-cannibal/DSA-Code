package Treeslevel1.GTlevel1.L003;
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

  public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
    if (node.data == data) {
      ArrayList<Integer> path = new ArrayList<>();
      path.add(node.data);
      return path;
    }

    for (Node child : node.children) {
      ArrayList<Integer> ptc = nodeToRootPath(child, data);
      if (ptc.size() > 0) {
        ptc.add(node.data);
        return ptc;
      }
    }

    return new ArrayList<>();
  }

  public static int lca(Node node, int d1, int d2) {
    ArrayList<Integer> p1 = nodeToRootPath(node, d1);
    ArrayList<Integer> p2 = nodeToRootPath(node, d2);

    int i = p1.size() - 1;
    int j = p2.size() - 1;

    while(i >= 0 && j >= 0 && p1.get(i) == p2.get(j)){
      i--;
      j--;
    }

    return p1.get(i + 1);
  }

  public static int distanceBetweenNodes(Node node, int d1, int d2){
    ArrayList<Integer> p1 = nodeToRootPath(node, d1);
    ArrayList<Integer> p2 = nodeToRootPath(node, d2);

    int i = p1.size() - 1;
    int j = p2.size() - 1;

    while(i >= 0 && j >= 0 && p1.get(i) == p2.get(j)){
      i--;
      j--;
    }

    return (i + j+ 2);
  }

  public static void removeLeaves(Node root) {
    if(root.children.size()==0)
    {
        return ;
    }

    for(int i=root.children.size()-1;i>=0;i--)
    {
        Node child= root.children.get(i);
        if(child.children.size()==0)
        {
            root.children.remove(i);

        }
    }

    for(Node child: root.children)
    {
        removeLeaves(child);
    }

  }
  public static Node getTail(Node node)
  {
    while(node.children.size()!=0)
    {
        node=node.children.get(0);
    }
    return node;
  }

  public static void linearize(Node node){
     for(Node child: node.children)
     {
        linearize(child);
     }

     while(node.children.size()>1)
     {
        Node lc= node.children.get(node.children.size()-1);
        Node slc= node.children.get(node.children.size()-2);
        node.children.remove(node.children.size()-1);
        Node tail= getTail(slc);
        tail.children.add(lc);
     }
  }

  public static boolean areSimilar(Node n1, Node n2) {
     if(n1.children.size()!=n2.children.size())
     {
        return false;
     }

     for(int i=0;i<n1.children.size();i++)
     {
        Node c1= n1.children.get(i);
        Node c2= n2.children.get(i);
        if(areSimilar(c1, c2)==false)
        {
            return false;
        }
     }
     return true;
  }

  public static boolean areMirror(Node n1, Node n2) {
    if(n1.children.size()!=n2.children.size())
    {
        return false;
    }

    for(int i=0;i<n1.children.size();i++)
    {
        int j=n1.children.size()-1-i;
        Node c1= n1.children.get(i);
        Node c2= n2.children.get(j);
        if(areMirror(c1, c2)==false)
        {
            return false;
        }
    }
    return true;
  }

  public static boolean IsSymmetric(Node node) {
    return areMirror(node, node);
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
    boolean sym = IsSymmetric(root);
    System.out.println(sym);
    // display(root);
  }

}