class Solution {
    public int findCircleNum(int[][] isConnected) {
        Map<Integer, List<Integer>> adjacencyMap = convertToAdjucencyList(isConnected);

        System.out.println(adjacencyMap);

        Set<Integer> isVisited = new HashSet<>();
        int count = 0;

        for(int node : adjacencyMap.keySet()) {
            if(!isVisited.contains(node)) {
                count++;
                dfs(node, adjacencyMap, isVisited);
            }
        }

        return count;
    }

    private Map<Integer, List<Integer>> convertToAdjucencyList(int[][] matrix) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i=0; i < matrix.length; i++) {
            map.put(i, new ArrayList<>());
        }

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 1) {
                    map.get(i).add(j);
                    map.get(j).add(i);
                }
            }
        }

        return map;
    }

    private void dfs(int node, Map<Integer, List<Integer>> adjacencyMap, Set<Integer> isVisited) {
        isVisited.add(node);

        for(int n : adjacencyMap.get(node)) {
            if(!isVisited.contains(n)) {
                dfs(n, adjacencyMap, isVisited);
            }
        }
    }
}