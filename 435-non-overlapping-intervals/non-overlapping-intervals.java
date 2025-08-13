class Solution {

    private static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return "("+start+", "+end+")";
        }
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        List<Interval> list = new ArrayList<>();

        for(int[] interval : intervals) {
            list.add(new Interval(interval[0], interval[1]));
        }

        Collections.sort(list, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.end - b.end;
            }
        });

        int minToBeRemoved = 0;
        int prevEndTime = Integer.MIN_VALUE;

        for(Interval interval : list) {
            if(interval.start >= prevEndTime){
                prevEndTime = interval.end;
            } else {
                minToBeRemoved++;
            }
        }

        return minToBeRemoved;
    }
}