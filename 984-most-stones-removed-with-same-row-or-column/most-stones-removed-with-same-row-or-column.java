class Solution {

    private static class DisjointSet {
        private final List<Integer> rank = new ArrayList<>();
        private final List<Integer> size = new ArrayList<>();
        private final List<Integer> parent = new ArrayList<>();
        
        public DisjointSet(int n) {
            for(int i = 0; i < n; i++) {
                rank.add(0);
                size.add(1);
                parent.add(i);
            }
        }
        
        public boolean isSameComponent(int u, int v) {
            return findUltimateParent(u) == findUltimateParent(v);
        }
        
        public void union(int u, int v) {
            unionByRank(u, v);
            // unionBySize(u, v);
        }
        
        public int findUltimateParent(int node) {
            if(node == parent.get(node)) {
                return node;
            }
            
            int ultimateParent = findUltimateParent(parent.get(node));
            parent.set(node, ultimateParent);
            
            return ultimateParent;
        }
        
        private void unionByRank(int u, int v) {
            int ultimateParentOfU = findUltimateParent(u);
            int ultimateParentOfV = findUltimateParent(v);
            
            if(ultimateParentOfU == ultimateParentOfV) {
                return;
            }
            
            if(rank.get(ultimateParentOfU) < rank.get(ultimateParentOfV)) {
                parent.set(ultimateParentOfU, ultimateParentOfV);
            } else if(rank.get(ultimateParentOfU) > rank.get(ultimateParentOfV)) {
                parent.set(ultimateParentOfV, ultimateParentOfU);
            } else {
                parent.set(ultimateParentOfV, ultimateParentOfU);
                rank.set(ultimateParentOfU, rank.get(ultimateParentOfU) + 1);
            }
        }
        
        private void unionBySize(int u, int v) {
            int ultimateParentOfU = findUltimateParent(u);
            int ultimateParentOfV = findUltimateParent(v);
            
            if(ultimateParentOfU == ultimateParentOfV) {
                return;
            }
            
            if(size.get(ultimateParentOfU) < size.get(ultimateParentOfV)) {
                parent.set(ultimateParentOfU, ultimateParentOfV);
                size.set(ultimateParentOfV, size.get(ultimateParentOfV) + size.get(ultimateParentOfU));
            } else {
                parent.set(ultimateParentOfV, ultimateParentOfU);
                size.set(ultimateParentOfU, size.get(ultimateParentOfU) + size.get(ultimateParentOfV));
            }
        }
    }

    public int removeStones(int[][] stones) {
        DisjointSet ds = new DisjointSet(30_000);
        Set<Integer> hashSet = new HashSet<>();

        for(int[] stone : stones) {
            int rowNode = stone[0];
            int colNode = 10001 + stone[1];

            ds.union(rowNode, colNode);

            hashSet.add(rowNode);
            hashSet.add(colNode);
        }

        int numOfComponents = 0;
        for(int node : hashSet) {
            if(ds.findUltimateParent(node) == node) {
                numOfComponents++;
            }
        }

        return stones.length - numOfComponents;
    }
}