class MedianFinder {

    private PriorityQueue<Integer> leftMaxHeap;
    private PriorityQueue<Integer> rightMinHeap;

    public MedianFinder() {
        leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        rightMinHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if(leftMaxHeap.size() == rightMinHeap.size()) {
            leftMaxHeap.add(num);
        } else {
            rightMinHeap.add(num);
        }

        if(leftMaxHeap.size() > 0 && rightMinHeap.size() > 0 && leftMaxHeap.peek() > rightMinHeap.peek()) {
            rightMinHeap.add(leftMaxHeap.remove());
            leftMaxHeap.add(rightMinHeap.remove());
        }
    }
    
    public double findMedian() {
        if((leftMaxHeap.size() + rightMinHeap.size()) % 2 == 0) {
            return (leftMaxHeap.peek() + rightMinHeap.peek()) / 2.0;
        } else {
            return leftMaxHeap.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */