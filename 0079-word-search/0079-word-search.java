class Solution {
    public boolean search(int row , int col , int index , char[][]board , String word){
        if(index == word.length()){
            return true;
        }
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length){
            return false;
        }
        if(board[row][col] != word.charAt(index)){
            return false;
        }

        char temp = board[row][col];
        board[row][col] = '#';

        boolean found = search(row + 1 , col , index + 1 , board , word) || 
                        search(row - 1 , col , index + 1 , board , word) || 
                        search(row , col + 1 , index + 1 , board , word) ||
                        search(row , col - 1 , index + 1 , board , word);
        
        board[row][col] = temp;
        return found;
    }

    public boolean exist(char[][] board, String word) {
        for(int row = 0 ; row < board.length ; row++){
            for(int col = 0 ; col < board[0].length ; col++){
                if(board[row][col] == word.charAt(0)){
                    if(search(row , col , 0 , board , word)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}