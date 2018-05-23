package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LC305_NumIslands {

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        DSU dsu = new DSU(m * n);
        int[][] matrix = new int[m][n];
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
////                matrix[i][j] = -1; // -1 means this node is empty
//                System.out.print("\t" + matrix[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();

        List<Integer> listNums = new ArrayList<>();
        int numIslands = 0;
        for (int[] position : positions) {
            int x = position[0], y = position[1];
            matrix[x][y] = 1;
            List<Integer> neighbors = new ArrayList<>();
            if (x > 0 && matrix[x - 1][y] == 1)
                neighbors.add((x - 1) * n + y);
            if (y > 0 && matrix[x][y - 1] == 1)
                neighbors.add(x * n + y - 1);
            if (x < m - 1 && matrix[x + 1][y] == 1)
                neighbors.add((x + 1) * n + y);
            if (y < n - 1 && matrix[x][y + 1] == 1)
                neighbors.add(x * n + y + 1);

            int current = x * n + y;
            if (neighbors.size() == 0) {
                numIslands++;
            } else if (neighbors.size() == 1) {
                dsu.union(neighbors.get(0), current);
            } else {
                numIslands++;
                for (int i = 0; i < neighbors.size(); i++) {
                    if (dsu.union(neighbors.get(i), current))
                        numIslands--;
                }
            }

            listNums.add(numIslands);
        }

        return listNums;
    }

    class DSU {

        int[] parent;
        int[] rank;

        public DSU(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        /**
         * All the nodes between i and the root will be updated: their parent is updated to root if not so before.
         */
        int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        /**
         * @return false if x and y are already connected
         */
        boolean union(int x, int y) {
            int xr = find(x), yr = find(y);
            if (xr == yr) {
                return false;
            } else if (rank[xr] > rank[yr]) {
                parent[yr] = xr;
            } else if (rank[xr] < rank[yr]) {
                parent[xr] = yr;
            } else {
                rank[xr]++;
                parent[yr] = xr;
                System.out.println("Node " + xr + " has rank " + rank[xr]);
            }
            return true;
        }
    }
}
