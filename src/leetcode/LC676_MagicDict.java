package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC676_MagicDict {

    public static void main(String[] args) {
        String[] dict = new String[]{"MagicDictionary", "buildDict", "search", "search", "search", "search"};
//[[], [["hello","hallo","leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]};

        LC676_MagicDict obj = new LC676_MagicDict();
        obj.buildDict(dict);
        obj.buildDict(new String[]{"hello","hallo","leetcode"});
        boolean param_2 = obj.search("leetcode");
        System.out.println("search: " + param_2);
    }

    Set<String> words;
    Map<String, Integer> counts;

    /** Initialize your data structure here. */
    public LC676_MagicDict() {
        words = new HashSet<>();
        counts = new HashMap<>();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            words.add(word);
            char[] chars = word.toCharArray();
            for (int i = 0; i < word.length(); i++) {
                char letter = chars[i];
                chars[i] = '_';
                String edit = new String(chars);
                counts.put(edit, counts.getOrDefault(edit, 0) + 1);
                chars[i] = letter;
            }
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char letter = chars[i];
            chars[i] = '_';
            int count = counts.getOrDefault(new String(chars), 0);
            if (count > 1 || count == 1 && !words.contains(word)) {
                return true;
            }
            chars[i] = letter;
        }
        return false;
    }
}
