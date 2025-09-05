class Solution {
    public int numIslands(char[][] grid) {
        char[][] copy = new char[grid.length][grid[0].length];

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                copy[i][j] = grid[i][j];
            }
        }

        int noOfIslands = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(copy[i][j] == '1') {
                    dfsShrink(copy, i, j);
                    noOfIslands++;
                }
            }
        }

        return noOfIslands;
    }

    private void dfsShrink(char[][] grid, int i, int j) {
        grid[i][j] = '0';

        int[] colAcc = {-1, 0, 1, 0};
        int[] rowAcc = {0, 1, 0, -1};

        for(int dir = 0; dir < 4; dir++) {
            int row = i + rowAcc[dir];
            int col = j + colAcc[dir];

            if(row < grid.length && row >= 0 && col < grid[row].length && col >= 0 && grid[row][col] == '1') {
                dfsShrink(grid, row, col);
            }
        }
    }
}