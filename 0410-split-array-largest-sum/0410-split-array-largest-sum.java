class Solution {
    public int countPartitions(int arr[] , int maxSum){
        int partition = 1;
        int subarraySum = 0;

        for(int num : arr){
            if(subarraySum + num <= maxSum){
                subarraySum += num;
            }else{
                partition++;
                subarraySum = num;
            }
        }

        return partition;
    }
    public int splitArray(int[] nums, int k) {
        int low = 0 , high = 0;
        for(int num : nums){
            low = Math.max(low , num);
            high += num;
        }

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(countPartitions(nums , mid) > k){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }

        return low ;
    }
}