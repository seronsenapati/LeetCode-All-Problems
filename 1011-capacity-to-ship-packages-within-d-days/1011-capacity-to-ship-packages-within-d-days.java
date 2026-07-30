class Solution {
    private int daysNeeded(int[] weights, int capacity) {
    int days = 1;
    int currentLoad = 0;

    for (int weight : weights) {
        if (currentLoad + weight > capacity) {
            days++;
            currentLoad = weight;
        } else {
            currentLoad += weight;
        }
    }

    return days;
}
    public int shipWithinDays(int[] weights, int days) {
        int low = 0 , high = 0;
        for(int weight : weights){
            low  = Math.max(low , weight);
            high += weight;
        }

        while(low <= high){
            int mid = low + (high - low) / 2;
            if(daysNeeded(weights , mid) <= days){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return low;
    }
}