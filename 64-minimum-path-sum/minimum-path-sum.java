class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        return getMinPath(grid, m-1, n-1, dp);
    }

    private static int getMinPath(int[][] grid, int i, int j, int[][] dp) {
        if(i == 0 && j == 0) {
            return grid[i][j];
        }

        if(i < 0 || i >= grid.length || j < 0 || j >= grid[i].length) {
            return Integer.MAX_VALUE;
        }

        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        int up = getMinPath(grid, i -1, j, dp);
        int left = getMinPath(grid, i, j-1, dp);

        dp[i][j] = Math.min(up, left) + grid[i][j];

        return dp[i][j];
    }
}