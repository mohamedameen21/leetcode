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
            // unionByRank(u, v);
            unionBySize(u, v);
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

        public int getComponentSize(int node) {
            return size.get(findUltimateParent(node));
        }
    }

    public int largestIsland(int[][] grid) {
        DisjointSet ds = new DisjointSet(grid.length * grid.length);

        // building the DSU
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1) {
                    int[] rowSum = {-1, 0, 1, 0};
                    int[] colSum = {0, 1, 0, -1};

                    for(int k = 0; k < rowSum.length; k++) {
                        int neighbourX = i + rowSum[k];
                        int neighbourY = j + colSum[k];

                        if(neighbourX < 0 || neighbourX >= grid.length || neighbourY < 0 || neighbourY >= grid[neighbourX].length) {
                            continue;
                        }

                        if(grid[neighbourX][neighbourY] == 1) {
                            int u = getFlattenedIndex(grid, i, j);
                            int v = getFlattenedIndex(grid, neighbourX, neighbourY);

                            if(!ds.isSameComponent(u, v)) {
                                ds.union(u, v);
                            }
                        }
                    }
                }
            }
        }

        int maxSize = 0;
        boolean isAll1s = true;;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 0) {
                    isAll1s = false;
                    int[] rowSum = {-1, 0, 1, 0};
                    int[] colSum = {0, 1, 0, -1};

                    Set<Integer> uniqueComponents = new HashSet<>();
                    for(int k = 0; k< rowSum.length; k++) {
                        int neighbourX = i + rowSum[k];
                        int neighbourY = j + colSum[k];

                        if(neighbourX < 0 || neighbourX >= grid.length || neighbourY < 0 || neighbourY >= grid[neighbourX].length) {
                            continue;
                        }

                        if(grid[neighbourX][neighbourY] == 1) {
                            int v = getFlattenedIndex(grid, neighbourX, neighbourY);
                            uniqueComponents.add(ds.findUltimateParent(v));
                        }
                    }

                    int size = 1;
                    for(int node : uniqueComponents) {
                        size += ds.getComponentSize(node);
                    }

                    maxSize = Math.max(maxSize, size);
                }
            }
        }

        return isAll1s ? grid.length * grid.length : maxSize;
    }

    private int getFlattenedIndex(int[][] arr, int i, int j) {
        int col = arr[i].length;

        return (col * i) + j;
    }
}