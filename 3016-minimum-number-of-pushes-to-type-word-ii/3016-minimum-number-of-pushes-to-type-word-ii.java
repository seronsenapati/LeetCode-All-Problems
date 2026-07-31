class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];

        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        Arrays.sort(freq);

        int ans = 0;
        int push = 1;

        for (int i = 25, cnt = 0; i >= 0 && freq[i] > 0; i--, cnt++) {
            ans += freq[i] * push;
            if ((cnt + 1) % 8 == 0) {
                push++;
            }
        }

        return ans;
    }
}