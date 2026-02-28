class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix.length];

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        // int minSum = Integer.MAX_VALUE;

        // for (int i = 0; i < matrix[0].length; i++) {
        //     minSum = Math.min(minSum, getMinPathSum(matrix, 0, i, dp));
        // }

        // return minSum;

        return getMinPathSum(matrix);
    }

    private static int getMinPathSum(int[][] matrix, int i, int j, int[][] dp) {
        if (j < 0 || j >= matrix[i].length) {
            return Integer.MAX_VALUE;
        }

        if (i == matrix.length - 1) {
            return matrix[i][j];
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int down = getMinPathSum(matrix, i + 1, j, dp);
        int diagonalLeft = getMinPathSum(matrix, i + 1, j - 1, dp);
        int diagonalRight = getMinPathSum(matrix, i + 1, j + 1, dp);

        dp[i][j] = Math.min(down, Math.min(diagonalLeft, diagonalRight)) + matrix[i][j];

        return dp[i][j];
    }

    private static int getMinPathSum(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix.length];

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        for (int i = 0; i < matrix[matrix.length - 1].length; i++) {
            dp[matrix.length - 1][i] = matrix[matrix.length - 1][i];
        }

        //  To decide the start cell in the last row
        for (int i = matrix[matrix.length - 1].length - 2; i >= 0; i--) {
            for (int j = 0; j < matrix[i].length; j++) {
                int down = dp[i+1][j];
                int diagonalLeft = (j > 0) ? dp[i+1][j-1] : Integer.MAX_VALUE;
                int diagonalRight = (j < matrix[i+1].length - 1) ? dp[i+1][j+1] : Integer.MAX_VALUE;

                dp[i][j] = Math.min(down, Math.min(diagonalLeft, diagonalRight)) + matrix[i][j];
            }   
        }

        int min = Integer.MAX_VALUE;

        for(int d : dp[0]) {
            min =  Math.min(d, min);
        }

        return min;
    }
}