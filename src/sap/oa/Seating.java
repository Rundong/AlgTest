package sap.oa;

import java.util.*;

public class Seating {

//    // check if the j-th seat can be used and compatible with any of the combinations for the last row
//    private static boolean checkSeatAvailability(int j, List<int[]> lastRows) {
//        if (lastRows.size() == 0)
//            return true;
//        for (int[] row : lastRows) {
//            boolean flag = true;
//            for (int k = 0; k <= j + 1; k++) {
//                if (k > 0 && row[k-1] == 1 || k < row.length - 1 && row[k+1] == 1) {
//                    flag = false;
//                    break;
//                }
//            }
//            if (flag)
//                return true;
//        }
//        return false;
//    }
//
//    // generate all combinations for the r-th row that are compatible with any of the last row's combinations
//    private static List<int[]> genCombsOld(int r, int C, String[] matrix, List<int[]> lastRows) {
//        List<int[]> combs = new ArrayList<>();
//        combs.add(new int[C]);
//        for (int j = 0; j < matrix[r].length(); j++) {
//            List<int[]> tempCombs = new ArrayList<>(combs.size());
//            if (matrix[r].charAt(j) == '+') {
//                for (int[] comb : combs) {
//                    comb[j] = 0;
//                }
//            } else {  // matrix[r].charAt(j) == '.'
//                for (int[] comb : combs) {
//                    // check if this seat can be used
//                    if (j == 0 || comb[j - 1] == 0 && checkSeatAvailability(j, lastRows)) {
//                        int[] newComb = new int[C];
//                        System.arraycopy(comb, 0, newComb, 0, j);
//                        newComb[j] = 1;
//                        tempCombs.add(newComb);
//                    }
//                    // the case where this case is not used
//                    comb[j] = 0;
//                }
//                combs.addAll(tempCombs);
//            }
//        }
//        return combs;
//    }

    // generate all combinations for the r-th row that are compatible with any of the last row's combinations
    private static Map<List<Integer>, Integer> genCombs(int r, int C, String[] matrix, Set<List<Integer>> lastRows,
                                        Map<List<Integer>, Integer> lastMaxSeated) {
        List<List<Integer>> combs = new ArrayList<>();
        Map<List<Integer>, Integer> maxSeated = new HashMap<>();
        for (List<Integer> lastRow : lastRows) {
            List<List<Integer>> tempCombs = new ArrayList<>();
            tempCombs.add(new ArrayList<>(C));
            for (int i = 0; i < C; i++) {
                tempCombs.get(0).add(0);
            }
            for (int j = 0; j < C; j++) {
                if (matrix[r].charAt(j) == '.' &&
                        (j == 0 || lastRow.get(j-1) == 0) &&
                        (j == C - 1 || lastRow.get(j+1) == 0)) {  // the j-th seat can be used
                    int oldCombsSize = tempCombs.size();
                    for (int k = 0; k < oldCombsSize; k++) {
                        if (j == 0 || tempCombs.get(k).get(j - 1) == 0) {
                            List<Integer> newComb = new ArrayList<>(tempCombs.get(k));
                            newComb.set(j, 1);
                            tempCombs.add(newComb);
                        }
                    }
                }
            }
            // update maxSeated
            int maxForCurLastRow = lastMaxSeated.getOrDefault(lastRow, 0);
            for (List<Integer> comb : tempCombs) {
                int count = maxForCurLastRow;
                for (int seat : comb) {
                    if (seat == 1)
                        count++;
                }
                if (!maxSeated.containsKey(comb) || maxSeated.get(comb) < count)
                    maxSeated.put(comb, count);
            }
        }

        return maxSeated;
    }

    // find the max number of people that can be seated
    private static int findMaxNumOfSeats(int R, int C, String[] matrix) {
        List<Integer> firstComb = new ArrayList<>(C);
        for (int i = 0; i < C; i++) {
            firstComb.add(0);
        }
        Map<List<Integer>, Integer> map = new HashMap<>();
        map.put(firstComb, 0);
        for (int i = 0; i < R; i++) {
            map = genCombs(i, C, matrix, map.keySet(), map);
//            System.out.println("--Row " + i);
//            for (List<Integer> comb : map.keySet()) {
//                System.out.println(comb.toString() + " --> " + map.get(comb));
//            }
        }

        // find the max number
        int max = 0;
        for (int num : map.values()) {
            if (num > max)
                max = num;
        }
        return max;
    }

    public static void main(String[] args) {
        String[] seats1 = new String[]{
                "+...+",
                ".+.+.",
                "..+..",
                ".+.+.",
                "+...+"
        };
        int max1 = findMaxNumOfSeats(5,5,seats1);
        System.out.println("max1: " + max1);

        String[] seats2 = new String[]{
                "...+.",
                "+...+"
        };
        int max2 = findMaxNumOfSeats(2, 5, seats2);
        System.out.println("max2: " + max2);
    }

}
