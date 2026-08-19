import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int ans = n * 2;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (col >= 2 && col <= 9) {
                map.put(row, map.getOrDefault(row, 0) | (1 << col));
            }
        }

        for (int mask : map.values()) {

            boolean left = (mask & 60) == 0;    // 2-5
            boolean middle = (mask & 240) == 0; // 4-7
            boolean right = (mask & 960) == 0;  // 6-9

            if (left && right) {
                // Two families can sit.
            } 
            else if (left || middle || right) {
                // Only one family can sit.
                ans--;
            } 
            else {
                // No family can sit.
                ans -= 2;
            }
        }

        return ans;
    }
}