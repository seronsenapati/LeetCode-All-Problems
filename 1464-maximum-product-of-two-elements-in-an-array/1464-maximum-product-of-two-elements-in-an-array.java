class Solution {
    public int maxProduct(int[] nums) {
        int firstHigh = Integer.MIN_VALUE;
        int secondHigh = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > firstHigh) {
                secondHigh = firstHigh;
                firstHigh = num;
            } else if (num > secondHigh) {
                secondHigh = num;
            }
        }

        return (firstHigh - 1) * (secondHigh - 1);
    }
}