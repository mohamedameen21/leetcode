class Solution {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];
        
        for(int[] d : dp) {
            Arrays.fill(d, -1);
        }

        // return countSubsequence(s, s.length()-1, t, t.length()-1, dp);

        return countSubsequence(s, t);
    }

    private static int countSubsequence(String s1, int i, String s2, int j, int[][] dp) {
        if(j < 0) return 1;
        if(i < 0) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        if(s1.charAt(i) == s2.charAt(j)) {
            dp[i][j] = countSubsequence(s1, i-1, s2, j-1, dp) + countSubsequence(s1, i-1, s2, j, dp);
            return dp[i][j];
        } else {
            dp[i][j] = countSubsequence(s1, i-1, s2, j, dp);
            return dp[i][j];
        }
    }

    private static int countSubsequence(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];

        for(int i = 0; i <= s1.length(); i++) {
            dp[i][0] = 1;
        }

        for(int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}