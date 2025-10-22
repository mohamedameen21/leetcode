class Solution {
    private static class Pair {
        int x;
        int y;
        int effort; 

        public Pair(int x, int y, int effort) {
            this.x = x;
            this.y = y;
            this.effort = effort;
        }

        public String toString() {
            return "( " + x + ", " + y + ", " + effort + ")";
        }
    }

    public int minimumEffortPath(int[][] heights) {
        int[][] efforts = new int[heights.length][heights[0].length];
        for(int[] arr : efforts) Arrays.fill(arr, Integer.MAX_VALUE);

        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((a, b) -> a.effort - b.effort);
        Pair source = new Pair(0, 0, 0);
        priorityQueue.add(source);
        efforts[0][0] = 0;

        while(!priorityQueue.isEmpty()) {
            Pair pair = priorityQueue.remove();

            if(pair.x == heights.length - 1 && pair.y == heights[pair.x].length - 1) {
                return pair.effort;
            }

            int[] row = {-1, 0, 1, 0};
            int[] col = {0, 1, 0, -1};

            for(int i = 0; i < row.length; i++) {
                int x = pair.x + row[i];
                int y = pair.y + col[i];

                if(x >= 0 && x < heights.length && y >= 0 && y < heights[x].length) {
                    int currentCell = heights[pair.x][pair.y];
                    int neighbourCell = heights[x][y];

                    // int effort = Math.abs(currentCell - neighbourCell); 
                    // we need to track the effort of all the path not only the current effort

                    int effort = Math.max(pair.effort, Math.abs(currentCell - neighbourCell));

                    if(effort < efforts[x][y]) {
                        efforts[x][y] = effort;
                        priorityQueue.add(new Pair(x, y, effort));
                    }
                }
            }
        }

        return -1;
    }
}