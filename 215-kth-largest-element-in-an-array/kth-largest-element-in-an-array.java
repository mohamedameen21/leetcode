class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();

        for(int num : nums) {
            if(priorityQueue.size() < k) {
                priorityQueue.add(num);
            } else if(num > priorityQueue.peek()) {
                priorityQueue.remove();
                priorityQueue.add(num);
            }
        }

        return priorityQueue.remove();
    }
}

