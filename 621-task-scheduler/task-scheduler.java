class Solution {
    private static class Pair {
        int count;
        int availabilityTime;

        Pair(int count, int availabilityTime) {
            this.count = count;
            this.availabilityTime = availabilityTime;
        }
    }

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> hashMap = new HashMap<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int timeTaken = 0;

        for (char task : tasks) {
            hashMap.put(task, hashMap.getOrDefault(task, 0) + 1);
        }

        for (int count : hashMap.values())
            maxHeap.add(count);

        Queue<Pair> queue = new LinkedList<>();
        // <Count, TimeThisTaskCanRunAgain>

        while (!maxHeap.isEmpty() || !queue.isEmpty()) {
            timeTaken++;

            if (!queue.isEmpty()) {
                Pair p = queue.peek();

                if (timeTaken > p.availabilityTime) {
                    queue.remove();
                    maxHeap.add(p.count);
                }
            }

            if(maxHeap.isEmpty()) continue;

            int count = maxHeap.remove();

            if (count > 1) {
                queue.add(new Pair(count - 1, timeTaken + n));
            }
        }

        return timeTaken;
    }
}