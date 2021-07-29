package com.study.algorithms.class19_cross_training_2.DeepCopy;

import java.util.*;

public class DeepCopyUGraph_DFS {
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
            return null;
        }

        List<GraphNode> res = new ArrayList<>();
        Map<GraphNode, GraphNode> prevToNew = new HashMap<>();
        for (GraphNode node : graph) {
            res.add(dfs(node, prevToNew));
        }
        return res;
    }

    // DFS: get the prev node, return the copied node.
    private GraphNode dfs(GraphNode node, Map<GraphNode, GraphNode> prevToNew) {
        if (prevToNew.containsKey(node)) {
            return prevToNew.get(node);
        }

        // copy:
        GraphNode newNode = new GraphNode(node.key);
        prevToNew.put(node, newNode);

        // build relationship to neighbors:
        for (GraphNode neighbor : node.neighbors) {
            newNode.neighbors.add(dfs(neighbor, prevToNew));
        }
        return newNode;
    }
}
