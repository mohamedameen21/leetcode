class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        // return getMinPath(grid, m-1, n-1, dp);

        return getMinPath(grid);
    }

    // Memoization TC: O(M * N) SC: O(N+M) + O(M*N)
    private static int getMinPath(int[][] grid, int i, int j, int[][] dp) {
        if (i == 0 && j == 0) {
            return grid[i][j];
        }

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length) {
            return Integer.MAX_VALUE;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int up = getMinPath(grid, i - 1, j, dp);
        int left = getMinPath(grid, i, j - 1, dp);

        dp[i][j] = Math.min(up, left) + grid[i][j];

        return dp[i][j];
    }

    // Tabulation. Tc: O(M*N) SC: O(N+M) + O(M*N)
    // private static int getMinPath(int[][] grid) {
    //     int[][] dp = new int[grid.length][grid[0].length];
    //     dp[0][0] = grid[0][0];

    //     for (int i = 0; i < grid.length; i++) {
    //         for (int j = 0; j < grid[i].length; j++) {
    //             if (i == 0 && j == 0) {
    //                 continue;
    //             }

    //             int up = (i - 1 >= 0) ? dp[i - 1][j] : Integer.MAX_VALUE;
    //             int left = (j - 1 >= 0) ? dp[i][j - 1] : Integer.MAX_VALUE;

    //             dp[i][j] = Math.min(up, left) + grid[i][j];
    //         }
    //     }

    //     return dp[grid.length - 1][grid[0].length - 1];
    // }

// Tabulation - Space Optmization. TC: O(M*N) SC: O(2N)
    private static int getMinPath(int[][] grid) {
        int[] prevRow = new int[grid[0].length];
        Arrays.fill(prevRow, Integer.MAX_VALUE);

        for(int i = 0; i < grid.length; i++) {
            int[] curRow = new int[grid[0].length];

            for(int j = 0; j < grid[0].length; j++) {
                if(i == 0 && j == 0) {
                    curRow[0] = grid[0][0];
                    continue;
                }

                int up = prevRow[j];
                int left = (j - 1 >= 0) ? curRow[j-1] : Integer.MAX_VALUE;

                curRow[j] = Math.min(up, left) + grid[i][j];
            }

            prevRow = curRow;
        }

        return prevRow[grid[0].length - 1];
    }
}