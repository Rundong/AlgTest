package leetcode;

import java.util.*;

public class LC267_PalindromePermutationII {

    //["aabbccccbbaa","aabcbccbcbaa","aabccbbccbaa","aacbbccbbcaa","aacbcbbcbcaa","aaccbbbbccaa","ababccccbaba","abacbccbcaba","abaccbbccaba","abbaccccabba","abbcaccacbba","abbccaaccbba","abcbaccabcba","abcbcaacbcba","abcabccbacba","abcacbbcacba","abccabbaccba","abccbaabccba","acbbaccabbca","acbbcaacbbca","acbabccbabca","acbacbbcabca","acbcabbacbca","acbcbaabcbca","acabbccbbaca","acabcbbcbaca","acacbbbbcaca","accbabbabcca","accbbaabbcca","accabbbbacca","accbabbabcca","accbbaabbcca","baabccccbaab","baacbccbcaab","baaccbbccaab","babaccccabab","babcaccacbab","babccaaccbab","bacbaccabcab","bacbcaacbcab","bacabccbacab","bacacbbcacab","baccabbaccab","baccbaabccab","bbaaccccaabb","bbacaccacabb","bbaccaaccabb","bbcaaccaacbb","bbcacaacacbb","bbccaaaaccbb","bcabaccabacb","bcabcaacbacb","bcaabccbaacb","bcaacbbcaacb","bcacabbacacb","bcacbaabcacb","bcbaaccaabcb","bcbacaacabcb","bcbcaaaacbcb","bcabaccabacb","bcabcaacbacb","bcaabccbaacb","bcaacbbcaacb","bcacabbacacb","bcacbaabcacb","bccbaaaabccb","bccabaabaccb","bccaabbaaccb","cabbaccabbac","cabbcaacbbac","cababccbabac","cabacbbcabac","cabcabbacbac","cabcbaabcbac","caabbccbbaac","caabcbbcbaac","caacbbbbcaac","cacbabbabcac","cacbbaabbcac","cacabbbbacac","cacbabbabcac","cacbbaabbcac","cbabaccababc","cbabcaacbabc","cbaabccbaabc","cbaacbbcaabc","cbacabbacabc","cbacbaabcabc","cbbaaccaabbc","cbbacaacabbc","cbbcaaaacbbc","cbabaccababc","cbabcaacbabc","cbaabccbaabc","cbaacbbcaabc","cbacabbacabc","cbacbaabcabc","cbcbaaaabcbc","cbcabaabacbc","cbcaabbaacbc","cabbaccabbac","cabbcaacbbac","cababccbabac","cabacbbcabac","cabcabbacbac","cabcbaabcbac","caabbccbbaac","caabcbbcbaac","caacbbbbcaac","cacbabbabcac","cacbbaabbcac","cacabbbbacac","cacbabbabcac","cacbbaabbcac","ccbbaaaabbcc","ccbabaababcc","ccbaabbaabcc","ccabbaabbacc","ccababbabacc","ccaabbbbaacc"]
    //["aabbccccbbaa","aabcbccbcbaa","aabccbbccbaa","aacbbccbbcaa","aacbcbbcbcaa","aaccbbbbccaa","ababccccbaba","abacbccbcaba","abaccbbccaba","abbaccccabba","abbcaccacbba","abbccaaccbba","abcabccbacba","abcacbbcacba","abcbaccabcba","abcbcaacbcba","abccabbaccba","abccbaabccba","acabbccbbaca","acabcbbcbaca","acacbbbbcaca","acbabccbabca","acbacbbcabca","acbbaccabbca","acbbcaacbbca","acbcabbacbca","acbcbaabcbca","accabbbbacca","accbabbabcca","accbbaabbcca","baabccccbaab","baacbccbcaab","baaccbbccaab","babaccccabab","babcaccacbab","babccaaccbab","bacabccbacab","bacacbbcacab","bacbaccabcab","bacbcaacbcab","baccabbaccab","baccbaabccab","bbaaccccaabb","bbacaccacabb","bbaccaaccabb","bbcaaccaacbb","bbcacaacacbb","bbccaaaaccbb","bcaabccbaacb","bcaacbbcaacb","bcabaccabacb","bcabcaacbacb","bcacabbacacb","bcacbaabcacb","bcbaaccaabcb","bcbacaacabcb","bcbcaaaacbcb","bccaabbaaccb","bccabaabaccb","bccbaaaabccb","caabbccbbaac","caabcbbcbaac","caacbbbbcaac","cababccbabac","cabacbbcabac","cabbaccabbac","cabbcaacbbac","cabcabbacbac","cabcbaabcbac","cacabbbbacac","cacbabbabcac","cacbbaabbcac","cbaabccbaabc","cbaacbbcaabc","cbabaccababc","cbabcaacbabc","cbacabbacabc","cbacbaabcabc","cbbaaccaabbc","cbbacaacabbc","cbbcaaaacbbc","cbcaabbaacbc","cbcabaabacbc","cbcbaaaabcbc","ccaabbbbaacc","ccababbabacc","ccabbaabbacc","ccbaabbaabcc","ccbabaababcc","ccbbaaaabbcc"]

    public static void main(String[] args) {
        LC267_PalindromePermutationII obj = new LC267_PalindromePermutationII();
        String input = "aaaabbbbcccc";
        List<String> res = obj.generatePalindromes(input);
        System.out.println(res.toString());
    }

    public List<String> generatePalindromes(String s) {
        HashMap<Character, Integer> counts = new HashMap<>();
        for(char c : s.toCharArray()) {
            int count = counts.getOrDefault(c, 0);
            counts.put(c, count + 1);
        }

        List<String> res = new ArrayList<>();
        int numOdds = 0;
        char center = ' ';
        for(char c : counts.keySet()) {
            int count = counts.get(c);
            if(count % 2 == 1) {
                if(++numOdds > 1) return res;
                center = c;
            }
            counts.put(c, count / 2);
        }

        // build a char array using half the palindrome
        char[] half = new char[s.length() / 2];
        int i = 0;
        for(char c : counts.keySet()) {
            for(int j = 0; j < counts.get(c); j++)
                half[i++] = c;
        }
        // System.out.println(s + "-> " + Arrays.toString(half));
        // building the permutations
        Set<String> halves = new HashSet<>();
        permute(halves, half, 0);
        System.out.println(s + ": " + halves.toString());
        System.out.println("num of halves: " + halves.size());

        // add center (if odd) and the second half (reverse of the first half)
        if(numOdds == 1) {
            for(String str : halves) {
                res.add(str + center + (new StringBuilder(str).reverse()));
            }
        } else {
            for(String str : halves) {
                res.add(str + (new StringBuilder(str).reverse()));
            }
        }
        return res;
    }

    private void permute(Set<String> res, char[] arr, int idx) {
        if(idx == arr.length)
            res.add(new String(arr));
        else {
            for(int i = idx; i < arr.length; i++) {
                if(i == idx || arr[i] != arr[i - 1]) {
                    swap(arr, idx, i);
                    permute(res, arr, idx + 1);
                    swap(arr, idx, i);
                }
            }
        }
    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
