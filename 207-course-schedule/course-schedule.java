class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = constructAdjacencyList(numCourses, prerequisites);
        int[] visited = new int[numCourses];
        int[] currentPath = new int[numCourses];

        for(int i = 0; i < numCourses; i++) {
            if(visited[i] != 1) {
                if(checkCycle(adjacencyList, i, visited, currentPath)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkCycle(List<List<Integer>> adjacencyList, int node, int[] visited, int[] currentPath) {
        visited[node] = 1;
        currentPath[node] = 1;

        for(int neighbour : adjacencyList.get(node)) {
            if(currentPath[neighbour] == 1) { // cycle found
                return true;
            }

            if(visited[neighbour] != 1) {
                if(checkCycle(adjacencyList, neighbour, visited, currentPath)) {
                    return true;
                }
            }
        }

        currentPath[node] = 0;
        return false;
    }

    private List<List<Integer>> constructAdjacencyList(int n, int[][] edges) {
        List<List<Integer>> adjacencyList = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for(int i = 0; i < edges.length; i++) {
            int v = edges[i][0];
            int u = edges[i][1];

            adjacencyList.get(u).add(v);
        }

        return adjacencyList;
    }
}