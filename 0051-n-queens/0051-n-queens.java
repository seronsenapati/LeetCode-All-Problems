class Solution {
    public static void solve(int col , char[][] board , List<List<String>>ans , int leftRow[] , int lowerDaigonal[] , int upperDaigonal[] , int n){
        if(col == n){
            List<String> current = new ArrayList<>();
            for(int i = 0 ; i < n ; i++){
                current.add(new String(board[i]));
            }
            ans.add(current);
            return;
        }

        for(int row = 0 ; row < n ; row++){
            if(leftRow[row] == 0 && lowerDaigonal[row + col] == 0 && upperDaigonal[n - 1 + col - row] == 0){
                board[row][col] = 'Q';
                leftRow[row] = 1;
                lowerDaigonal[row + col] = 1;
                upperDaigonal[n - 1 + col - row] = 1;

                solve(col + 1 , board , ans , leftRow , lowerDaigonal , upperDaigonal , n);

                board[row][col] = '.';
                leftRow[row] = 0;
                lowerDaigonal[row + col] = 0;
                upperDaigonal[n - 1 + col - row] = 0;
            }
        }
    }
    public List<List<String>> solveNQueens(int n) {
        List<List<String>>ans = new ArrayList<>();
        char[][] board = new char[n][n];
        for(int i = 0 ; i < n ; i++){
            Arrays.fill(board[i] , '.');
        }
        int leftRow[] = new int[n];
        int lowerDaigonal[] = new int[2*n - 1];
        int upperDaigonal[] = new int[2*n - 1];
        solve(0 , board, ans , leftRow , lowerDaigonal , upperDaigonal , n);
        return ans;
    }
}