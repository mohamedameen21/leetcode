class KthLargest {

    private PriorityQueue<Integer> minHeap;
    private int sizeOfHeap;

    public KthLargest(int k, int[] nums) {
        minHeap = new PriorityQueue<>();
        sizeOfHeap = k;

        for(int num : nums) {
            minHeap.add(num);

            if(minHeap.size() > k) {
                minHeap.remove();
            }
        }
    }
    
    public int add(int val) {
        minHeap.add(val);

        if(minHeap.size() > sizeOfHeap) minHeap.remove();

        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */