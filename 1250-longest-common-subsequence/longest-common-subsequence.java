class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        return getMax(text1, text1.length() - 1, text2, text2.length() - 1, dp);
    }

    private static int getMax(String s1, int index1, String s2, int index2, int[][] dp) {
        if (index1 < 0 || index2 < 0) {
            return 0;
        }

        if (dp[index1][index2] != -1) {
            return dp[index1][index2];
        }

        if (s1.charAt(index1) == s2.charAt(index2)) {
            dp[index1][index2] = 1 + getMax(s1, index1 - 1, s2, index2 - 1, dp);

            return dp[index1][index2];
        }

        dp[index1][index2] = Math.max(getMax(s1, index1 - 1, s2, index2, dp), getMax(s1, index1, s2, index2 - 1, dp));

        return dp[index1][index2];
    }
}