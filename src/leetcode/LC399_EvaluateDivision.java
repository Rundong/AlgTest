package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LC399_EvaluateDivision {

    public static void main(String[] args) {
        LC399_EvaluateDivision obj = new LC399_EvaluateDivision();

        String[][] equations = new String[][]{{"a", "b"}, {"b", "c"}};
        double[] values = new double[]{2.0, 3.0};
        String[][] queries = new String[][]{{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        double[] results;
//        results = obj.calcEquation(equations, values, queries);
//        for(double result : results) {
//            System.out.print(result + ", ");
//        }
//        System.out.println();

        equations = new String[][]{{"a","b"},{"e","f"},{"b","e"}};
        values = new double[]{3.4,1.4,2.3};
        queries = new String[][]{{"b","a"},{"a","f"},{"f","f"},{"e","e"},{"c","c"},{"a","c"},{"f","e"}};
        results = obj.calcEquation(equations, values, queries);
        for(double result : results) {
            System.out.print(result + ", ");
        }
        System.out.println();
    }

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, Node> map = new HashMap<>();
        for(int i = 0; i < equations.length; i++) {
            String s1 = equations[i][0], s2 = equations[i][1];
            if(map.containsKey(s1) && map.containsKey(s2)) {
                union(map.get(s1), map.get(s2), values[i], map);
            } else if (map.containsKey(s1)) {
                Node node1 = map.get(s1);
                Node node2 = new Node(node1.value / values[i], node1);
                map.put(s2, node2);
            } else if (map.containsKey(s2)) {
                Node node2 = map.get(s2);
                Node node1 = new Node(values[i] * node2.value, node2);
                map.put(s1, node1);
            } else {
                Node node2 = new Node(1);
                Node node1 = new Node(values[i], node2);
                map.put(s1, node1);
                map.put(s2, node2);
            }
        }

        double[] results = new double[queries.length];
        for(int i = 0; i < queries.length; i++) {
            String s1 = queries[i][0], s2 = queries[i][1];
            if(!map.containsKey(s1) || !map.containsKey(s2) || findEndOfChain(map.get(s1)) != findEndOfChain(map.get(s2))) {
                results[i] = -1;
            } else {
                results[i] = map.get(s1).value / map.get(s2).value;
            }
        }
        return results;
    }

    private Node findEndOfChain(Node node) {
        if(node.denom == node)
            return node;
        node.denom = findEndOfChain(node.denom);
        return node.denom;
    }

    private void union(Node node1, Node node2, double value, Map<String, Node> map) {
        Node p1 = findEndOfChain(node1), p2 = findEndOfChain(node2);
        double ratio = node2.value * value / node1.value;
        for(Node node : map.values()) {
            if(findEndOfChain(node) == p1)
                node.value *= ratio;
        }
        p1.denom = p2;
    }

    class Node {
        double value; // value = parent / this
        Node denom;

        public Node(double val) {
            this.denom = this;
            this.value = val;
        }

        public Node(double val, Node den) {
            this.denom = den;
            this.value = val;
        }

    }
}
