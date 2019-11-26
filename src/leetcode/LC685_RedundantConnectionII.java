package leetcode;

import java.util.*;

public class LC685_RedundantConnectionII {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static void test1() {
        int[][] edges = new int[][]{{1,2},{1,3},{2,3}};
        LC685_RedundantConnectionII obj = new LC685_RedundantConnectionII();
        int[] edge = obj.findRedundantDirectedConnection(edges);
        System.out.println(Arrays.toString(edge));
    }

    private static void test2() {
        int[][] edges = new int[][]{{1,2},{2,3}, {3,4},{4,1},{1,5}};
        LC685_RedundantConnectionII obj = new LC685_RedundantConnectionII();
        int[] edge = obj.findRedundantDirectedConnection(edges);
        System.out.println(Arrays.toString(edge));
    }

    private static void test3() {
        int[][] edges = new int[][]{{2,1},{3,1},{4,2},{1,4}};
        LC685_RedundantConnectionII obj = new LC685_RedundantConnectionII();
        int[] edge = obj.findRedundantDirectedConnection(edges);
        System.out.println(Arrays.toString(edge));
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        HashMap<Integer,Integer> parent = new HashMap<>(edges.length);
        List<int[]> candidates = new ArrayList<>(2);
        for(int[] edge : edges) {
            if(parent.containsKey(edge[1])) {
                candidates.add(new int[]{parent.get(edge[1]), edge[1]});
                candidates.add(edge);
            } else {
                parent.put(edge[1],edge[0]);
            }
        }
        // find the root of the tree, or one node in a cycle
        int root = orbit(1, parent);
        if(candidates.isEmpty()) { // this means that the root must be in a cycle
            orbit(root, parent);
            for(int i = edges.length - 1; i >= 0; i--) {
                if(seen.contains(edges[i][0]) && seen.contains(edges[i][1]))
                    return edges[i];
            }
        }
        // check if the HashMap parent is a connected graph, if not, then candidates.get(0) is to be returned
        HashMap<Integer, HashSet<Integer>> children = new HashMap<>();
        for(Map.Entry<Integer,Integer> entry : parent.entrySet()) {
            if(!children.containsKey(entry.getValue())){
                children.put(entry.getValue(), new HashSet());
            }
            children.get(entry.getValue()).add(entry.getKey());
        }
        seen = new HashSet<>();
        dfs(root, children);
        if(seen.size() < edges.length)
            return candidates.get(0);
        else
            return candidates.get(1);
    }

    // for orbit and dfs function
    Set<Integer> seen = new HashSet<>();

    // orbit will return either (1) the root of the given node
    // , or (2) one of the node in a cycle if there is one
    private int orbit(int node, HashMap<Integer,Integer> parent) {
        seen = new HashSet<>();
        while(parent.containsKey(node) && !seen.contains(node)) {
            seen.add(node);
            node = parent.get(node);
        }
        return node;
    }

    private void dfs(int node, HashMap<Integer,HashSet<Integer>> children) {
        if(seen.contains(node))
            return;
        seen.add(node);
        if(children.containsKey(node)) {
            for(int child : children.get(node)) {
                dfs(child, children);
            }
        }
    }
}
