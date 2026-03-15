class Solution {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        // return count(coins, coins.length-1, amount, dp);
        // return count(coins, amount);
        return countO(coins, amount);
    }

    // Memoization
    private static int count(int[] coins, int index, int target, int[][] dp) {
        if (target == 0) {
            return 1;
        }

        if (index < 0 || target < 0) {
            return 0;
        }

        if (dp[index][target] != -1) {
            return dp[index][target];
        }

        int take = count(coins, index, target - coins[index], dp);
        int notTake = count(coins, index - 1, target, dp);

        dp[index][target] = take + notTake;

        return dp[index][target];
    }

    // Tabulation
    private static int count(int[] coins, int target) {
        int n = coins.length;
        int[][] dp = new int[n][target + 1];

        // base case: amount 0 can always be made with empty combination
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        // first row: using only coins[0]
        for (int t = 1; t <= target; t++) {
            if (t % coins[0] == 0) {
                dp[0][t] = 1;
            }
        }

        for (int index = 1; index < coins.length; index++) {
            for (int t = 0; t <= target; t++) {
                int take = (coins[index] <= t) ? dp[index][t - coins[index]] : 0;
                int notTake = dp[index - 1][t];

                dp[index][t] = take + notTake;
            }
        }

        return dp[coins.length - 1][target];
    }

    // Space Optimiation
    private static int countO(int[] coins, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int t = 1; t <= target; t++) {
            if (t % coins[0] == 0) {
                dp[t] = 1;
            }
        }

        for (int index = 1; index < coins.length; index++) {
            for (int t = 0; t <= target; t++) {
                // int take = dp[t - coins[index]];
                int take = (coins[index] <= t) ? dp[t - coins[index]] : 0;

                int notTake = dp[t];

                dp[t] = take + notTake;
            }
        }

        return dp[target];
    }
}