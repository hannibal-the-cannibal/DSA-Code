package Treeslevel2.L001;
import java.util.Scanner;

public class Main {
  public static Scanner scn = new Scanner(System.in);

  public static class Node {
    int val = 0;
    Node left = null;
    Node right = null;

    Node(int val) {
      this.val = val;
    }
  }
  public static Node getTail(Node node)
  {
    while(node.right!=null)
    {
        node=node.right;
    }
    return node;
  }

  public static Node bToDLL(Node root) {
    if(root==null)
    {
        return null;
    }
    Node lres= bToDLL(root.left);
    Node rres= bToDLL(root.right);

    if(lres==null && rres==null)
    {
        return root;
    }

    if(lres==null)
    {
        root.right=rres;
        rres.left=root;
        return root;
    }
    else
    {
        Node ltail =getTail(lres);
        ltail.right=root;
        root.left=ltail;
        if(rres!=null)
        {
            root.right=rres;
            rres.left=root;
        }
        return lres;
    }
  }

 

  // input_section=================================================

  public static void display(Node node) {
    Node head = node;
    while (node != null) {
      System.out.print(node.val + " ");
      node = node.right;
      if (node == head)
        break;
    }

  }

  public static Node constructFromInOrder_(int[] in, int si, int ei) {
    if (si > ei)
      return null;

    int mid = (si + ei) / 2;
    Node node = new Node(in[mid]);

    node.left = constructFromInOrder_(in, si, mid - 1);
    node.right = constructFromInOrder_(in, mid + 1, ei);

    return node;
  }

  public static Node constructFromInOrder(int[] inOrder) {
    int n = inOrder.length;
    return constructFromInOrder_(inOrder, 0, n - 1);
  }

  public static void solve() {
    int n = scn.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++)
      arr[i] = scn.nextInt();

    int[] IDX = new int[1];
    Node root = createTree(arr, IDX);
    int fireNode = scn.nextInt();

    
  }
  public static Node createTree(int[] arr, int[] IDX) {
    if (IDX[0] > arr.length || arr[IDX[0]] == -1) {
      IDX[0]++;
      return null;
    }
    Node Treenode = new Node(arr[IDX[0]++]);
    Treenode.left = createTree(arr, IDX);
    Treenode.right = createTree(arr, IDX);

    return Treenode;
  }

  public static void main(String[] args) {
    solve();
  }
}