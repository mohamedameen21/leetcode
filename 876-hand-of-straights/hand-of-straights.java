class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if(hand.length % groupSize != 0) return false;

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for(int card : hand) {
            treeMap.put(card, treeMap.getOrDefault(card, 0) + 1);
        }

        System.out.println(treeMap);

        for(int i = 0; i < hand.length / groupSize; i++) {
            int num = treeMap.firstKey();
            
            for(int j = 0; j < groupSize; j++) {
                if(!treeMap.containsKey(num)) {
                    return false;
                }

                int count = treeMap.get(num);

                if(count == 1) {
                    treeMap.remove(num);
                } else {
                    treeMap.put(num, count -1);
                }

                num++;
            }
        }

        return true;
    }
}