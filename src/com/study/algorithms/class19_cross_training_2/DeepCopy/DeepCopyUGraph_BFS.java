package com.study.algorithms.class19_cross_training_2.DeepCopy;

import java.util.*;

public class DeepCopyUGraph_BFS {
    // BFS: O(n) space, O(n) time

    // Deep copy undirected graph
    class GraphNode {
        public int key;
        public List<GraphNode> neighbors;

        public GraphNode(int key) {  // 记得写constructor
            this.key = key;
            this.neighbors = new ArrayList<GraphNode>();
        }
    }

    public List<GraphNode> copy(List<GraphNode> graph) {
        if (graph == null) {
            return graph;
        }
        // there are many graphs:
        List<GraphNode> res = new ArrayList<>();
        Map<GraphNode, GraphNode> prvToNew = new HashMap<>();
        for (GraphNode node : graph) {
            prvToNew.put(node, new GraphNode(node.key)); // prvToNew存储全局的映射。

            Queue<GraphNode> queue = new ArrayDeque<>();
            queue.offer(node); // all nodes here has been copied
            while (!queue.isEmpty()) {
                // try to build relationship to neighbors
                GraphNode curPrv = queue.poll();
                GraphNode curNew = prvToNew.get(curPrv);

                for (GraphNode neighbor : curPrv.neighbors) {
                    if (!prvToNew.containsKey(neighbor)) {
                        prvToNew.put(neighbor, new GraphNode(neighbor.key));
                    }
                    curNew.neighbors.add(prvToNew.get(neighbor));
                }

            }
            res.add(prvToNew.get(node));
        }
        return res;
    }
}
