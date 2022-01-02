package com.jiuzhang.BFS进阶与动态规划.飞行棋;

import java.util.*;

public class BFS {
    public static void main(String[] args) {
        int[][] connections = new int[][]{{7, 9}, {8, 14}};
        // 1->7(9)
        // 9->15
        int length = 15;
        System.out.println(modernLudo(length, connections));
    }

    // 输入一维飞行棋的length和可以单向瞬间移动的connections
    // 棋盘用int表达，从1开始到length
    public static int modernLudo(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);

        // 先把开头加入
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> distance = new HashMap<>();
        queue.offer(1);
        distance.put(1, 0);
        // 每次把自己的neighbor，以及每个neighbor的connection目的地加入。 这些点被认为是“一步”的距离
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int rightLimit = Math.min(length, node + 6);
            for (int neighbor = node + 1; neighbor <= rightLimit; neighbor++) {
                // 其实这里相当于模板中的 if (valid(neighbor)), 只是这里的neighbor还要包括neighbor的连通域
                List<Integer> connectedNodes = getConnectedNodes(graph, neighbor);
                for (int connectedNode : connectedNodes) {
                    if (!distance.containsKey(connectedNode)) { // 跳过那些已经被获得过最短路径的
                        // 对于一个新的neighbor，需要更新它的最短路径
                        distance.put(connectedNode, distance.get(node) + 1);
                        queue.offer(connectedNode);
                    }
                }
            }
        }

        return distance.get(length);
    }

    // 找到某个neighbor的在graph中的连通块的算法（一个小BFS）
    private static List<Integer> getConnectedNodes(Map<Integer, Set<Integer>> graph, int neighbor) {
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        List<Integer> connectedNodes = new ArrayList<>();
        queue.offer(neighbor);
        set.add(neighbor);
        connectedNodes.add(neighbor);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int nei : graph.get(node)) {
                if (!set.contains(nei)) {
                    queue.offer(nei);
                    connectedNodes.add(nei);
                }
            }
        }
        return connectedNodes;
    }

    // 构造connections的图，因为二维数组connections不适合进行“从某个点出发可以瞬移到哪些点”这种查询
    private static Map<Integer, Set<Integer>> buildGraph(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        // 拆成两个for循环会简单很多
        for (int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] conn : connections) {
            graph.get(conn[0]).add(conn[1]);
        }
        return graph;
    }
}
