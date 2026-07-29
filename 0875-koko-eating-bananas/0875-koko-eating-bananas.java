class Solution {
    private long calculateTotalHours(int[] piles, int speed) {
    long total = 0;

    for (int banana : piles) {
        total += (banana + speed - 1) / speed;
    }

    return total;
    }
    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = 0;
        for(int banana : piles){
            maxPile = Math.max(maxPile , banana);
        }

        int low = 1 , high = maxPile;
        int ans = maxPile;

        while(low <= high){
            int mid = low + (high - low) / 2;
            long totalHours = calculateTotalHours(piles , mid);
            if(totalHours <= h){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return ans;
    }
}