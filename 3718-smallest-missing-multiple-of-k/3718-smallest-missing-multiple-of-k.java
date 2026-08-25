import java.util.*;

class Solution {
    public int missingMultiple(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        for (int multiple = k; ; multiple += k) {
            if (!set.contains(multiple)) {
                return multiple;
            }
        }
    }
}