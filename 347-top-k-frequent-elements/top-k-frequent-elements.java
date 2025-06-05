class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return hashMap.get(b) - hashMap.get(a);
            }
        });

        for (int num : hashMap.keySet()) {
            maxHeap.add(num);
        }

        int[] result = new int[k];
        int i = 0;
        while (i < k) {
            result[i++] = maxHeap.remove();
        }

        return result;
    }
}