import java.util.*;

class Solution {

    private static class Pair {
        int node;
        int cost;
        int stops;

        public Pair(int node, int cost, int stops) {
            this.node = node;
            this.cost = cost;
            this.stops = stops;
        }

        public String toString() {
            return "(" + node + ", " + cost + ", " + stops + ")";
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Step 1: Build adjacency list
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] flight : flights) {
            adj.get(flight[0]).add(new Pair(flight[1], flight[2], 0)); // (to, cost)
        }

        // Step 2: BFS setup
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(src, 0, 0));

        // Step 3: Distance array to store min cost to each node
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Step 4: BFS traversal (level by stops)
        while (!queue.isEmpty()) {
            Pair curr = queue.remove();

            // If we already exceeded max stops, skip
            if (curr.stops > k) continue;

            // Explore neighbors
            for (Pair next : adj.get(curr.node)) {
                int newCost = curr.cost + next.cost;

                // Only proceed if this path offers a cheaper cost
                if (newCost < dist[next.node]) {
                    dist[next.node] = newCost;
                    queue.add(new Pair(next.node, newCost, curr.stops + 1));
                }
            }
        }

        // Step 5: Result
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
