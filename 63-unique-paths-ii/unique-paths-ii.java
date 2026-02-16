class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1) { // if thereis obstacle in start cell, then we can't able to move anywhere
            return 0;
        }

        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        // return getUniquePath(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length -1, dp);

        return getUniquePath(obstacleGrid);
    }

// Memoization. TC: O(MxN) SC: O(Path [m+n]) + O(m*n)
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

// Tabulation TC: O(N*M) SC: O(M*N)
    // private static int getUniquePath(int[][] grid) {
    //     int[][] dp = new int[grid.length][grid[0].length];
    //     dp[0][0] = 1;

    //     for (int i = 0; i < grid.length; i++) {
    //         for (int j = 0; j < grid[i].length; j++) {
    //             if (i == 0 && j == 0) {
    //                 continue;
    //             } 

    //             if(grid[i][j] != 0) {
    //                 continue;
    //             }

    //             int up = (i - 1 >= 0) ? dp[i-1][j] : 0;
    //             int left = (j - 1 >=0) ? dp[i][j-1] : 0;

    //             dp[i][j] = up + left;
    //         }
    //     }

    //     return dp[grid.length-1][grid[0].length-1];
    // }

    // Tabulation with Space Optimization  TC: O(N*M) SC: O(2N)
    private static int getUniquePath(int[][] grid) {
        int[] prevRow = new int[grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            int[] curRow = new int[grid[0].length];

            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 && j == 0) {
                    curRow[j] = 1;
                    continue;
                } 

                if(grid[i][j] != 0) {
                    continue;
                }

                int up = prevRow[j];
                int left = (j - 1 >=0) ? curRow[j-1] : 0;

                curRow[j] = up + left;
            }
            prevRow = curRow;
        }

        return prevRow[grid[0].length-1];
    }

}