class Solution {
    public List<List<Integer>> permute(int[] nums) {
        return getPermutation(nums, 0);
    }

    private static List<List<Integer>> getPermutation(int[] nums, int position) {
        List<List<Integer>> list = new ArrayList<>();

        if(position >= nums.length) {
            List<Integer> l = new ArrayList<>();
            for(int num : nums) { l.add(num); }
            list.add(l);

            return list;
        }

        for(int i=position; i < nums.length; i++) {
            swap(nums, position, i);
            list.addAll(getPermutation(nums, position + 1));
            swap(nums, i, position);
        }

        return list;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp; 
    }
}