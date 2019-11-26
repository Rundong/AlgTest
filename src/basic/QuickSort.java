package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort alg = new QuickSort();
        int[] arr = new int[]{1,3,10,4,6,8,9,7,2,5,0};
        alg.quickSort(arr, 0, arr.length - 1);
        System.out.println("sorted: " + Arrays.toString(arr));

        List<Integer> list = new ArrayList<>();
    }

    // quick sort the given array between index low and high, both inclusive
    public void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;

        int pi = partition(arr, low, high);
        //if (pi > 0)
            quickSort(arr, low, pi - 1);
        //if (pi < arr.length - 1)
            quickSort(arr, pi + 1, high);
    }

    // This function partitions the given array between index low and high,
    //      using the last element as pivot,
    //      places all smaller (smaller than pivot) to left of pivot
    //      and all greater elements to right of pivot
    // return the index of pivot, which is in the correct position.
    public int partition(int[] arr, int low, int high) {
        System.out.print("partition: ");
        for (int i = low; i <= high; i++)
            System.out.print(" " + arr[i]);

        int pivot = arr[high];
        int pi = low;
        for (int j = low; j < high; j++) { // since arr[high] is the pivot, we will not visit it in the loop
            if (arr[j] <= pivot) {
                if (pi != j)
                    swap(arr, pi, j);
                pi++;
            }
        }
        swap(arr, pi, high);
        System.out.println(" --> pivot = " + pivot + " , [" + low + "," + high + "], pi = " + pi);
        return pi;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
