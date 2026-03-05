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

        // return isTargetExists(nums, nums.length - 1, target, dp);

        return isTargetExists(nums, target);
    }

// Memoization. TC: O(N*K) SC: O(N*K) + O(N)
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

// Tabulation. TC:O(N*K) SC:(N*K)
    private static boolean isTargetExists(int[] arr, int target) {
        boolean[][] dp = new boolean[arr.length][target+1];

        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = true; 
        }
        if(arr[0] <= target) dp[0][arr[0]] = true;

        for(int i = 1; i < arr.length; i++) {
            for(int j = 0; j <= target; j++) {
                boolean notTake = dp[i-1][j];
                boolean take = false;

                if(j >= arr[i]) {
                    take = dp[i-1][j-arr[i]];
                }

                dp[i][j] = notTake || take;
            }
        }

        return dp[arr.length-1][target];
    }
}