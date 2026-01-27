/*
Question:
3650. Minimum Cost Path with Edge Reversals

Each node has a switch usable once:
- At node u, you may reverse ONE incoming edge (v -> u) into (u -> v)
- Traversing the reversed edge costs 2 * w
- Reversal is only for that move

Return minimum cost from node 0 to node n-1.
*/

import java.util.*;

class Solution {
    public int minCost(int n, int[][] edges) {

        // Build adjacency list
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        // For each edge u -> v (w):
        // - normal edge: u -> v (w)
        // - reversed option: v -> u (2w)
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, 2 * w});
        }

        // Dijkstra
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long cost = cur[0];
            int u = (int) cur[1];

            if (cost > dist[u]) continue;

            for (int[] e : graph[u]) {
                int v = e[0];
                long w = e[1];

                if (cost + w < dist[v]) {
                    dist[v] = cost + w;
                    pq.offer(new long[]{dist[v], v});
                }
            }
        }

        return dist[n - 1] == Long.MAX_VALUE ? -1 : (int) dist[n - 1];
    }
}

 /*
Explanation:

Key Insight:
- Each node’s switch can be used at most once.
- So the state is not just (node), but (node, switchUsed).

State Definition:
- dist[u][0]: minimum cost to reach u without using its switch
- dist[u][1]: minimum cost to reach u after using its switch

Graph Transformation:
- Original edge u -> v with cost w is always allowed.
- For an edge x -> u with cost w:
  we allow a virtual edge u -> x with cost 2*w,
  but only if switch at u has not been used yet.

Algorithm:
- Use Dijkstra on (node, switchUsed) states.
- From each state, try:
  - normal outgoing edges
  - reversed edges only if switch is unused
- Take the minimum distance to (n-1, 0 or 1).

Why Dijkstra works:
- All edge costs are positive.
- Expanded state space is at most 2 * n.

Time Complexity:
- O((n + m) log (n)), where m = edges.length

Space Complexity:
- O(n + m)

This is a classic “graph + state” shortest path problem.
The trick is modeling edge reversals as conditional transitions.
*/
