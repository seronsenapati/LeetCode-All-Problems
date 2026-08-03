class Solution {
    public String largestOddNumber(String num) {
        int end = -1;
        for(int i = num.length() - 1 ; i >= 0 ; i--){
            if((num.charAt(i) - '0') % 2 != 0){
                end = i;
                break;
            }
        }

        if(end == -1){
            return "";
        }
        
        int start = 0;
        while(start <= end && num.charAt(start) == '0'){
            start++;
        }

        return num.substring(start , end + 1);
    }
}