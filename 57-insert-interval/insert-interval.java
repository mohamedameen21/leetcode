class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0;
        List<List<Integer>> list = new ArrayList<>();

        while(i < intervals.length && intervals[i][1] < newInterval[0]) {
            List<Integer> l = new ArrayList<>();
            l.add(intervals[i][0]);
            l.add(intervals[i][1]);
            list.add(l);
            i++;
        }

        while(i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        List<Integer> newInter = new ArrayList<>();
        newInter.add(newInterval[0]);
        newInter.add(newInterval[1]);
        list.add(newInter);

        while(i < intervals.length) {
            List<Integer> l = new ArrayList<>();
            l.add(intervals[i][0]);
            l.add(intervals[i][1]);

            list.add(l);
            i++;
        }

        int[][] result = new int[list.size()][2];
        i = 0;

        for(List<Integer> l : list) {
            result[i][0] = l.get(0);
            result[i][1] = l.get(1); 
            i++;
        }

        return result;
    }
}