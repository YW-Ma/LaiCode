package com.study.algorithms.class23_graph_search_3.重听.BFS1;

import java.util.*;
/*
* Time Complexity:O(E + V)
* since for each popped V we iterate over all its neighbours and sum up to be E edges.
*
* Space Complexity: O(E + V)
* we store a adjacency list (map).
* */

public class CourseScheduleIINew {
    // get 2, [[1,0]]
    // output [0,1]
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // initialize the graph, value is the courses dependent on the key. (一开始把关系写的反的，但是发现不好更新，因为那样每次修一门课需要修改多个entry了）
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> courseMap = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> courseList = courseMap.getOrDefault(src, new ArrayList<Integer>());
            courseList.add(dest);
            courseMap.put(src, courseList);
            inDegree[dest]++;
        }

        // BFS starting with 0 In Degree node
        // find the 0 in-degree node
        Queue<Integer> q = new ArrayDeque<>();
        int[] scheduler = new int[numCourses];
        int courseSelectedCounter = 0;
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                scheduler[courseSelectedCounter] = i;
                courseSelectedCounter++;
            }
        }


        while (!q.isEmpty()) {
            int courseId = q.poll();
            if (courseMap.containsKey(courseId)) { // 有的课程可能根本不存在于里面，即那些没有被作为prerequisite的课程。
                for (int i : courseMap.get(courseId)) {
                    // update value or change in degree
                    inDegree[i]--;
                    if (inDegree[i] == 0) {
                        q.offer(i);
                        scheduler[courseSelectedCounter] = i;
                        courseSelectedCounter++;
                    }
                }
            }
        }
        if (courseSelectedCounter == numCourses) {
            return scheduler;
        }
        return new int[0];
    }
}
