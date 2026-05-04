class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for(int[] d : dp) {
            Arrays.fill(d, -1);
        }

        return minDistance(word1, word1.length()-1, word2, word2.length()-1, dp);
    }

    private static int minDistance(String s1, int i, String s2, int j, int[][] dp) {
        if(i < 0) return j+1;
        if(j < 0) return i+1;

        if(dp[i][j] != -1) {
            return dp[i][j];
        } 

        if(s1.charAt(i) == s2.charAt(j)) {
            dp[i][j] = minDistance(s1, i-1, s2, j-1, dp);
            return dp[i][j];
        }

        int insert = minDistance(s1, i, s2, j-1, dp);
        int delete = minDistance(s1, i-1, s2, j, dp);
        int replace = minDistance(s1, i-1, s2, j-1, dp);

        dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace)); 

        return dp[i][j];
    }
}