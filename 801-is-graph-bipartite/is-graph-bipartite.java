class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] colour = new int[graph.length];
        Arrays.fill(colour, -1);
        
        for(int i = 0; i < colour.length; i++) {
            if(colour[i] == -1) {
                if(!bfs(graph, colour, i)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean bfs(int[][] adjacencyArray, int[] colourOf, int nodeIndex) {
        Queue<Integer> queue = new LinkedList<>();  
        colourOf[nodeIndex] = 0;
        queue.add(nodeIndex);

        while(!queue.isEmpty()) {
            int node = queue.remove();

            for(int neighbour : adjacencyArray[node]) {
                if (colourOf[neighbour] == -1) {
                    colourOf[neighbour] = colourOf[node] ^ 1; 
                    queue.add(neighbour);
                } else if (colourOf[neighbour] == colourOf[node]) {
                    return false;
                }
            }
        }

        return true;
    }
}