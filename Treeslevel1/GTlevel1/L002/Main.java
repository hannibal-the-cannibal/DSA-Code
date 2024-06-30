package Treeslevel1.GTlevel1.L002;
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

  public static int size(Node node) {
    int s = 0;

    for (Node child : node.children) {
      s += size(child);
    }
    s += 1;

    return s;
  }

  public static int max(Node node) {
    int m = Integer.MIN_VALUE;

    for (Node child : node.children) {
      int cm = max(child);
      m = Math.max(m, cm);
    }
    m = Math.max(m, node.data);

    return m;
  }

  public static int height(Node node) {
    int h = -1;

    for (Node child : node.children) {
      int ch = height(child);
      h = Math.max(h, ch);
    }
    h += 1;

    return h;
  }

  public static void traversals(Node node){
    System.out.println("Node Pre " + node.data);

    for(Node child: node.children){
      System.out.println("Edge Pre " + node.data + "--" + child.data);
      traversals(child);
      System.out.println("Edge Post " + node.data + "--" + child.data);
    }

    System.out.println("Node Post " + node.data);
  }

  public static void levelOrderLinewiseZZ(Node node){
    Stack<Node> ms= new Stack<>();
    Stack<Node> hs= new Stack<>();
    ms.push(node);
    int lvl=1;

    while(ms.size()!=0)
    {
        Node rem= ms.pop();
        System.out.print(rem.data+" ");

        if(lvl%2!=0)
        {
            for(int i=0;i<rem.children.size();i++)
            {
                hs.push(rem.children.get(i));
            }
        }
        else
        {
            for(int i=rem.children.size()-1;i>=0;i--)
            {
                hs.push(rem.children.get(i));
            }
        }

        if(ms.size()==0)
        {
            System.out.println();
            lvl++;
            ms=hs;
            hs= new Stack<>();
        }
    }
  }

  public static boolean find(Node node, int data) {
    if(node.data==data)
    {
        return true;
    }
    for(Node child: node.children)
    {
        boolean rec= find(child,data);
        if(rec)
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

    for(Node child: node.children)
    {
        ArrayList<Integer> rec= nodeToRootPath(child, data);
        if(rec.size()>0)
        {
            rec.add(node.data);
            return rec;
        }
        
    }
    return new ArrayList<>();
 }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(values[i]);
    }

    int data = Integer.parseInt(br.readLine());
    Node root = construct(arr);
    // levelOrderLinewiseZZ(root);
    // boolean flag = find(root, data);
    // System.out.println(flag);

    ArrayList<Integer> path = nodeToRootPath(root, data);
    System.out.println(path);
  }

}