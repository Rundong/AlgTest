package leetcode;

import java.util.List;

import static org.junit.Assert.*;

public class LC305_NumIslandsTest {

    @org.junit.Test
    public void numIslands2() {
        LC305_NumIslands obj = new LC305_NumIslands();
        int[][] pos = new int[][]{{0,0},{0,1},{1,2},{2,1}};
        List<Integer> results = obj.numIslands2(3, 3, pos);
        for (int num : results)
            System.out.println(num);
        System.out.println();

        pos = new int[][]{{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}};
        results = obj.numIslands2(3, 3, pos);

        for (int num : results) {
            System.out.println(num);
        }
    }
}