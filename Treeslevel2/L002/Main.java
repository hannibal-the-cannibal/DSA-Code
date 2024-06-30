package Treeslevel2.L002;
import java.util.*;

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
    public static class vpair{
        TreeNode node;
        int lvl;
        vpair(TreeNode node, int lvl)
        {
            this.node=node;
            this.lvl=lvl;
        }
    }
    public static void widthhelper(TreeNode root, int lvl, int[]ans)
    {
        if(root==null)
        {
            return;
        }
        ans[0]=Math.min(ans[0],lvl);
        ans[1]=Math.max(ans[1],lvl);

        widthhelper(root.left, lvl-1, ans);
        widthhelper(root.right, lvl+1, ans);
    }

    public static int width(TreeNode root) {
       int ans[]= new int[2];
       widthhelper(root,0,ans);

       return ans[1]-ans[0]+1;
    }

    public static ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans= new ArrayList<>();
        Queue<vpair> qu= new ArrayDeque<>();
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        qu.add(new vpair(root, 0));
        int max=0;
        int min=0;
        while(qu.size()!=0)
        {
            int size=qu.size();
            while(size -- >0)
            {
                vpair rem= qu.remove();
                max= Math.max(max,rem.lvl);
                min= Math.min(min,rem.lvl);
                map.putIfAbsent(rem.lvl, new ArrayList<>());
                map.get(rem.lvl).add(rem.node.val);

                if(rem.node.left!=null)
                {
                    qu.add(new vpair(rem.node.left, rem.lvl-1));
                }

                if(rem.node.right!=null)
                {
                    qu.add(new vpair(rem.node.right, rem.lvl+1));
                }

            }
        }

        for(int i=min;i<=max;i++)
        {
            ans.add(map.get(i));
        }

        return ans;

    }

    // input_section=================================================

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

        ArrayList<ArrayList<Integer>> ans = verticalOrderTraversal(root);
        int idx = 0;
        for (ArrayList<Integer> i : ans) {
            System.out.print(idx++ + " -> ");
            for (Integer j : i)
                System.out.print(j + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        solve();
    }
}