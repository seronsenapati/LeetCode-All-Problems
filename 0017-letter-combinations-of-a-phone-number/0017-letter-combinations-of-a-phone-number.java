class Solution {
    public static void generate(int index , String digits , String current , List<String>ans){
        if(index == digits.length()){
            ans.add(current);
            return;
        }

        String letter = getLetters(digits.charAt(index));
        for(int i = 0 ; i < letter.length() ; i++){
            generate(index + 1 , digits , current + letter.charAt(i) , ans);
        }
    }
    private static String getLetters(char digit){
        if(digit == '2') return "abc";
        if(digit == '3') return "def";
        if(digit == '4') return "ghi";
        if(digit == '5') return "jkl";
        if(digit == '6') return "mno";
        if(digit == '7') return "pqrs";
        if(digit == '8') return "tuv";
        return "wxyz";
    }
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if(digits.length() == 0){
            return ans;
        }
        generate(0 , digits , "" , ans);
        return ans;
    }
}