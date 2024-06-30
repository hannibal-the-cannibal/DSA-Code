package Treeslevel2.L003;
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

    public static TreeNode constructFromInOrder(int[] inOrder) {
        int n= inOrder.length;
        return helperinorder(inOrder,0,n-1);

    }
    public static TreeNode helperinorder(int[] inorder, int st, int end)
    {
        if(st>end)
        {
            return null;
        }
        int mid= (st+end)/2;
        TreeNode node= new TreeNode(inorder[mid]);
        node.left= helperinorder(inorder, st, mid-1);
        node.right=helperinorder(inorder, mid+1, end);

        return node;

    }
    public static TreeNode bstFromPostorder(int[] preorder) {
        idx=preorder.length-1;
        int lr= Integer.MIN_VALUE;
        int rr= Integer.MAX_VALUE;

        return helperpostorder(preorder, lr, rr);
    }
    static int idx=0;
    public static TreeNode helperpostorder(int[] preorder, int lr, int rr)
    {
        if(idx<0 || preorder[idx]<lr || preorder[idx]>rr)
        {
            return null;
        }
        TreeNode node= new TreeNode(preorder[idx]);
        idx--;

        node.right= helperpostorder(preorder, node.val, rr);
        node.left= helperpostorder(preorder, lr, node.val);

        return node;
    }

    public static TreeNode constructBSTFromLevelOrder(int[] LevelOrder) {
        return null;
    }

    // input_section=================================================

    public static void display(TreeNode node) {
        if (node == null)
            return;

        StringBuilder sb = new StringBuilder();
        sb.append((node.left != null ? node.left.val : "."));
        sb.append(" -> " + node.val + " <- ");
        sb.append((node.right != null ? node.right.val : "."));

        System.out.println(sb.toString());

        display(node.left);
        display(node.right);

    }

    public static void solve() {
        int n = scn.nextInt();
        int[] level = new int[n];
        for (int i = 0; i < n; i++)
            level[i] = scn.nextInt();

        TreeNode root = constructBSTFromLevelOrder(level);
        display(root);
    }

    public static void main(String[] args) {
        solve();
    }
}