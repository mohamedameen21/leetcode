class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for(int[] d : dp) {
            Arrays.fill(d, -1);
        }

        // return minDistance(word1, word1.length()-1, word2, word2.length()-1, dp);

        return minDistanceTab(word1, word2);
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

    private static int minDistanceTab(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];

        for(int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        for(int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }

        for(int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
                
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                    continue;
                }

                int insert = dp[i][j-1];
                int delete = dp[i-1][j];
                int replace = dp[i-1][j-1];

                dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace)); 
            }
        }

        return dp[s1.length()][s2.length()];
    }
}