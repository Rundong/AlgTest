package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LC_IntersectionTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
//        Set<Integer> resultSet = new HashSet<>(Arrays.<Integer>asList(nums1)); // https://stackoverflow.com/questions/10858476/java-arraylist-cannot-find-constructor-using-arrays-aslist
        Set<Integer> resultSet = new HashSet<>();
        HashSet<Integer> set1 = new HashSet<>();

        for(int num : nums2) {
            if(set1.contains(num)) {
                resultSet.add(num);
            }
        }

        int[] results = new int[resultSet.size()];
        int i = 0;
        for(int num : resultSet) {
            results[i++] = num;
        }
        return results;
    }
}
