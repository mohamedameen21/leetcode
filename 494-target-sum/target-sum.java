class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        // s1 - s2 = target --> 1
        // s1 + s2 = total  --> 2

        // s1 = total - s2 Substitute this s1 formula in 1

        // (total -s2) -s2 = target
        // -2s2 + total = target
        // -2s2 = target - total
        // 2s2 = -(target - total)
        // 2s2 = total - target
        // s2 = (total - target) / 2

        // (total - target) / 2 is odd -> then we can't able to find the subset (so the count is 0)
        // when it is even Then we need to find the subset with target (total - target) / 2

        // int sum = 0;
        // for (int n : nums) {
        //     sum += n;
        // }

        // if ((sum - target) % 2 != 0)
        //     return 0;

        // target = (sum - target) / 2;

        // if(target < 0) return 0;

        // int[][] dp = new int[nums.length][target + 1];
        // for (int[] d : dp) {
        //     Arrays.fill(d, -1);
        // }

        // int totalSubsetThatGivesDifferenceEqualToTarget = count(nums, nums.length - 1, target, dp);


        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        int[][] dp = new int[nums.length][2*sum + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int totalSubsetThatGivesDifferenceEqualToTarget = count(nums, nums.length - 1, target, dp, sum);

        return totalSubsetThatGivesDifferenceEqualToTarget;
    }

    private static int count(int[] nums, int index, int target, int[][] dp) {
        if(target < 0) return 0;

        if (index < 0) {
            return target == 0 ? 1 : 0;
        }

        if (dp[index][target] != -1) {
            return dp[index][target];
        }

        int take = count(nums, index - 1, target - nums[index], dp);
        int notTake = count(nums, index - 1, target, dp);

        dp[index][target] = take + notTake;

        return dp[index][target];
    }

    private static int count(int[] nums, int index, int target, int[][] dp, int offset) {
        if (target > offset || target < -offset) return 0;

        if(index < 0) {
            return target == 0 ? 1 : 0;
        }

        if(dp[index][target + offset] != -1) {
            return dp[index][target + offset];
        }

        int add = count(nums, index - 1, target + nums[index], dp, offset);
        int subract = count(nums, index-1, target - nums[index], dp, offset);

        dp[index][target + offset] = add + subract;

        return dp[index][target + offset];
    }
}