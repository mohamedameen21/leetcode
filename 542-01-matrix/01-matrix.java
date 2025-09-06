class Solution {
    private static class Pair {
        int x;
        int y;
        int dist;

        Pair(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public int[][] updateMatrix(int[][] mat) {
        int[][] distanceFromZero = new int[mat.length][mat[0].length];
        int[][] isVisited = new int[mat.length][mat[0].length];
        Queue<Pair> queue = new LinkedList<>();

        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[i].length; j++) {
                if(mat[i][j] == 0) {
                    queue.add(new Pair(i, j, 0));
                    isVisited[i][j] = 1;
                }
            }
        }


        while(!queue.isEmpty()) {
            Pair pair = queue.remove();
            distanceFromZero[pair.x][pair.y] = pair.dist;

            int[] cols = {-1, 0, 1, 0};
            int[] rows = {0, 1 , 0, -1};

            for(int i = 0; i < 4; i++) {
                int newX = pair.x + rows[i];
                int newY = pair.y + cols[i];

                if(newX >= 0 && newX < mat.length && newY >= 0 && newY < mat[newX].length 
                    && isVisited[newX][newY] == 0) {
                        isVisited[newX][newY] = 1;
                        queue.add(new Pair(newX, newY, pair.dist + 1));
                }
            }
        }

        return distanceFromZero;
    }
}