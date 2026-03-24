class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        // return getMax(text1, text1.length() - 1, text2, text2.length() - 1, dp);
        return getMax(text1, text2);
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

    // private static int getMax(String s1, String s2) {
    //     int[][] dp = new int[s1.length()][s2.length()];

    //     for (int i = 0; i < s2.length(); i++) {
    //         if (s1.charAt(0) == s2.charAt(i)) {
    //             dp[0][i] = 1;
    //         } else {
    //             dp[0][i] = (i > 0) ? dp[0][i - 1] : 0;
    //         }
    //     }

    //     for (int i = 0; i < s1.length(); i++) {
    //         if (s2.charAt(0) == s1.charAt(i)) {
    //             dp[i][0] = 1;
    //         } else {
    //             dp[i][0] = (i > 0) ? dp[i - 1][0] : 0;
    //         }
    //     }

    //     for (int i = 1; i < s1.length(); i++) {
    //         for (int j = 1; j < s2.length(); j++) {
    //             if (s1.charAt(i) == s2.charAt(j)) {
    //                 dp[i][j] = 1 + dp[i - 1][j - 1];
    //             } else {
    //                 dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
    //             }
    //         }
    //     }

    //     return dp[s1.length() - 1][s2.length() - 1];
    // }

    private static int getMax(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }

        return dp[s1.length()][s2.length()];
    }
}