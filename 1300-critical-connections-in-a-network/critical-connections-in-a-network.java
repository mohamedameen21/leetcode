class Solution {

    private int[] timeToReachNode;
    private int[] lowestAdjacentNodeTime;
    private boolean[] visited;
    private List<List<Integer>> bridges;
    private int timer;
    

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> adjacencyList = buildAdjacencyList(connections, n);

        timeToReachNode = new int[n];
        Arrays.fill(timeToReachNode, Integer.MAX_VALUE);

        lowestAdjacentNodeTime = new int[n];

        visited = new boolean[n];
        bridges = new ArrayList<>();

        for(int i = 0; i < adjacencyList.size(); i++) {
            if(!visited[i]) {
                dfs(adjacencyList, i, -1);
            }
        }

        return bridges;
    }

    private void dfs(List<List<Integer>> adjacencyList, int node, int parent) {
        visited[node] = true;
        timeToReachNode[node] = lowestAdjacentNodeTime[node] = ++timer;

        for(int neighbour : adjacencyList.get(node)) {   
            if(neighbour == parent) {
                continue;
            }

            if(!visited[neighbour]) {
                dfs(adjacencyList, neighbour, node);
                lowestAdjacentNodeTime[node] = Math.min(lowestAdjacentNodeTime[node], lowestAdjacentNodeTime[neighbour]);

                if(lowestAdjacentNodeTime[neighbour] > timeToReachNode[node]) {
                    bridges.add(new ArrayList<>(Arrays.asList(node, neighbour)));
                }
            } else {
                lowestAdjacentNodeTime[node] = Math.min(lowestAdjacentNodeTime[node], lowestAdjacentNodeTime[neighbour]);
            }
        }
    } 

    private List<List<Integer>> buildAdjacencyList(List<List<Integer>> edges, int n) {
        List<List<Integer>> adjacencyList = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());            
        }

        for(List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }

        return adjacencyList;
    }
}