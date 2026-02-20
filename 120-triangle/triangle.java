class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.get(triangle.size()-1).size()];
        for(int[] d : dp) {
            Arrays.fill(d, Integer.MIN_VALUE);
        }

        return getMinPathSum(triangle, 0, 0, dp);
    }

    private static int getMinPathSum(List<List<Integer>> triangle, int i, int j, int[][] dp) {
        if(i == triangle.size() - 1) {
            return triangle.get(i).get(j);
        }

        if(dp[i][j] != Integer.MIN_VALUE) {
            return dp[i][j];
        }

        int down = triangle.get(i).get(j) + getMinPathSum(triangle, i+1, j, dp);
        int downDigonal = triangle.get(i).get(j) + getMinPathSum(triangle, i+1, j+1, dp);

        dp[i][j] = Math.min(down, downDigonal);

        return dp[i][j];
    }
}