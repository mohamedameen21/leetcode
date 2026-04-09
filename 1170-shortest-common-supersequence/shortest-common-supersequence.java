class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        // return scsTabulation(str1, str2);

        return scsMemoization(str1, str2);
    }

    // ================= TABULATION =================
    public String scsTabulation(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n + 1][m + 1];

        // build LCS table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // build SCS from dp
        int i = n, j = m;
        StringBuilder sb = new StringBuilder();

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                sb.append(str1.charAt(i - 1));
                i--;
            } else {
                sb.append(str2.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            sb.append(str1.charAt(i - 1));
            i--;
        }

        while (j > 0) {
            sb.append(str2.charAt(j - 1));
            j--;
        }

        return sb.reverse().toString();
    }

    // ================= MEMOIZATION =================
    public String scsMemoization(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n][m];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // build full dp table using memo
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                lcsMemo(str1, i, str2, j, dp);
            }
        }

        // backtrack (adapted for 0-based dp)
        int i = n - 1, j = m - 1;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 && j >= 0) {
            if (str1.charAt(i) == str2.charAt(j)) {
                sb.append(str1.charAt(i));
                i--;
                j--;
            } else {
                int up = (i > 0) ? dp[i - 1][j] : 0;
                int left = (j > 0) ? dp[i][j - 1] : 0;

                if (up > left) {
                    sb.append(str1.charAt(i));
                    i--;
                } else {
                    sb.append(str2.charAt(j));
                    j--;
                }
            }
        }

        while (i >= 0) {
            sb.append(str1.charAt(i));
            i--;
        }

        while (j >= 0) {
            sb.append(str2.charAt(j));
            j--;
        }

        return sb.reverse().toString();
    }

    private int lcsMemo(String s1, int i, String s2, int j, int[][] dp) {
        if (i < 0 || j < 0)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            dp[i][j] = 1 + lcsMemo(s1, i - 1, s2, j - 1, dp);
        } else {
            dp[i][j] = Math.max(
                    lcsMemo(s1, i - 1, s2, j, dp),
                    lcsMemo(s1, i, s2, j - 1, dp));
        }

        return dp[i][j];
    }
}
