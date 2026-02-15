class Solution {
    // public int uniquePaths(int m, int n) {
    //     int[][] dp = new int[m][n];

    //     for (int[] d : dp) {
    //         Arrays.fill(d, -1);
    //     }

    //     // return getUniquePath(m, n, m - 1, n - 1, dp);

    //     return getUniquePath(m, n);
    // }

    public int uniquePaths(int m, int n) {
          // We need to make (m-1) down moves and (n-1) right moves
          // Total moves = (m-1) + (n-1) = m + n - 2
          // We choose which (m-1) positions are down moves (or which (n-1) are right)
          // Answer = C(m+n-2, m-1) = (m+n-2)! / ((m-1)! * (n-1)!)

          int totalMoves = m + n - 2;
          int k = Math.min(m - 1, n - 1); // optimize: choose smaller k

          // Calculate C(totalMoves, k) efficiently
          // C(n, k) = (n * (n-1) * ... * (n-k+1)) / (k * (k-1) * ... * 1)
          long result = 1;

          for (int i = 1; i <= k; i++) {
              result = result * (totalMoves - k + i) / i;
          }

          return (int) result;
      }

    // Memoization
    private static int getUniquePath(int m, int n, int i, int j, int[][] dp) {
        if (i == 0 && j == 0) {
            return 1;
        }

        if (i >= m || j >= n || i < 0 || j < 0) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int up = getUniquePath(m, n, i - 1, j, dp);
        int left = getUniquePath(m, n, i, j - 1, dp);

        dp[i][j] = up + left;

        return dp[i][j];
    }

// Tabulation
    // private static int getUniquePath(int m, int n) {
    //     int[][] dp = new int[m][n];
    //     dp[0][0] = 1;

    //     for (int i = 0; i < m; i++) {
    //         for (int j = 0; j < n; j++) {
    //             if (i == 0 && j == 0) {
    //                 continue;
    //             } 

    //             int up = (i - 1 >= 0) ? dp[i-1][j] : 0;
    //             int left = (j - 1 >=0) ? dp[i][j-1] : 0;

    //             dp[i][j] = up + left;
    //         }
    //     }

    //     return dp[m-1][n-1];
    // }

// Tabulation with Space Optimization 
    private static int getUniquePath(int m, int n) {
        int[] prevRow = new int[n];

        for (int i = 0; i < m; i++) {
            int[] curRow = new int[n];

            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    curRow[j] = 1;
                    continue;
                } 

                int up = prevRow[j];
                int left = (j - 1 >=0) ? curRow[j-1] : 0;

                curRow[j] = up + left;
            }
            prevRow = curRow;
        }

        return prevRow[n-1];
    }
}