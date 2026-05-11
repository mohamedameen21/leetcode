class Solution {
    public boolean isMatch(String s, String p) {
        Boolean[][] dp = new Boolean[p.length()][s.length()];

        return isMatch(p, p.length() - 1, s, s.length() - 1, dp);
    }

    // s1 = pattern (p)
    // s2 = string (s)
    private static boolean isMatch(String s1, int i, String s2, int j, Boolean[][] dp) {
        if (i < 0)
            return j < 0;

        if (j < 0) {
            while (i >= 0) {
                if (s1.charAt(i) != '*') return false;
                i--;
            }

            return true;
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j) || s1.charAt(i) == '?') {
            dp[i][j] = isMatch(s1, i - 1, s2, j - 1, dp);
            return dp[i][j];
        } else if (s1.charAt(i) == '*') {
            dp[i][j] = isMatch(s1, i - 1, s2, j, dp) || isMatch(s1, i, s2, j - 1, dp);
            return dp[i][j];
        } else {
            dp[i][j] = false;
            return false;
        }
    }
}