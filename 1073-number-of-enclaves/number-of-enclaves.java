class Solution {
    public int numEnclaves(int[][] grid) {
        int[][] visited = new int[grid.length][grid[0].length];

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if((i == 0 || i == grid.length - 1 || j == 0 || j == grid[i].length - 1) && grid[i][j] == 1) {
                    dfs(grid, i, j, visited);
                } 
            }
        }

        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1 && visited[i][j] == 0) {
                    count++;
                }
            }
        }
        
        return count;
    }

    private static void dfs(int[][] grid, int x, int y, int[][] visited) {
        visited[x][y] = 1;

        int[] cols = {-1, 0, 1, 0};
        int[] rows = {0, 1, 0, -1};

        for(int i = 0; i < 4; i++) {
            int newX = x + rows[i];
            int newY = y + cols[i];

            if(newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[newX].length 
                && visited[newX][newY] == 0 && grid[newX][newY] == 1) {
                dfs(grid, newX, newY, visited);
            }
        }
    }   
}