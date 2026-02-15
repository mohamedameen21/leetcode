class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for(int[] d : dp) {
            Arrays.fill(d, -1);
        }
        
        return getUniquePath(m, n, m-1, n-1, dp);
    }

    private static int getUniquePath(int m, int n, int i, int j, int[][] dp) {
        if(i == 0 && j == 0) {
            return 1;
        }

        if(i >= m || j >= n || i < 0 || j < 0) {
            return 0;
        }

        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        int up = getUniquePath(m, n, i -1, j, dp);
        int left = getUniquePath(m, n, i, j-1, dp);

        dp[i][j] = up + left;

        return dp[i][j];
    }
}