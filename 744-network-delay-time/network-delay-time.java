class Solution {
    private static final class Pair {
        int node;
        int weight;

        public Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Pair>> adjacencyList = buildAdjacencyList(times, n);
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((a, b) -> a.weight - b.weight);

        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Pair src = new Pair(k, 0);
        priorityQueue.add(src);
        dist[k] = 0;

        while(!priorityQueue.isEmpty()) {
            Pair pair = priorityQueue.remove();
            int node = pair.node;

            for(Pair neighbour : adjacencyList.get(node)) {
                if((dist[node] + neighbour.weight) < dist[neighbour.node]) {
                    dist[neighbour.node] = dist[node] + neighbour.weight;
                    priorityQueue.add(new Pair(neighbour.node, dist[node] + neighbour.weight));
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 1; i < dist.length; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                return -1;
            }

            max = Math.max(max, dist[i]);
        }
        
        return max;
    }

    private static List<List<Pair>> buildAdjacencyList(int[][] times, int n) {
        List<List<Pair>> adjacencyList = new ArrayList<>();

        for(int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for(int i = 0; i < times.length; i++) {
            int u = times[i][0];
            int v = times[i][1];
            int w = times[i][2];

            adjacencyList.get(u).add(new Pair(v, w));
        }

        return adjacencyList;
    }
}