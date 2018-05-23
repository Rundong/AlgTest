package leetcode;

import java.util.*;

public class LC684 {

    public static void main(String[] args) {
//        int[][] edges = new int[3][];
//        edges[0] = new int[]{1, 2};
//        edges[1] = new int[]{1, 3};
//        edges[2] = new int[]{2 ,3};

        int[][] edges = new int[5][];
        edges[0] = new int[]{1,3};
        edges[1] = new int[]{3,4};
        edges[2] = new int[]{1,5};
        edges[3] = new int[]{3,5};
        edges[4] = new int[]{2,3};

        LC684 obj = new LC684();
        int[] result = obj.findRedundantConnection_GraphDFS(edges);
        System.out.println(result[0] + "," + result[1]);
    }

    /**
     * Construct the graph and then DFS
     */
    public int[] findRedundantConnection_GraphDFS(int[][] edges) {
        List[] graph = new ArrayList[edges.length + 1]; // graph[i] is a list of its neighbors
        for (int i = 1; i <= edges.length; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        HashMap<EdgeKey, Boolean> edgeVisited = new HashMap<>();
        HashMap<EdgeKey, Integer> edgeOrder = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
            EdgeKey edgeKey = new EdgeKey(edge[0], edge[1]);
            edgeVisited.put(edgeKey, false);
            edgeOrder.put(edgeKey, i);
        }

        List<Integer> path = new ArrayList<>();
        path.add(edges[0][0]);
        return DFS(graph, edges[0][0], edgeVisited, edgeOrder, path);
    }

    private int[] DFS(List<Integer>[] graph, int source, HashMap<EdgeKey, Boolean> edgeVisited,
                      HashMap<EdgeKey, Integer> edgeOrder, List<Integer> path) {
        for (int conn : graph[source]) {
            EdgeKey edgeKey;
            if (source < conn) {
                edgeKey = new EdgeKey(source, conn);
            } else {
                edgeKey = new EdgeKey(conn, source);
            }

            if (!edgeVisited.get(edgeKey)) { // this edge has not been visited
                if (path.contains(conn)) { // a cycle is found!
                    path.add(conn);
                    int maxOrder = 0;
                    EdgeKey additionalEdge = null;
                    for (int i = path.indexOf(conn); i < path.size() - 1; i++) {
                        EdgeKey curEdge;
                        if (path.get(i) < path.get(i+1)) {
                            curEdge = new EdgeKey(path.get(i), path.get(i+1));
                        } else {
                            curEdge = new EdgeKey(path.get(i+1), path.get(i));
                        }
                        int order = edgeOrder.get(curEdge);
                        if (order > maxOrder) {
                            additionalEdge = curEdge;
                            maxOrder = order;
                        }
                    }
                    return new int[]{additionalEdge.x, additionalEdge.y};
                }
                edgeVisited.put(edgeKey, true);
                path.add(conn);
                int[] result = DFS(graph, conn, edgeVisited, edgeOrder, path);
                if (result != null) {
                    return result;
                }
                path.remove(path.size() - 1);
            }
        }

        return null; // no cycle is found
    }

    /**
     * Use set union
     */
    public int[] findRedundantConnection_DSU(int[][] edges) {
        DSU dsu = new DSU(edges.length + 1);
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) {
                return edge;
            }
        }
        throw new AssertionError();
    }

}


class EdgeKey {

    public final int x;
    public final int y;

    public EdgeKey(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EdgeKey)) return false;
        EdgeKey key = (EdgeKey) o;
        return x == key.x && y == key.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

}



/**
 * Disjoint Set Union
 */
class DSU {
    int[] parent;
    int[] rank;

    DSU(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
        rank = new int[size];
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /**
     * @return false if x and y are already connected
     */
    boolean union(int x, int y) {
        int xr = find(x), yr = find(y);
        if (xr == yr) {
            return false;
        } else if (rank[xr] < rank[yr]) {
            parent[xr] = yr;
        } else if (rank[xr] > rank[yr]) {
            parent[yr] = xr;
        } else {
            parent[yr] = xr;
            rank[xr]++;
        }
        return true;
    }
}