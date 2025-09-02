class Solution {
    private class Pair { 
        int x; 
        int y; 

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int orangesRotting(int[][] grid) {
        Queue<Pair> queue = new LinkedList<>();

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 2) {
                    queue.add(new Pair(i, j));
                }
            }
        }

        int time = 0;

     while (!queue.isEmpty()) {
    int size = queue.size();
    boolean rottedThisRound = false;  // track if anything rotted this minute

    for (int i = 0; i < size; i++) {
        Pair pair = queue.remove();
        int x = pair.x;
        int y = pair.y;

        // Right
        if (y + 1 < grid[0].length && grid[x][y+1] == 1) {
            grid[x][y+1] = 2;
            queue.add(new Pair(x, y+1));
            rottedThisRound = true;
        }

        // Left
        if (y - 1 >= 0 && grid[x][y-1] == 1) {
            grid[x][y-1] = 2;
            queue.add(new Pair(x, y-1));
            rottedThisRound = true;
        }

        // Top
        if (x - 1 >= 0 && grid[x-1][y] == 1) {
            grid[x-1][y] = 2;
            queue.add(new Pair(x-1, y));
            rottedThisRound = true;
        }

        // Down
        if (x + 1 < grid.length && grid[x+1][y] == 1) {
            grid[x+1][y] = 2;
            queue.add(new Pair(x+1, y));
            rottedThisRound = true;
        }
    }

    if (rottedThisRound) time++;  // only count minutes when something rots
}


        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return time;
    }
}