package leetcode;

public class LC_FirstMissingPositive {

    public static void main(String[] args) {
        LC_FirstMissingPositive obj = new LC_FirstMissingPositive();
        System.out.println(obj.firstMissingPositive(new int[]{-1,4,2,1,9,10}));
        System.out.println(obj.firstMissingPositive(new int[]{1,1}));
    }

    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0 && nums[i] < nums.length) {
                int temp = nums[i];
                if(nums[temp - 1] == temp) // notice: this is to avoid infinite loop
                    continue;
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
                if(nums[i] != i+1)  // notice: avoid infinite loop
                    i--;   // notice: the new nums[i] may not be at the right location ( nums[i] - 1 ), so we need to revisit it
            }
        }
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1)
                return i + 1;
        }
        return nums.length + 1;
    }

}
