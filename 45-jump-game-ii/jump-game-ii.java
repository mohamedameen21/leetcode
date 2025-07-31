class Solution {
    public int jump(int[] nums) {
        int maxReach = 0;
        int noOfJumps = 0;
        int currentJumpLength = 0;

        for (int i = 0; i < nums.length; i++) {
            if(currentJumpLength >= nums.length - 1) {
                return noOfJumps;
            }

            maxReach = Math.max(maxReach, i + nums[i]);

            if (i == currentJumpLength) {
                noOfJumps++;
                currentJumpLength = maxReach;
            }
        }

        return noOfJumps;
    }
}