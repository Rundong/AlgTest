package leetcode;

import java.util.HashMap;
import java.util.Stack;

public class LC720_LongestWord {

    public static void main(String[] args) {
//        String[] words = new String[]{"w","wo","wor","worl", "world"};
        String[] words = new String[]{"a", "banana", "app", "apl", "appp", "apply", "apple"};
        LC720_LongestWord sol = new LC720_LongestWord();
        String answer = sol.longestWord(words);
        System.out.println("answer is " + answer);
    }

    public String longestWord(String[] words) {
        Trie trie = new Trie(words);
        int index = 0;
        for (String word : words) {
            trie.insert(word, index++);
        }

        String answer = "";
        Stack<Node> stack = new Stack<>();
        for (Node node : trie.root.children.values()) {
            stack.push(node);
        }
        while (!stack.empty()) {
            Node cur = stack.pop();
            if (cur.ind >= 0) {
                String curWord = words[cur.ind];
                if (curWord.length() > answer.length()
                        || curWord.length() == answer.length() && curWord.compareTo(answer) < 0) {
                    answer = curWord;
                }
                for (Node node : cur.children.values()) {
                    stack.push(node);
                }
            }
        }

        return answer;
    }
}

class Node {
    char c;
    HashMap<Character, Node> children;
    int ind;  // the index of the word in the array "words"

    public Node(char ch) {
        this.c = ch;
        this.children = new HashMap<>();
        this.ind = -1;  // by default, the word index is -1, indicating it does not exist in the array "words"
    }
}

class Trie {
    Node root;
    String[] words;

    public Trie(String[] inputWords) {
        root = new Node('0');
        words = inputWords;
    }

    public void insert(String word, int index) {
        words[index] = word;
        Node cur = root;
        for (char ch : word.toCharArray()) {
            cur.children.putIfAbsent(ch, new Node(ch));
            cur = cur.children.get(ch);
        }
        cur.ind = index;
    }
}
