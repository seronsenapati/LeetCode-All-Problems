class Solution {
    public int sumByD(int nums[] , int div){
        int sum = 0;
        for(int num : nums){
            sum += (int)Math.ceil((double)num/div);
        }
        return sum;
    }
    public int smallestDivisor(int[] nums, int threshold) {
        if(nums.length > threshold){
            return -1;
        }

        int max = 0;
        for(int num : nums){
            max = Math.max(max , num);
        }

        int low = 1 , high = max;
        
        while(low <= high){
            int mid = low + (high - low) / 2;

            if(sumByD(nums , mid) <= threshold){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return low;
    }
}