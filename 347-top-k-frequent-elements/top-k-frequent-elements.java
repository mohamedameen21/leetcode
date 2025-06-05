class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return hashMap.get(a) - hashMap.get(b);
            }
        });

        for (int num : hashMap.keySet()) {
            minHeap.add(num);

            if(minHeap.size() > k) minHeap.remove();
        }

        int[] result = new int[k];
        int i = 0;
        while (i < k) {
            result[i++] = minHeap.remove();
        }

        return result;
    }
}