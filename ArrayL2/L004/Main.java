package L004;
import java.util.*;

public class Main {

    public static int minTotalDistance(int[][] grid) {
        ArrayList<Integer> xcord= new ArrayList<>();
        for(int r=0;r<grid.length;r++)
        {
            for(int c=0;c<grid[0].length;c++)
            {
                if(grid[r][c]==1)
                {
                    xcord.add(r); // we get x in sorted order 
                }
            }
        }

        ArrayList<Integer> ycord= new ArrayList<>();
        for(int c=0;c<grid[0].length;c++)
        {
            for(int r=0;r<grid.length;r++)
            {
                if(grid[r][c]==1)
                {
                    ycord.add(c); // we get y in sorted order 
                }
            }
        }

        int xmid= xcord.get(xcord.size()/2);
        int ymid= ycord.get(ycord.size()/2);

        int dist=0;
        for(int xval: xcord)
        {
            dist+=Math.abs(xval-xmid);
        }
        for(int yval: ycord)
        {
            dist+=Math.abs(yval-ymid);
        }

        return dist;
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();


        int[][] grid = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                grid[i][j] = scn.nextInt();
            }
        }

        int dist = minTotalDistance(grid);
        System.out.println(dist);
    }
}
