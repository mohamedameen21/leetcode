class Solution {
    public int candy(int[] ratings) {
        int[] result = new int[ratings.length];
        result[0] = 1;
        int noOfCandies = 0;

        for(int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i -1]) {
                result[i] = result[i - 1] + 1;
            } else {
                result[i] = 1;
            }
        }

        System.out.println(Arrays.toString(result));

        noOfCandies = result[ratings.length -1];
        for(int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) {
                result[i] = Math.max(result[i+1] + 1, result[i]);
                noOfCandies += Math.max(result[i+1] + 1, result[i]);
            } else {
                noOfCandies += result[i];
            }
        }

        System.out.println(Arrays.toString(result));

        return noOfCandies;
    }
}