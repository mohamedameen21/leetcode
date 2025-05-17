class Solution {

    private class Pair implements Comparable<Pair> {
        int num;
        int row;
        int col;

        Pair(int num , int row, int col) {
            this.num = num;
            this.row = row;
            this.col = col;
        }

        public int compareTo(Pair other) {
            return this.num - other.num;
        }

        public String toString() {
            return this.num + "";
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();

        for(int i = 0; i < Math.min(k, matrix.length); i++) {
            priorityQueue.add(new Pair(matrix[i][0], i, 0));
        }

        while(k > 1) {
            Pair pair = priorityQueue.remove();
            
            if(pair.col < matrix[pair.row].length - 1)
                priorityQueue.add(new Pair(matrix[pair.row][pair.col+1], pair.row, pair.col+1));
            k--;
        }

        return priorityQueue.remove().num;
    }
}