package leetcode;

public class LC_ReverseWordsInString {

    public static void main(String[] args) {
        LC_ReverseWordsInString obj = new LC_ReverseWordsInString();
        System.out.println(obj.reverseWords("  the sky   is blue  "));
        System.out.println(obj.reverseWords("the sky   is blue"));
        System.out.println(obj.reverseWords("a"));
    }

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder("");
        for(int i = s.length() - 1; i >= 0; i--) {
            if(s.charAt(i) != ' ') {
                int j = i - 1;
                while(j >= 0 && s.charAt(j) != ' ')
                    --j;
                sb.append(s.substring(j+1, i+1)).append(' ');
                i = j;
            }
        }
        return sb.toString().trim();
    }
}
