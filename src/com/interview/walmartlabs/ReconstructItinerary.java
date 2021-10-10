package com.interview.walmartlabs;

import java.util.*;

public class ReconstructItinerary {
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();   // 我这个解法是错的。如果是这个例子，会直接走到AAA，然后就死在那里了。
        tickets.add(List.of("JFK", "AAA"));
        tickets.add(List.of("JFK", "BBB"));
        tickets.add(List.of("BBB", "CCC"));
        tickets.add(List.of("CCC", "JFK"));

        List<String> res = findItinerary(tickets);
        System.out.println(res);
    }
    // starts from JFK
    // traversal a directed graph (may have cycle)

    // 对于之前的问题：
    // 如何避免走到死胡同产生无解问题： 不可以再使用while循环了，应该使用DFS。
    // 因为while循环删了node就从map里消失了。应该用DFS实现 trail-fail-and-fallback strategy.
    // enumerate all possible choice in a node, and wait the response from a choice. If it failed, then try next choice.

    public static List<String> findItinerary(List<List<String>> tickets) {
        // corner case:
        List<String> route = new ArrayList<>();
        if (tickets == null || tickets.size() == 0) {
            return route;
        }
        // try to build the graph --> graph to reconstruct the graph of his trip      A HashMap of departure airport --> the list of target airport
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, boolean[]> isVisited = new HashMap<>();
        for (List<String> ticket : tickets) {
            // generate node
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!graph.containsKey(from)) {
                graph.put(from, new ArrayList<String>());
            }
            if (!graph.containsKey(to)) {
                graph.put(to, new ArrayList<String>());
            }
            // generate edge
            List<String> neighbors = graph.get(from);
            neighbors.add(to);
            graph.put(from, neighbors);
        }
        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            Collections.sort(entry.getValue()); // sort the graph (we have dereference here)
            // we need to make sure we go through each edge only once.
            isVisited.put(entry.getKey(), new boolean[entry.getValue().size()]);
        }

        // try to traverse this graph:
        route.add("JFK");
        helper(graph, isVisited, "JFK", route, tickets.size() + 1);
        return route;
    }

    private static boolean helper(Map<String, List<String>> graph, Map<String, boolean[]> isVisited, String root, List<String> route, int validLength) {
        // base case:
        if (isAllVisited(isVisited, root)) {
            System.out.print("root");
            System.out.println(route);
            return route.size() == validLength;
        }
        // recursion rule:
        List<String> neighbors = graph.get(root);
        boolean[] isVisitedArray = isVisited.get(root);
        for (int i = 0; i < neighbors.size(); i++) { // 只能在没造访过的里面找：
            if (isVisitedArray[i] == true) {
                continue;
            }
            String tar = neighbors.get(i);
            route.add(tar);
            isVisitedArray[i] = true;
            // isVisited.put(root, isVisitedArray); // 其实这里可以不用存回去, 修改isVisitedArray的时候，value就直接改了 (reference type)
            System.out.println(neighbors);
            if (helper(graph, isVisited, tar, route, validLength)) {
                return true;
            }
            isVisitedArray[i] = false;
            route.remove(route.size() - 1);
        }
        return false;
    }

    private static boolean isAllVisited(Map<String, boolean[]> isVisited, String root) {
        for (boolean b : isVisited.get(root)) {
            if (!b) { // 找到了一个没访问过的
                return false;
            }
        }
        return true; // isVisited为空，或者全部都访问过
    }
}
