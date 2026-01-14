class Solution {
    public int climbStairs(int n) {
        // int[] dp = new int[n+1];
        // Arrays.fill(dp,-1);

        // return climbStairs(n, dp);

        int prev2 = 1;
        int prev = 1;

        for(int i = 2; i <= n; i++) {
            int cur = prev + prev2;
            prev2 = prev;
            prev = cur;
        }

        return prev;
    }

    private int climbStairs(int n, int[] dp) {
        if(n <= 1) return 1;

        if(dp[n] != -1) return dp[n];

        dp[n-1] = climbStairs(n-1, dp);
        dp[n-2] = climbStairs(n-2, dp);

        return dp[n-1] + dp[n-2];
    }
}