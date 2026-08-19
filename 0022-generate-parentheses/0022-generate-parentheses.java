class Solution {
    public static void generate(int n , int open , int close , String current , List<String>ans){
        if(current.length() == 2 * n){
            ans.add(current);
            return;
        }

        if(open < n){
            generate(n , open + 1 , close , current + "(" , ans);
        }

        if(close < open){
            generate(n , open , close + 1 , current + ")" , ans);
        }
    }
    public List<String> generateParenthesis(int n) {
        ArrayList<String>ans = new ArrayList<>();
        generate(n , 0 , 0 , "" , ans);
        return ans;
    }
}