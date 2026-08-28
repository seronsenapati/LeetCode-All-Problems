class Solution {
    public void solve(String num , int target , int index , long value , long previous , String expression , List<String> ans){
        if(index == num.length()){
            if(value == target){
                ans.add(expression);
            }
            return;
        }

        for(int i = index ; i < num.length() ; i++){
            if(i > index && num.charAt(index) == '0'){
                break;
            }

            String currentString = num.substring(index , i + 1);
            long current = Long.parseLong(currentString);
            if(index == 0){
                solve(num , target , i + 1 , current , current , currentString , ans);
            }else{
                solve(num , target , i + 1 , value + current , current , expression+"+"+currentString , ans);

                solve(num , target , i + 1 , value - current , -current , expression+"-"+currentString , ans);

                solve(num , target , i + 1 , value - previous + previous * current , previous * current , expression+"*"+currentString , ans);
            }
        }
    }
    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        solve(num , target , 0 , 0 , 0 , "" , ans);
        return ans;
    }
}