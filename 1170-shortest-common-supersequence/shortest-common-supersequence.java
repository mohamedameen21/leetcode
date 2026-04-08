class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int[][] dp = getLCS(str1, str2);

        int i = str1.length();
        int j = str2.length();
        StringBuilder sb = new StringBuilder();

        while(i > 0 && j > 0) {
            if(str1.charAt(i-1) == str2.charAt(j-1)) {
                sb.append(str1.charAt(i-1));
                i--;
                j--;
            } else {
                if(dp[i-1][j] > dp[i][j-1]) {
                    sb.append(str1.charAt(i-1));
                    i--;
                } else {
                    sb.append(str2.charAt(j-1));
                    j--;
                }
            }
        }

        while(i > 0) {
            sb.append(str1.charAt(i-1));
            i--;
        }

        while(j > 0) {
            sb.append(str2.charAt(j-1));
            j--;
        }


        return sb.reverse().toString();
    }

    private static int[][] getLCS(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];

        for(int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp;
    }
}