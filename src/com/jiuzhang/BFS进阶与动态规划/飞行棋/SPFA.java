package com.jiuzhang.BFS进阶与动态规划.飞行棋;

import java.util.*;



public class SPFA {
    // SPFA
    // 和BFS类似，需要queue，但是入queue的策略有变化：
    // 入队：入队的是“找到了到达这个node的最短路径”的node，并准备用这个最短路径去更新node的邻居们。
    //  所以 BFS会入队unvisited neighbors，因为认为找到了他们的最短路径
    //  所以 SPFA除了unvisited，还会入队所有的找到了更短distance的node。

    // 用SPFA的原因，是哪些connection其实相当于是 weight为0的edge，所以把这个图变成了复杂图，不再是简单图

    // 但是这样每次都拿队首就不太划算了，不如每次拿queue里面distance最小的那个，省得费劲更新了一波后又被覆盖了。
    // 所以用PriorityQueue

    public static void main(String[] args) {
        int[][] connections = new int[][]{{7, 9}, {8, 14}};
        // 1->7(9)
        // 9->15
        int length = 15;
        System.out.println(modernLudo(length, connections));
    }

    static class Cell {
        int id;
        int distance;

        public Cell(int node, int distance) {
            this.id = node;
            this.distance = distance;
        }
    }

    public static int modernLudo(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);
        Map<Integer, Integer> distance = new HashMap<>();
        PriorityQueue<Cell> queue = new PriorityQueue<>((a, b) -> a.distance - b.distance);

        // 很重要的一步，把distance用最大值填满
        for (int i = 1; i <= length; i++) {
            distance.put(i, Integer.MAX_VALUE);
        }

        distance.put(1, 0);
        queue.offer(new Cell(1, 0));

        while (!queue.isEmpty()) {
            Cell node = queue.poll();
            // weight 1
            int limit = Math.min(node.id + 6, length);
            for (int nei = node.id + 1; nei <= limit; nei++) {
                if (distance.get(nei) > node.distance + 1) {
                    distance.put(nei, node.distance + 1);
                    queue.offer(new Cell(nei, node.distance + 1));
                }
            }
            // weight 0
            for (int nei : graph.get(node.id)) {
                if (distance.get(nei) > node.distance) {
                    distance.put(nei, node.distance);
                    queue.offer(new Cell(nei, node.distance));
                }
            }
        }

        return distance.get(length);
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
