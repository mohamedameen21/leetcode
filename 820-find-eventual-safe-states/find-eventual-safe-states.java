class Solution {
    public List<Integer> eventualSafeNodes(int[][] adjacencyArray) {
        int[] visited = new int[adjacencyArray.length];
        int[] currentPath = new int[adjacencyArray.length];
        int[] safeNodes = new int[adjacencyArray.length];

        for(int i = 0; i < adjacencyArray.length; i++) {
            if(visited[i] == 0) {
                checkCycleWithDFS(adjacencyArray, i, visited, currentPath, safeNodes);
            }
        }

        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < safeNodes.length; i++) {
            if(safeNodes[i] == 1) 
                list.add(i);
        }

        return list;
    }

// Here we are just using the same logic for checking the cycle in the graph, but here we also makr the path of the cycle - So that we know
// that it won't be a part of the safe nodes.

// we will check safe node or not only after checking all the adjacent nodes on that node
    private static boolean checkCycleWithDFS(int[][] adjacencyArray, int nodeIndex, int[] visited, int[] currentPath, int[] safeNodes) {
        visited[nodeIndex] = 1;
        currentPath[nodeIndex] = 1;

        for(int neighbourIndex : adjacencyArray[nodeIndex]) {
            // If we deduct a cycle we return true and now move for next node or cycle or another component 
            if(visited[neighbourIndex] == 1 && currentPath[neighbourIndex] == 1) {
                return true;
            }

            // Considering only the non visited node, if the node is already visited then those neighbour too visied, so don't need to revisit
            if(visited[neighbourIndex] == 0) {
                if(checkCycleWithDFS(adjacencyArray, neighbourIndex, visited, currentPath, safeNodes)) {
                    return true;
                }
            }
        }

        // when the execution came here then this node doesn't find a cycle or doesn't a part of the cycle
        safeNodes[nodeIndex] = 1;
        currentPath[nodeIndex] = 0;

        return false;
    }
}