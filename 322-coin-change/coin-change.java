class Solution {
    public int coinChange(int[] coins, int amount) {
        long[][] dp = new long[coins.length][amount+1];
        for(long[] d : dp) {
            Arrays.fill(d , -1);
        }

        int ans = (int) getMin(coins, coins.length-1, amount, dp);

        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

// Basic Recursion
    // private static long getMin(int[] coins, int index, int amount) {
    //     if(amount == 0) {
    //         return 0;
    //     }

    //     if(index < 0 || amount < 0) {
    //         return Integer.MAX_VALUE;
    //     }

    //     long take = 1 + getMin(coins, index, amount - coins[index]);
    //     long notTake = getMin(coins, index-1, amount);

    //     return Math.min(take, notTake);
    // }

    private static long getMin(int[] coins, int index, int amount, long[][] dp) {
        if(amount == 0) {
            return 0;
        }

        if(index < 0 || amount < 0) {
            return Integer.MAX_VALUE;
        }

        if(dp[index][amount] != -1) {
            return dp[index][amount];
        }

        long take = 1 + getMin(coins, index, amount - coins[index], dp);
        long notTake = getMin(coins, index-1, amount, dp);

        dp[index][amount] = Math.min(take, notTake);

        return dp[index][amount];
    }
}