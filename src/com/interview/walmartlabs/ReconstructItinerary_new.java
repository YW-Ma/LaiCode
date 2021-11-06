package com.interview.walmartlabs;

import java.util.*;

public class ReconstructItinerary_new {
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();   // 我这个解法是错的。如果是这个例子，会直接走到AAA，然后就死在那里了。
        tickets.add(List.of("JFK", "AAA"));
        tickets.add(List.of("JFK", "BBB"));
        tickets.add(List.of("BBB", "CCC"));
        tickets.add(List.of("CCC", "JFK"));

        Solution sol = new Solution();
        List<String> res = sol.findItinerary(tickets);
        System.out.println(res);
    }

    static class Solution {
        // origin -> list of destinations
        HashMap<String, LinkedList<String>> flightMap = new HashMap<>();
        List<String> terminals = null;

        public List<String> findItinerary(List<List<String>> tickets) {
            // Step 1). build the graph first
            for (List<String> ticket : tickets) {
                String origin = ticket.get(0);
                String dest = ticket.get(1);
                if (this.flightMap.containsKey(origin)) {
                    LinkedList<String> destList = this.flightMap.get(origin);
                    destList.add(dest);
                } else {
                    LinkedList<String> destList = new LinkedList<>();
                    destList.add(dest);
                    this.flightMap.put(origin, destList);
                }
            }

            // Step 2). order the destinations
            this.flightMap.forEach((key, value) -> Collections.sort(value)); // lambda函数拿到了value的reference，然后对各个value内部进行了排序。不需要put回去。

            this.terminals = new ArrayList<String>();
            // Step 3). post-order DFS
            this.DFS("JFK");
            Collections.reverse(this.terminals);
            return this.terminals;
        }

        protected void DFS(String origin) {
            // Visit all the outgoing edges first.
            if (this.flightMap.containsKey(origin)) { // 1. 如果origin存在，那么就post-order遍历
                LinkedList<String> destList = this.flightMap.get(origin);   // 把邻接表拿出来
                while (!destList.isEmpty()) {
                    // while we visit the edge, we trim it off from graph.
                    String dest = destList.pollFirst(); // 不需要恢复现场！
                    DFS(dest);
                }
            }
            // add the airport to the head of the itinerary
            this.terminals.add(origin);
            // 只有自己的destList已经为空的时候，才会走到这里。这意味着自己是当前视角下的重点。
        }
    }
}
