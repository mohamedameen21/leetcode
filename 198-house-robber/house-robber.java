class Solution {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);

        return getMaxSubsequence(nums.length - 1, nums, dp);
    }

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
}