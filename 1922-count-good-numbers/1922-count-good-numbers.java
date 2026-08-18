class Solution {
    static final long MOD = 1000000007;
    public static long power(long x, long n) {
        if (n == 0) {
            return 1;
        }
        if (n % 2 == 0) {
            return power((x * x) % MOD , n / 2) ;
        }
        return (x * power(x, n - 1)) % MOD;
    }
    public int countGoodNumbers(long n) {   
        long even = (n + 1) / 2;
        long odd = n / 2;

        long ans = power(5, even);
        ans = (ans * power(4, odd)) % MOD;

        return (int) ans;
    }
}