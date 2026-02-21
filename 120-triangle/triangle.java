class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.get(triangle.size()-1).size()];
        for(int[] d : dp) {
            Arrays.fill(d, Integer.MIN_VALUE);
        }

        // return getMinPathSum(triangle, 0, 0, dp);

        return getMinPathSum(triangle);
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

    private static int getMinPathSum(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.get(triangle.size()-1).size()];

        List<Integer> lastRow = triangle.get(triangle.size()-1);

        for(int i = 0; i < lastRow.size(); i++) {
            dp[i] = lastRow.get(i);
        }

        for(int i = triangle.size()-2; i >= 0; i--) {
            for(int j = 0; j < triangle.get(i).size(); j++) {
                int cell = triangle.get(i).get(j);

                int down = dp[j];
                int diagonal = dp[j+1];

                dp[j] = cell + Math.min(down, diagonal);
            }
        }

        return dp[0];
    }
}