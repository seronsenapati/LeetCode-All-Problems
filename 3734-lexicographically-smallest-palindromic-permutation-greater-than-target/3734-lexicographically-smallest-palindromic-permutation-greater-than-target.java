class Solution {

    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();

        // Count characters.
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Check whether a palindrome can be formed.
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLen = n / 2;

        /*
         * pairCount[c] tells how many copies of character c
         * are available in the LEFT half.
         */
        int[] pairCount = new int[26];

        for (int i = 0; i < 26; i++) {
            pairCount[i] = freq[i] / 2;
        }

        /*
         * First check whether the left half can be exactly
         * equal to target's left half.
         */
        int[] temp = pairCount.clone();
        boolean possible = true;

        for (int i = 0; i < halfLen; i++) {
            int c = target.charAt(i) - 'a';

            if (temp[c] == 0) {
                possible = false;
                break;
            }

            temp[c]--;
        }

        /*
         * If the left half is equal to target's left half,
         * construct the palindrome and check the complete
         * string.
         */
        if (possible) {
            String left = target.substring(0, halfLen);

            String palindrome = buildPalindrome(left, middle, n);

            if (palindrome.compareTo(target) > 0) {
                return palindrome;
            }
        }

        /*
         * Now find the first position from RIGHT to LEFT
         * where we can make the left half larger.
         *
         * Everything before this position remains equal
         * to target.
         */
        for (int pivot = halfLen - 1; pivot >= 0; pivot--) {

            int[] remaining = pairCount.clone();

            // Match target[0 ... pivot-1]
            boolean valid = true;

            for (int i = 0; i < pivot; i++) {
                int c = target.charAt(i) - 'a';

                if (remaining[c] == 0) {
                    valid = false;
                    break;
                }

                remaining[c]--;
            }

            if (!valid) {
                continue;
            }

            /*
             * At pivot, choose the SMALLEST character
             * greater than target[pivot].
             */
            int targetChar = target.charAt(pivot) - 'a';

            for (int c = targetChar + 1; c < 26; c++) {

                if (remaining[c] == 0) {
                    continue;
                }

                remaining[c]--;

                /*
                 * Build the smallest possible suffix.
                 */
                StringBuilder left = new StringBuilder();

                // Prefix equal to target.
                for (int i = 0; i < pivot; i++) {
                    left.append(target.charAt(i));
                }

                // Character that makes us greater.
                left.append((char) ('a' + c));

                // Fill remaining positions in ascending order.
                for (int x = 0; x < 26; x++) {
                    while (remaining[x] > 0) {
                        left.append((char) ('a' + x));
                        remaining[x]--;
                    }
                }

                String answer = buildPalindrome(
                    left.toString(),
                    middle,
                    n
                );

                return answer;
            }
        }

        return "";
    }

    private String buildPalindrome(String left, char middle, int n) {

        StringBuilder ans = new StringBuilder();

        // Left half
        ans.append(left);

        // Middle character for odd length.
        if (n % 2 == 1) {
            ans.append(middle);
        }

        // Right half = reverse(left)
        for (int i = left.length() - 1; i >= 0; i--) {
            ans.append(left.charAt(i));
        }

        return ans.toString();
    }
}