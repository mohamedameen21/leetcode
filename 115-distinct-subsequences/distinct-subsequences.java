class Solution {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];
        
        for(int[] d : dp) {
            Arrays.fill(d, -1);
        }

        return countSubsequence(s, s.length()-1, t, t.length()-1, dp);
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
}