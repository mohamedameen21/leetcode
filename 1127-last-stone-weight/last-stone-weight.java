class Solution {
    public int lastStoneWeight(int[] stones) {
        if(stones.length == 1) return stones[0];

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        for(int stone : stones) priorityQueue.add(stone);

        while(priorityQueue.size() > 1) {
            int stone1 = priorityQueue.remove();
            int stone2 = priorityQueue.remove();

            if(stone1 == stone2) continue;

            priorityQueue.add(Math.abs(stone1 - stone2));
        }

        return priorityQueue.isEmpty() ? 0 : priorityQueue.remove();
    }
}