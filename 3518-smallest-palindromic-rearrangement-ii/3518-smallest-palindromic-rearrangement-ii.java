import java.util.*;

class Solution {
    private static final int MAX = 1_000_001;

    public String smallestPalindrome(String s, int k) {
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        if (!isPalindromePossible(count)) {
            return "";
        }

        int[] halfCount = new int[26];
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
            if ((count[i] & 1) == 1) {
                mid = (char) ('a' + i);
            }
        }

        long totalPerm = countArrangements(halfCount);

        if (k > totalPerm) {
            return "";
        }

        StringBuilder left = generateLeftHalf(halfCount, k);

        StringBuilder ans = new StringBuilder();
        ans.append(left);

        if (mid != 0) {
            ans.append(mid);
        }

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private boolean isPalindromePossible(int[] count) {
        int odd = 0;

        for (int x : count) {
            if ((x & 1) == 1) {
                odd++;
            }
        }

        return odd <= 1;
    }

    private StringBuilder generateLeftHalf(int[] halfCount, int k) {
        int len = 0;

        for (int x : halfCount) {
            len += x;
        }

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < len; pos++) {
            for (int i = 0; i < 26; i++) {
                if (halfCount[i] == 0) {
                    continue;
                }

                halfCount[i]--;

                long ways = countArrangements(halfCount);

                if (ways >= k) {
                    left.append((char) ('a' + i));
                    break;
                }

                k -= ways;
                halfCount[i]++;
            }
        }

        return left;
    }

    private long countArrangements(int[] count) {
        int total = 0;

        for (int x : count) {
            total += x;
        }

        long res = 1;

        for (int freq : count) {
            res *= nCk(total, freq);

            if (res >= MAX) {
                return MAX;
            }

            total -= freq;
        }

        return res;
    }

    private long nCk(int n, int k) {
        if (k > n) {
            return 0;
        }

        k = Math.min(k, n - k);

        long res = 1;

        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;

            if (res >= MAX) {
                return MAX;
            }
        }

        return res;
    }
}