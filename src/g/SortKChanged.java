package g;

import java.util.*;

public class SortKChanged {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        arr = sort(arr, 0);
        System.out.println("result: " + Arrays.toString(arr));

        arr = new int[]{1, 2, 5, 4, 5, 6, 7, 8, 9, 10};
        arr = sort(arr, 1);
        System.out.println("result: " + Arrays.toString(arr));

        arr = new int[]{1, 2, 100, 4, 5, -10, 7, 8, 9, 10};
        arr = sort(arr, 1);
        System.out.println("result: " + Arrays.toString(arr));
    }

    public static int[] sort(int[] arr, int k) {
        if (k <= 0) return arr;

        PriorityQueue<Integer> heap = new PriorityQueue<>(k + 1);
        List<Integer> small = new ArrayList<>(k);
        int i = 0;
        for ( ; i <= k; i++) {
            heap.offer(arr[i]);
        }

        int idx = 0;
        arr[idx++] = heap.poll();
        for ( ; i < arr.length; i++) {
            if (arr[i] < arr[i - k - 1]) { // if arr[i] is smaller than the previous already-set element
                small.add(arr[i]);
            } else {
                heap.offer(arr[i]);
                arr[idx++] = heap.poll();
            }

            // debug:
            if (heap.size() > k) {
                System.out.println("  heap size " + heap.size() + " is bigger than " + k);
                System.out.println("  heap: " + heap.toString());
                System.exit(-1);
            }
        }
        //System.out.println("  scanned the array, now arr: " + Arrays.toString(arr));
        //System.out.println("  i = " + i + ", heap: " + heap.toString());

        // put the biggest elements at the end
        while (!heap.isEmpty())
            arr[idx] = heap.poll();

        // sort the smallest integers which were changed randomly
        small.sort(Comparator.comparingInt(a -> a));

        if (!small.isEmpty()) {
            for (int j = arr.length - 1; j >= small.size(); j--)
                arr[j] = arr[j - small.size()];
            for (int j = 0; j < small.size(); j++)
                arr[j] = small.get(j);
        }

        return arr;
    }
}
