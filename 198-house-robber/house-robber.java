class Solution {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);

        // return getMaxSubsequence(nums.length - 1, nums, dp);
        return getMaxSubsequence(nums);
    }

    // Memoization TC: O(N), SC: O(N) + O(N)
    private static int getMaxSubsequence(int i, int[] nums, int[] dp) {
        if (i == 0) {
            return nums[i];
        }

        if (i < 0) {
            return 0;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        int pick = nums[i] + getMaxSubsequence(i - 2, nums, dp);
        int notPick = 0 + getMaxSubsequence(i - 1, nums, dp);

        dp[i] = Math.max(pick, notPick);

        return dp[i];
    }

    private static int getMaxSubsequence(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int pick = (i - 2 >= 0) ? dp[i - 2] + nums[i] : nums[i];
            int notPick = dp[i - 1] + 0;

            dp[i] = Math.max(pick, notPick);
        }

        return dp[nums.length - 1];
    }
}