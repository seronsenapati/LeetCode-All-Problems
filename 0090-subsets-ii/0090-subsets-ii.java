class Solution {
    public static void findSubsets(int index , int nums[] , List<Integer>current , List<List<Integer>>ans){
        ans.add(new ArrayList<>(current));
        for(int i = index ; i < nums.length; i++){
            if(i > index && nums[i] == nums[i - 1]){
                continue;
            }

            current.add(nums[i]);
            findSubsets(i+1 , nums , current , ans);
            current.remove(current.size() - 1);
        }
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        findSubsets(0 , nums , new ArrayList<>() , ans);
        return ans;
    }
}