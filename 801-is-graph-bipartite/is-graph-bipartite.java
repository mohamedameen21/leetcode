class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] colour = new int[graph.length];
        Arrays.fill(colour, -1);
        
        for(int i = 0; i < colour.length; i++) {
            if(colour[i] == -1) {
                if(!dfs(graph, colour, i, 0)) {
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

    private static boolean dfs(int[][] adjacencyArray, int[] colourOf, int index, int colour) {
        colourOf[index] = colour;

        for(int neighbour : adjacencyArray[index]) {
            if(colourOf[neighbour] == -1) {
                if(!dfs(adjacencyArray, colourOf, neighbour, colourOf[index] ^ 1)) {
                    return false;
                }
            } else if(colourOf[neighbour] == colourOf[index]) {
                return false;
            }
        }

        return true;
    }
}