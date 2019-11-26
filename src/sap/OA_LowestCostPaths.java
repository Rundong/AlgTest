package sap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class OA_LowestCostPaths {

    private static void update(int row, int col, int cost, int[][] costs, boolean[][] visited, Queue<int[]> qu) {
        costs[row][col] += cost + 1;
        visited[row][col] = true;
        qu.offer(new int[]{row, col});
    }

    /**
     * @param matrix: -1: broken path, 0: village, 1: city
     * @return lowest cost to build paths connecting all the cities and villages
     *
     * @alg: BFS. Start from all the cities, explore their 1-hop neighbors, then 2-hop, and so on.
     *   Use a 2D array to store the visit status of all cells.
     *   Keep the count of visited villages, and stop BFS when all the villages are visited.
     */
    public static int findAnswer(int[][] matrix) {
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int[][] costs = new int[matrix.length][matrix[0].length];
        int countVisited = 0, numVillages = 0;
        int totalCost = 0;
        Queue<int[]> qu = new LinkedList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 1) {  // city
                    qu.offer(new int[]{row, col});
                    costs[row][col] = 1;
                } else if (matrix[row][col] == 0)
                    numVillages++;
            }
        }

        // BFS from the cities
        while (!qu.isEmpty() && countVisited < numVillages) {
            int[] index = qu.poll();
            int row = index[0], col = index[1];
            int cost = costs[row][col];
            System.out.println("Cell " + row + "," + col + " has cost " + cost);
            // explore up
            row = row - 1;
            if (row >= 0 && matrix[row][col] == 0 && !visited[row][col]) {
                totalCost += cost + 1;
                countVisited++;
                update(row, col, cost, costs, visited, qu);
            }
            // explore down
            row = row + 2;
            if (row < matrix.length && matrix[row][col] == 0 && !visited[row][col]) {
                totalCost += cost + 1;
                countVisited++;
                update(row, col, cost, costs, visited, qu);
            }
            // explore left
            row = row - 1;
            col = col - 1;
            if (col >= 0 && matrix[row][col] == 0 && !visited[row][col]) {
                totalCost += cost + 1;
                countVisited++;
                update(row, col, cost, costs, visited, qu);
            }
            // explore right
            col = col + 2;
            if (col < matrix[0].length && matrix[row][col] == 0 && !visited[row][col]) {
                totalCost += cost + 1;
                countVisited++;
                update(row, col, cost, costs, visited, qu);
            }

            System.out.println("---costs:");
            for (int[] line : costs) {
                System.out.println(Arrays.toString(line));
            }
            System.out.println();
        }


        return totalCost;
    }

    public static void main(String[] args) {
        int[][] mat1 = new int[][]{
                {1, 0, 0},
                {0, 0, 0},
                {0, 0, 1}};
        System.out.println("mat1 answer: " + findAnswer(mat1));
    }
}
