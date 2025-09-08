class Solution {
    public void solve(char[][] board) {

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if((i == 0 || i == board.length - 1 || j == 0 || j == board[i].length - 1) && board[i][j] == 'O') {
                    dfs(board, i, j);
                } 
            }
        }

        replace(board, 'O', 'X');
        replace(board, 'S', 'O');
    }

    private static void replace(char[][] board, char source, char destination) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == source) {
                    board[i][j] = destination;
                }
            }
        }
    }

    private static void dfs(char[][] board, int x, int y) {
        board[x][y] = 'S';

        int[] cols = {-1, 0, 1, 0};
        int[] rows = {0, 1, 0, -1};

        for(int i = 0; i < 4; i++) {
            int newX = x + rows[i];
            int newY = y + cols[i];

            if(newX >= 0 && newX < board.length && newY >= 0 && newY < board[newX].length && board[newX][newY] == 'O') {
                dfs(board, newX, newY);
            }
        }
    }   
}