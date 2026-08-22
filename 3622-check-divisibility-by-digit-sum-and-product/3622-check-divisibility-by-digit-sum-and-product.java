class Solution {
    public boolean checkDivisibility(int n) {
        int num = n;
        int sum = 0;
        int prod = 1;

        while(n > 0){
            int x = n % 10;
            sum += x ;
            prod *= x;

            n /= 10;
        }

        return num % (sum + prod) == 0;
    }
}