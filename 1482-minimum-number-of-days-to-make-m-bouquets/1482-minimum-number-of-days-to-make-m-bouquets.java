class Solution {
    public static boolean isPossible(int[] bloomDays, int day, int m, int k) {
        int count = 0;
        int bouquets = 0;

        for (int bloom : bloomDays) {
            if (bloom <= day) {
                count++;
            } else {
                bouquets += (count / k);    
                count = 0;
            }
        }
        bouquets += (count / k);
        return bouquets >= m;
    }
    public int minDays(int[] bloomDay, int m, int k) {
        long required = (long)m * k ;
        if(required > bloomDay.length){
            return -1;
        } 

        int maxDay = Integer.MIN_VALUE;
        int minDay = Integer.MAX_VALUE;

        for(int bloom : bloomDay){
            maxDay = Math.max(maxDay , bloom);
            minDay = Math.min(minDay , bloom);
        }

        int low = minDay , high = maxDay;
        int ans = -1;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(isPossible(bloomDay , mid , m , k)){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return ans;
    }
}