class Solution {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        for(int[] d : dp) {
            Arrays.fill(d, -1);
        }

        return count(coins, coins.length-1, amount, dp);
    }

    private static int count(int[] coins, int index, int target, int[][] dp) {
        if(target == 0) {
            return 1;
        }

        if(index < 0 || target < 0) {
            return 0;
        }

        if(dp[index][target] != -1) {
            return dp[index][target];
        }

        int take = count(coins, index, target - coins[index], dp);
        int notTake = count(coins, index -1, target, dp);

        dp[index][target] = take + notTake;

        return dp[index][target];
    }
}