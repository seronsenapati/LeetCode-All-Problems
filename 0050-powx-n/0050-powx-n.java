class Solution {

    public double power(double x, long n) {

        if (n == 0) {
            return 1.0;
        }

        if (n % 2 == 0) {
            return power(x * x, n / 2);
        }

        return x * power(x, n - 1);
    }

    public double myPow(double x, int n) {

        long exponent = n;

        if (exponent < 0) {
            return 1.0 / power(x, -exponent);
        }

        return power(x, exponent);
    }
}