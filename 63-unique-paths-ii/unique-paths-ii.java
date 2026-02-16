class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1) {
            return 0;
        }

        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        return getUniquePath(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length -1, dp);
    }

    private static int getUniquePath(int[][] grid, int i, int j, int[][] dp) {
        if (i == 0 && j == 0) {
            return 1;
        }

        if (i >= grid.length || i < 0 || j >= grid[i].length || j < 0) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if(grid[i][j] != 0) { // there is obstacle
            return 0;
        }

        int up = getUniquePath(grid, i - 1, j, dp);
        int left = getUniquePath(grid, i, j - 1, dp);

        dp[i][j] = up + left;

        return dp[i][j];
    }
}