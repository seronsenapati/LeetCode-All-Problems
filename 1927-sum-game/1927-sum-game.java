class Solution {
    public boolean sumGame(String num) {
        int diff = 0;
        int q = 0;
        int mid = num.length() / 2;

        for (int i = 0; i < num.length(); i++) {
            int sign = i < mid ? 1 : -1;
            char c = num.charAt(i);

            if (c == '?') {
                q += sign;
            } else {
                diff += sign * (c - '0');
            }
        }

        return 2 * diff + 9 * q != 0;
    }
}