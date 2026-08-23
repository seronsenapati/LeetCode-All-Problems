class Solution {
    public static void findCombinations(int index , int arr[] , int target , List<List<Integer>>ans , List<Integer>current){
        if(target == 0){
            ans.add(new ArrayList<>(current));
            return;
        }

        for(int i = index ; i < arr.length ; i++){
            if(i > index && arr[i] == arr[i - 1]){
                continue;
            }

            if(arr[i] > target){
                break;
            }

            current.add(arr[i]);

            findCombinations(i + 1 , arr , target - arr[i] , ans , current);

            current.remove(current.size() - 1);
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        findCombinations(0 , candidates , target , ans , new ArrayList<>());
        return ans; 
    }
}