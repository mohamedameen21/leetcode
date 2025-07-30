class Solution {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;

        // Add index to each task
        int[][] tasksWithIndex = new int[n][3]; // {enqueueTime, processingTime, index}
        for (int i = 0; i < n; i++) {
            tasksWithIndex[i][0] = tasks[i][0];
            tasksWithIndex[i][1] = tasks[i][1];
            tasksWithIndex[i][2] = i;
        }

        // Sort by enqueueTime
        Arrays.sort(tasksWithIndex, Comparator.comparingInt(a -> a[0]));

        // Priority Queue: sort by processingTime, then index
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[1] != b[1] ? Integer.compare(a[1], b[1]) : Integer.compare(a[2], b[2])
        );

        int time = 0, i = 0, idx = 0;
        int[] result = new int[n];

        while (i < n || !pq.isEmpty()) {
            // Push all available tasks to queue
            while (i < n && tasksWithIndex[i][0] <= time) {
                pq.offer(tasksWithIndex[i]);
                i++;
            }

            if (!pq.isEmpty()) {
                int[] task = pq.poll();
                time += task[1]; // process time
                result[idx++] = task[2]; // original index
            } else {
                // No available tasks, jump time
                time = tasksWithIndex[i][0];
            }
        }

        return result;
    }
}
