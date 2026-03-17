class Solution {
    public int coinChange(int[] coins, int amount) {
        long[][] dp = new long[coins.length][amount + 1];
        for (long[] d : dp) {
            Arrays.fill(d, -1);
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

    // Memoization
    private static long getMin(int[] coins, int index, int amount, long[][] dp) {
        if (amount == 0) {
            return 0;
        }

        if (index < 0 || amount < 0) {
            return Integer.MAX_VALUE;
        }

        if (dp[index][amount] != -1) {
            return dp[index][amount];
        }

        long take = 1 + getMin(coins, index, amount - coins[index], dp);
        long notTake = getMin(coins, index - 1, amount, dp);

        dp[index][amount] = Math.min(take, notTake);

        return dp[index][amount];
    }


// Tabulation
    // private static long getMin(int[] coins, int amount) {
    //     long[][] dp = new long[coins.length][amount + 1];

    //     dp[0][0] = 0;
    //     for (int a = 1; a <= amount; a++) {
    //         dp[0][a] = (coins[0] > a || (a % coins[0] != 0)) ? Integer.MAX_VALUE : a / coins[0];
    //     }

    //     for (int index = 1; index < coins.length; index++) {
    //         for (int a = 0; a <= amount; a++) {
    //             long take = 1 + ((coins[index] <= a) ? dp[index][a - coins[index]] : Integer.MAX_VALUE);
    //             long notTake = dp[index-1][a];

    //             dp[index][a] = Math.min(take, notTake);
    //         }
    //     }

    //     return dp[coins.length-1][amount];
    // }



// Space Optimization
    // private static long getMin(int[] coins, int amount) {
    //     long[] dp = new long[amount + 1];

    //     dp[0] = 0;
    //     for (int a = 1; a <= amount; a++) {
    //         dp[a] = (coins[0] > a || (a % coins[0] != 0)) ? Integer.MAX_VALUE : a / coins[0];
    //     }

    //     for (int index = 1; index < coins.length; index++) {
    //         for (int a = coins[index]; a <= amount; a++) {
    //             long take = 1 + dp[a - coins[index]];
    //             long notTake = dp[a];

    //             dp[a] = Math.min(take, notTake);
    //         }
    //     }

    //     return dp[amount];
    // }
}