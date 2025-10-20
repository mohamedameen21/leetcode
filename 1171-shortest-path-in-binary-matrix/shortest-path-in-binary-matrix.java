class Solution {
    private static class Pair
    {
        int i;
        int j;

    public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

    public String toString() {
        return "( " + i + ", " + j + ")";
    }

    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] != 0) {
            return -1;
        }

        Queue<Pair> queue = new LinkedList<>();
        int[][] visited = new int[grid.length][grid.length];

        Pair source = new Pair(0, 0);
        visited[0][0] = 1;
        queue.add(source);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Pair pair = queue.remove();

                if (pair.i == grid.length - 1 && pair.j == grid.length - 1) {
                    return level + 1;
                }

                int[] row = {-1, -1, 0, 1, 1, 1, 0, -1 };
                int[] col = {0, 1, 1, 1, 0, -1, -1, -1 };

                for(int j = 0; j < row.length; j++) {
                    int x = pair.i + row[j];
                    int y = pair.j + col[j];

                    if(x >= 0 && x < grid.length && y >= 0 && y < grid.length) {
                        if(grid[x][y] == 0 && visited[x][y] == 0) {
                            visited[x][y] = 1;
                            queue.add(new Pair(x, y));
                        }
                    }
                }
            }

            level++;
        }

        return -1;
    }
}