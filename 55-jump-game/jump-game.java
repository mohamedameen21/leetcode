class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;

        for (int i = 0; i < nums.length; i++) {
            maxReach = Math.max(maxReach, i + nums[i]);

            if(maxReach >= nums.length-1) {
                return true;
            }

            if(maxReach == i && nums[i] == 0) return false;
        }

        return false;
    }
}