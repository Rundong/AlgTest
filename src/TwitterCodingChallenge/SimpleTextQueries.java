package TwitterCodingChallenge;

import java.util.*;

public class SimpleTextQueries {
    public static void main(String[] args) {
        List<String> sentences = new ArrayList<>();
        sentences.add("bob and alice like to test each other");
        sentences.add("bob does not like to ski but does not like to fall");
        sentences.add("alice likes to ski");

        Map<String, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < sentences.size(); i++) {
            String[] words = sentences.get(i).split("[\\s]");
            for (String word : words) {
                List<int[]> pairs;
                if (!map.containsKey(word)) {
                    pairs = new ArrayList<>();
                    pairs.add(new int[]{i, 1});
                    map.put(word, pairs);
                } else {
                    pairs = map.get(word); // pairs must has at least one element
                    int[] pair = pairs.get(pairs.size() - 1);
                    if (pair[0] == i)
                        pair[1]++;
                    else {
                        pairs.add(new int[]{i, 1});
                    }
                }
            }
        }

        System.out.println("alice");
        for (int[] pair : map.get("alice")) {
            System.out.println(pair[0] + " : " + pair[1]);
        }
        System.out.println("like:");
        for (int[] pair : map.get("like")) {
            System.out.println(pair[0] + " : " + pair[1]);
        }

        List<String> queries = new ArrayList<>();
        queries.add("bob alice");
        queries.add("alice");
        queries.add("like");

        for (String query : queries) {
            System.out.println(query);

            Queue<int[]> qu = new LinkedList<>();
            String[] words = query.split("[\\s]");

            // find all the sentences containing the first word
            List<int[]> firstPairs = map.get(words[0]);
            if (firstPairs == null) {
                System.out.println("-1");
                continue;
            }
            for (int[] pair : firstPairs)
                qu.offer(new int[]{pair[0], pair[1]});

            // for the remaining words, prune the results
            boolean flag = false;
            for (int j = 1; j < words.length; j++) {
                List<int[]> pairs = map.get(words[j]);
                if (pairs == null) {
                    flag = true;
                    break;
                }
                int pairIndex = 0;
                int[] pair = pairs.get(pairIndex++);
                int size = qu.size();
                while (size-- > 0) {
                    int[] resPair = qu.poll();
                    while (pair[0] < resPair[0]) { // make sure pair[0] >= resPair[0]
                        if (pairIndex < pairs.size())
                            pair = pairs.get(pairIndex++);
                    }
                    if (resPair[0] < pair[0])
                        continue;
                    resPair[1] = Math.min(resPair[1], pair[1]);
                    qu.offer(resPair);
                }
            }
            if (flag || qu.isEmpty())
                System.out.println("-1");
            else {
                while (!qu.isEmpty()) {
                    int[] pair = qu.poll();
                    while (pair[1]-- > 0) {
                        System.out.print(pair[0] + " ");
                    }
                }
                System.out.println();
            }
        }
    }

    public static void useTrie() {
        List<String> sentences = new ArrayList<>();
        sentences.add("bob and alice like to test each other");
        sentences.add("bob does not like to ski but does not like to fall");
        sentences.add("alice likes to ski");

        TrieNode root = new TrieNode();
        root.insertSentences(sentences);

        System.out.println("alice");
        for (int[] pair : root.searchWord("alice")) {
            System.out.println(pair[0] + " : " + pair[1]);
        }
        System.out.println("like:");
        for (int[] pair : root.searchWord("like")) {
            System.out.println(pair[0] + " : " + pair[1]);
        }

        List<String> queries = new ArrayList<>();
        queries.add("bob alice");
        queries.add("alice");
        queries.add("like");

        for (String query : queries) {
            System.out.println(query);

            ArrayDeque<int[]> qu = new ArrayDeque<>(10);
            String[] words = query.split("[\\s]");

            // find all the sentences containing the first word
            List<int[]> firstPairs = root.searchWord(words[0]);
            if (firstPairs == null) {
                System.out.println("-1");
                continue;
            }
            for (int[] pair : firstPairs)
                qu.addLast(new int[]{pair[0], pair[1]});

            // for the remaining words, prune the results
            boolean flag = false;
            for (int j = 1; j < words.length; j++) {
                List<int[]> pairs = root.searchWord(words[j]);
                if (pairs == null) {
                    flag = true;
                    break;
                }
                int pairIndex = 0;
                int[] pair = pairs.get(pairIndex++);
                int size = qu.size();
                while (size-- > 0) {
                    int[] resPair = qu.poll();
                    while (pair[0] < resPair[0]) { // make sure pair[0] >= resPair[0]
                        if (pairIndex < pairs.size())
                            pair = pairs.get(pairIndex++);
                    }
                    if (resPair[0] < pair[0])
                        continue;
                    resPair[1] = Math.min(resPair[1], pair[1]);
                    qu.offerLast(resPair);
                }
            }
            if (flag || qu.isEmpty())
                System.out.println("-1");
            else {
                while (!qu.isEmpty()) {
                    int[] pair = qu.poll();
                    while (pair[1]-- > 0) {
                        System.out.print(pair[0] + " ");
                    }
                }
                System.out.println();
            }
        }
    }

    public static class TrieNode {
        Map<Character, TrieNode> children; // a-zA-z
        List<int[]> pairList;

        public TrieNode() {
            children = new HashMap<>(); // A-Za-z
            pairList = null;
        }

        public List<int[]> searchWord(String word) {
            TrieNode cur = this;
            for (int i = 0; i < word.length(); i++) {
                if (!cur.children.containsKey(word.charAt(i)))
                    return null;
                cur = cur.children.get(word.charAt(i));
            }
            if (cur.pairList == null)
                return null;
            return cur.pairList;
        }

        public void insertSentences(List<String> sentences) {
            for (int i = 0; i < sentences.size(); i++) {
                String[] words = sentences.get(i).split("[\\s]");
                insertASentence(words, i);
            }
        }

        private void insertASentence(String[] words, int sentenceID) {
            for (String word : words) {
                insertAWord(word, sentenceID);
            }
        }

        private void insertAWord(String word, int sentenceID) {
            TrieNode cur = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!cur.children.containsKey(c))
                    cur.children.put(c, new TrieNode());
                cur = cur.children.get(c);
            }
            if (cur.pairList == null) {
                cur.pairList = new ArrayList<>();
                cur.pairList.add(new int[]{sentenceID, 1});
            } else if (cur.pairList.size() > 0) {
                int[] lastPair = cur.pairList.get(cur.pairList.size() - 1);
                if (lastPair[0] == sentenceID) { // this word has appeared in this sentence already
                    lastPair[1]++;
                } else {
                    cur.pairList.add(new int[]{sentenceID, 1});
                }
            } else {
                cur.pairList.add(new int[]{sentenceID, 1});
            }

        }

        private int getIndex(char c) {
            if (c <= 'Z') { // 'A' < 'Z' < 'a' < 'z'
                return c - 'A';
            } else {
                return c - 'a';
            }
        }
    }

    /*public static class TrieNode {
        TrieNode[] children; // a-zA-z
        List<int[]> pairList;

        public TrieNode() {
            children = new TrieNode[52]; // A-Za-z
            pairList = null;
        }

        public List<int[]> searchWord(String word) {
            TrieNode cur = this;
            for (int i = 0; i < word.length(); i++) {
                int nodeIndex = getIndex(word.charAt(i));
                cur = cur.children[nodeIndex];
                if (cur == null)
                    return null;
            }
            if (cur.pairList == null)
                return null;
            return cur.pairList;
        }

        public void insertSentences(List<String> sentences) {
            for (int i = 0; i < sentences.size(); i++) {
                String[] words = sentences.get(i).split("[\\s]");
                insertASentence(words, i);
            }
        }

        private void insertASentence(String[] words, int sentenceID) {
            for (String word : words) {
                insertAWord(word, sentenceID);
            }
        }

        private void insertAWord(String word, int sentenceID) {
            TrieNode cur = this;
            for (int i = 0; i < word.length(); i++) {
                int nodeIndex = getIndex(word.charAt(i));
                if (cur.children[nodeIndex] == null)
                    cur.children[nodeIndex] = new TrieNode();
                cur = cur.children[nodeIndex];
            }
            if (cur.pairList == null) {
                cur.pairList = new ArrayList<>();
                cur.pairList.add(new int[]{sentenceID, 1});
            } else if (cur.pairList.size() > 0) {
                int[] lastPair = cur.pairList.get(cur.pairList.size() - 1);
                if (lastPair[0] == sentenceID) { // this word has appeared in this sentence already
                    lastPair[1]++;
                } else {
                    cur.pairList.add(new int[]{sentenceID, 1});
                }
            } else {
                cur.pairList.add(new int[]{sentenceID, 1});
            }

        }

        private int getIndex(char c) {
            if (c <= 'Z') { // 'A' < 'Z' < 'a' < 'z'
                return c - 'A';
            } else {
                return c - 'a';
            }
        }
    }*/

}
