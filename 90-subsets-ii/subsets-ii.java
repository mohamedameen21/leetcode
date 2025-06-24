class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        return getUniqueSubsets(nums,new ArrayList<>(), 0);
    }

    private List<List<Integer>> getUniqueSubsets(int[] nums, List<Integer> temp, int index) {
        List<List<Integer>> list = new ArrayList<>();

        if (index >= nums.length) {
            list.add(temp);
            return list;
        }

        temp.add(nums[index]);
        list.addAll(getUniqueSubsets(nums, new ArrayList<>(temp), index + 1));

        temp.remove(temp.size() - 1);

        while (index + 1 < nums.length && nums[index] == nums[index + 1])
            index++;

        list.addAll(getUniqueSubsets(nums, new ArrayList<>(temp), index + 1));

        return list;
    }
}