class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = constructAdjacencyList(numCourses, prerequisites);
        List<Integer> topo = getTopoList(adjacencyList, numCourses);

        if(topo.size() < numCourses) {
            return new int[]{};
        }
        
        // Convert List<Integer> to int[]
        int[] result = new int[topo.size()];
        for (int i = 0; i < topo.size(); i++) {
            result[i] = topo.get(i);
        }

        return result;
    }

    private List<Integer> getTopoList(List<List<Integer>> adjacencyList, int v) {
          // Step 2: Compute in-degrees
        int[] inDegree = new int[v];
        for (int u = 0; u < v; u++) {
            for (int nei : adjacencyList.get(u)) {
                inDegree[nei]++;
            }
        }

        // Step 3: Queue with all nodes having in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        ArrayList<Integer> topoOrder = new ArrayList<>();

        // Step 4: Process queue
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topoOrder.add(node);

            for (int nei : adjacencyList.get(node)) {
                inDegree[nei]--;
                if (inDegree[nei] == 0) {
                    queue.add(nei);
                }
            }
        }

        return topoOrder;
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