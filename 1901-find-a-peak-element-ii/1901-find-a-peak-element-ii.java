class Solution {
    public int maxElement(int matrix[][] , int col){
        int maxRow = 0;
        for(int i = 0 ; i < matrix.length ; i++){
            if(matrix[i][col] > matrix[maxRow][col]){
                maxRow = i;
            }
        } 

        return maxRow;
    }
    public int[] findPeakGrid(int[][] mat) {
        int low = 0 , high = mat[0].length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;

            int row = maxElement(mat , mid);

            int left = (mid > 0) ? mat[row][mid - 1] : Integer.MIN_VALUE;
            int right = (mid < mat[0].length - 1) ? mat[row][mid + 1] : Integer.MIN_VALUE;

            if(mat[row][mid] > left && mat[row][mid] > right){
                return new int[]{row , mid};
            }

            if(mat[row][mid] < left ){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return new int[] {-1 , -1};
    }
}