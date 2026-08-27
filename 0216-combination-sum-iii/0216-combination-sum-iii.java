class Solution {
    public static void findCombinations(int start , int k , int n , List<Integer>current,  List<List<Integer>>ans){
        if(current.size() == k){
            if(n == 0){
                ans.add(new ArrayList<>(current));
            }
            return;
        }

        for(int i = start ; i <= 9 ; i++){
            if(i > n){
                break;
            }
            current.add(i);
            findCombinations(i + 1 , k , n - i , current , ans);
            current.remove(current.size() - 1);
        }
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        findCombinations(1 , k , n , new ArrayList<>() , ans);
        return ans;
    }
}