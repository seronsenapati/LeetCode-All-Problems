class Solution {
    public static void findCombination(int index , int arr[] , int target , List<List<Integer>>ans , List<Integer>current){
        if(index == arr.length){
            if(target == 0){
                ans.add(new ArrayList<>(current));
            }
            return;
        }

        if(arr[index] <= target){
            current.add(arr[index]);
            findCombination(index , arr , target - arr[index] , ans , current);
            current.remove(current.size() - 1);
        }
        findCombination(index + 1 , arr , target , ans , current);
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        findCombination(0  , candidates , target , ans , new ArrayList<>());

        return ans; 
    }
}