class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0; 
        for(int n : nums) {
            sum += n;
        }
        if(sum % 2 != 0) return false;

        int target = sum / 2;


        int[][] dp = new int[nums.length][target + 1];

        for(int[] d : dp) {
            Arrays.fill(d, -1);
        }

        return isTargetExists(nums, nums.length - 1, target, dp);
    }

    private static boolean isTargetExists(int[] arr, int index, int target, int[][] dp) {
        if(target == 0) {
            return true;
        } 

        if(index < 0) {
            return false;
        }

        if(dp[index][target] != -1) {
            return dp[index][target] == 1;
        }

        boolean notTake = isTargetExists(arr, index-1, target, dp);
        boolean take = false;

        if(!notTake && target >= arr[index]) {
            take = isTargetExists(arr, index-1, target - arr[index], dp);
        }

        dp[index][target] = (notTake || take) ? 1 : 0;

        return notTake || take;
    }
}