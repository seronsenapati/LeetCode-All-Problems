class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Match target from left to right
        for (int i = 0; i < n; i++) {
            int x = target.charAt(i) - 'a';

            // We can keep the same character
            if (freq[x] > 0) {
                freq[x]--;
                continue;
            }

            // Cannot match target[i].
            // Try to make this position larger.
            for (int c = x + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    freq[c]--;
                    return build(target, i, c, freq);
                }
            }

            // No larger character here.
            // Backtrack to an earlier matched position.
            for (int j = i - 1; j >= 0; j--) {
                int old = target.charAt(j) - 'a';

                // Restore the character used at position j
                freq[old]++;

                // Try a character greater than target[j]
                for (int c = old + 1; c < 26; c++) {
                    if (freq[c] > 0) {
                        freq[c]--;

                        return build(target, j, c, freq);
                    }
                }
            }

            return "";
        }

        // We formed target exactly.
        // It is not strictly greater, so backtrack.
        for (int j = n - 1; j >= 0; j--) {
            int old = target.charAt(j) - 'a';

            freq[old]++;

            for (int c = old + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    freq[c]--;

                    return build(target, j, c, freq);
                }
            }
        }

        return "";
    }

    private String build(String target, int pos, int ch, int[] freq) {
        StringBuilder ans = new StringBuilder();

        // Keep target's prefix
        ans.append(target, 0, pos);

        // Put the first greater character
        ans.append((char) ('a' + ch));

        // Smallest possible remaining suffix
        for (int i = 0; i < 26; i++) {
            while (freq[i] > 0) {
                ans.append((char) ('a' + i));
                freq[i]--;
            }
        }

        return ans.toString();
    }
}