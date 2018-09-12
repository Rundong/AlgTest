package leetcode;

public class LC125_ValidPalindrome {

    public static void main(String[] args) {
        LC125_ValidPalindrome obj = new LC125_ValidPalindrome();
        String inputs = "\"aabbccccbbaa\",\"aabcbccbcbaa\",\"aabccbbccbaa\",\"aacbbccbbcaa\",\"aacbcbbcbcaa\",\"aaccbbbbccaa\",\"ababccccbaba\",\"abacbccbcaba\",\"abaccbbccaba\",\"abbaccccabba\",\"abbcaccacbba\",\"abbccaaccbba\",\"abcbaccabcba\",\"abcbcaacbcba\",\"abcabccbacba\",\"abcacbbcacba\",\"abccabbaccba\",\"abccbaabccba\",\"acbbaccabbca\",\"acbbcaacbbca\",\"acbabccbabca\",\"acbacbbcabca\",\"acbcabbacbca\",\"acbcbaabcbca\",\"acabbccbbaca\",\"acabcbbcbaca\",\"acacbbbbcaca\",\"accbabbabcca\",\"accbbaabbcca\",\"accabbbbacca\",\"accbabbabcca\",\"accbbaabbcca\",\"baabccccbaab\",\"baacbccbcaab\",\"baaccbbccaab\",\"babaccccabab\",\"babcaccacbab\",\"babccaaccbab\",\"bacbaccabcab\",\"bacbcaacbcab\",\"bacabccbacab\",\"bacacbbcacab\",\"baccabbaccab\",\"baccbaabccab\",\"bbaaccccaabb\",\"bbacaccacabb\",\"bbaccaaccabb\",\"bbcaaccaacbb\",\"bbcacaacacbb\",\"bbccaaaaccbb\",\"bcabaccabacb\",\"bcabcaacbacb\",\"bcaabccbaacb\",\"bcaacbbcaacb\",\"bcacabbacacb\",\"bcacbaabcacb\",\"bcbaaccaabcb\",\"bcbacaacabcb\",\"bcbcaaaacbcb\",\"bcabaccabacb\",\"bcabcaacbacb\",\"bcaabccbaacb\",\"bcaacbbcaacb\",\"bcacabbacacb\",\"bcacbaabcacb\",\"bccbaaaabccb\",\"bccabaabaccb\",\"bccaabbaaccb\",\"cabbaccabbac\",\"cabbcaacbbac\",\"cababccbabac\",\"cabacbbcabac\",\"cabcabbacbac\",\"cabcbaabcbac\",\"caabbccbbaac\",\"caabcbbcbaac\",\"caacbbbbcaac\",\"cacbabbabcac\",\"cacbbaabbcac\",\"cacabbbbacac\",\"cacbabbabcac\",\"cacbbaabbcac\",\"cbabaccababc\",\"cbabcaacbabc\",\"cbaabccbaabc\",\"cbaacbbcaabc\",\"cbacabbacabc\",\"cbacbaabcabc\",\"cbbaaccaabbc\",\"cbbacaacabbc\",\"cbbcaaaacbbc\",\"cbabaccababc\",\"cbabcaacbabc\",\"cbaabccbaabc\",\"cbaacbbcaabc\",\"cbacabbacabc\",\"cbacbaabcabc\",\"cbcbaaaabcbc\",\"cbcabaabacbc\",\"cbcaabbaacbc\",\"cabbaccabbac\",\"cabbcaacbbac\",\"cababccbabac\",\"cabacbbcabac\",\"cabcabbacbac\",\"cabcbaabcbac\",\"caabbccbbaac\",\"caabcbbcbaac\",\"caacbbbbcaac\",\"cacbabbabcac\",\"cacbbaabbcac\",\"cacabbbbacac\",\"cacbabbabcac\",\"cacbbaabbcac\",\"ccbbaaaabbcc\",\"ccbabaababcc\",\"ccbaabbaabcc\",\"ccabbaabbacc\",\"ccababbabacc\",\"ccaabbbbaacc\"";
        String[] strs = inputs.split(",");
        int count = 0;
        for(String str : strs) {
            if (!obj.isPalindrome(str))
                System.out.println("not palindrome: " + str);
            count++;
        }
        System.out.println("count="+count);

        for(String str1 : strs) {
            for(String str2 : strs) {
                if(str1.equalsIgnoreCase(str2)) {
                    System.out.println("repeated: " + str1 + " and " + str2);
                }
            }
        }
    }

    public boolean isPalindrome(String s) {
        if(s.length() <= 1) return true;
        char[] letters = s.toLowerCase().toCharArray();
        int k = 0;
        for(int i = 0; i < letters.length; i++) {
            if((letters[i] >= '0' && letters[i] <= '9') || (letters[i] >= 'a' && letters[i] <= 'z'))
                letters[k++] = letters[i];
        }
        //System.out.println(Arrays.toString(letters) + k);
        if(k <= 1) return true;
        int i = (k - 2) / 2;
        int j = (k + 1) / 2;
        for(; i >= 0; i--, j++) {
            if(letters[i] != letters[j])
                return false;
        }
        return true;
    }
}
