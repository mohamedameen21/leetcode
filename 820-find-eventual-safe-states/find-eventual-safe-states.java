class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<List<Integer>> adjacencyList = getReversedAdjacencyList(graph);
        int[] inboundDegree = new int[graph.length];

        for(int i = 0; i < graph.length; i++) {
            for(int neighbour : adjacencyList.get(i)) {
                inboundDegree[neighbour]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < inboundDegree.length; i++) {
            if(inboundDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> safeNodes = new ArrayList<>();

        while(!queue.isEmpty()) {
            int node = queue.remove();
            safeNodes.add(node);

            for(int neighbour : adjacencyList.get(node)) {
                inboundDegree[neighbour]--;

                if(inboundDegree[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }

        Collections.sort(safeNodes);

        return safeNodes;
    }

    private List<List<Integer>> getReversedAdjacencyList(int[][] graph) {
        List<List<Integer>> list = new ArrayList<>();

        for(int i = 0; i < graph.length; i++) {
            list.add(new ArrayList<>());
        }

        for(int v = 0; v < graph.length; v++) {
            for(int u = 0; u < graph[v].length; u++) {
                list.get(graph[v][u]).add(v);
            }
        }

        return list;
    }
}