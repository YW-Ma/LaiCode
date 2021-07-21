package com.study.algorithms.class23_graph_search_3.重听.BFS1;

/*"430 Course Schedule II
Request edit access


Share
FileEditViewToolsHelp
To enable screen reader support, press ⌘+Option+Z To learn about keyboard shortcuts, press ⌘slash
Course Schedule II
Given n courses and the prerequisites of each course, find a valid order to take all the courses.
https://app.laicode.io/app/problem/430*/

import java.util.*;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // the adjacency list representation of prerequisites.
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] dependency : prerequisites) {
            int x = dependency[0];
            int y = dependency[1];
            graph.get(y).add(x);
        }
        
        return topologicalSort(graph);
    }
    
    private int[] topologicalSort(List<List<Integer>> graph) {
        int numCourses = graph.size();
        int[] topologicalOrder = new int[numCourses];
        int[] incomingEdges = new int[numCourses];
        for (int x = 0; x < numCourses; x++) {
            for (int y : graph.get(x)) {
                incomingEdges[y]++;
            }
        }
        
        // nodes with no incoming edges.
        Queue<Integer> queue = new ArrayDeque<>();
        for (int x = 0; x < numCourses; x++) {
            if (incomingEdges[x] == 0) {
                queue.offer(x);
            }
        }
        
        int numExpanded = 0;
        while (!queue.isEmpty()) {
            int x = queue.poll();
            topologicalOrder[numExpanded++] = x;
            for (int y : graph.get(x)) {
                if (--incomingEdges[y] == 0) {
                    queue.offer(y);
                }
            }
        }
        return numExpanded == numCourses ? topologicalOrder : new int[]{};
    }
}
/*
Time: O(V+E)
Space:  O(V+E)
Course Schedule II
Turn on screen reader support"
*/
