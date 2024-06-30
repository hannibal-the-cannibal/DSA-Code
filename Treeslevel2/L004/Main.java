package Treeslevel2.L004;
import java.util.Scanner;

public class Main {
  public static Scanner scn = new Scanner(System.in);

  public static class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int val) {
      this.val = val;
    }
  }
  public static class Pair{
    int leaftoleaf= Integer.MIN_VALUE;
    int nodetoleaf= Integer.MIN_VALUE;
  }
  public static Pair helper(TreeNode root)
  {
    Pair myans= new Pair();
    if(root==null)
    {
        return myans;
    }
    if(root.left==null && root.right==null)
    {
        myans.nodetoleaf=root.val;
        return myans;
    }
    Pair lp= helper(root.left);
    Pair rp=helper(root.right);

    myans.leaftoleaf= Math.max(lp.leaftoleaf,rp.leaftoleaf);
    if(root.left!=null && root.right!=null)
    {
        myans.leaftoleaf= Math.max(myans.leaftoleaf,lp.nodetoleaf+root.val+rp.nodetoleaf);
    }
    myans.nodetoleaf=Math.max(lp.nodetoleaf,rp.nodetoleaf)+root.val;
    return myans;


  }

  public static int maxPathSum(TreeNode root) {
    return helper(root).leaftoleaf;
  }

  // input_Section=================================================

  public static TreeNode createTree(int[] arr, int[] IDX) {
    if (IDX[0] > arr.length || arr[IDX[0]] == -1) {
      IDX[0]++;
      return null;
    }

    TreeNode node = new TreeNode(arr[IDX[0]++]);
    node.left = createTree(arr, IDX);
    node.right = createTree(arr, IDX);

    return node;
  }

  public static void solve() {
    int n = scn.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++)
      arr[i] = scn.nextInt();

    int[] IDX = new int[1];
    TreeNode root = createTree(arr, IDX);
    System.out.println(maxPathSum(root));
  }

  public static void main(String[] args) {
    solve();
  }
}