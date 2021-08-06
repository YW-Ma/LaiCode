package com.study.algorithms.class07_Heap_and_Graph_Search.重听;

import java.util.*;
import com.study.util.GraphNode;

public class Bipartite {
    public boolean isBipartite(List<GraphNode> graph) { // 发现graph可以不连通，我之前的算法是针对全联通的图设计的，现在要修改。
        Queue<GraphNode> queue = new ArrayDeque<>();  // 负责BST的基本任务。
        Map<GraphNode, Boolean> nodeToSet = new HashMap<>(); // 负责记录set，并且负责BST的去重。

        for (GraphNode graphNode : graph) { // 需要从每个graphNode出发尝试一次。因为可能不是全可达的。
            if (nodeToSet.containsKey(graphNode)) {
                continue;
            }

            queue.offer(graphNode);
            nodeToSet.put(graphNode, true);

            while (!queue.isEmpty()) {
                // expand, get the current set value
                GraphNode cur = queue.poll();
                if (!nodeToSet.containsKey(cur)) {
                    nodeToSet.put(cur, true);
                }
                boolean curSet = nodeToSet.get(cur);
                boolean neiSet = !curSet;

                // generate, invert current set value
                for (GraphNode nei : cur.neighbors) {
                    if (!nodeToSet.containsKey(nei)) {
                        nodeToSet.put(nei, neiSet);
                        queue.offer(nei); // 以前加入过的，会出现在nodeToSet里面。 为了去重，不应该再加入一次。 因此offer应该放置在分支里，而不是每个for循环结尾。
                    } else if (nodeToSet.get(nei) != neiSet) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    // Time: Each vertex is visited once and each edge twice，O(|V| + 2|E|)，（注意，|V|最大是|E|+1）
    // Space: taken by map and queue, which stores vertices.  O(|V|)
}
