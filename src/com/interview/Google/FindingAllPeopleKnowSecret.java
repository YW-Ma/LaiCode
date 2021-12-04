package com.interview.Google;

import java.util.*;

public class FindingAllPeopleKnowSecret {
    public static void main(String[] args) {
        SolutionToFindingAllPeopleKnowSecret s = new SolutionToFindingAllPeopleKnowSecret();
        int[][] meetings = {{1,2,2}, {0,3,3}, {3,1,3}};
        List<Integer> res = s.findAllPeople(4, meetings,3);
        System.out.println(res);
    }
}

class SolutionToFindingAllPeopleKnowSecret {
    // 这个思路是错的，不是只保留最小time就行了。 因为可能 a->b 在较早时间、较晚时间各发生一次meeting，而且较早时a还不知道message
    // 那么如果只保留最小time，就会认为b不知道message了
    
    // 正确思路:
    // 1. 先排序所有的meeting，保证meeting按顺序发生
    // 2. 每个meeting看成一个indirected edge
    // 3. 在每一段时间内，进行一次BFS
    
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // Non-Directed Graph
        // A node can spread info to all nodes whose time >= this node's' time.
        
        // step 1: convert meetings to a graph
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        HashMap<Integer, List<Integer>> edges = new HashMap<>();
        boolean[] knowSecret = new boolean[n];
        knowSecret[0] = true;
        knowSecret[firstPerson] = true;
        
        int i = 0;
        while (i < meetings.length) {
            // for the meetings happen at the same time, do BFS
            int j = i;
            while (j < meetings.length && meetings[j][2] == meetings[i][2]) {
                j++;
            }
            // [i, j) has the same time
            edges.clear();
            Queue<Integer> q = new ArrayDeque<>();
            // initial state
            for (int k = i; k < j; k++) {
                int x = meetings[k][0];
                int y = meetings[k][1]; // two person
                
                List<Integer> list = edges.getOrDefault(x, new ArrayList<>());
                list.add(y);
                edges.put(x, list);
                
                list = edges.getOrDefault(y, new ArrayList<>());
                list.add(x);
                edges.put(y, list);
            }
    
            // only push the one who "knew" the secret into queue.
            for (int x : edges.keySet()) {
                if (knowSecret[x]) {
                    q.offer(x);
                }
            }
            
            // BFS
            while (!q.isEmpty()) {
                int curId = q.poll();
                for (int nei : edges.get(curId)) {
                    if (!knowSecret[nei]) {
                        knowSecret[nei] = true;
                        q.offer(nei);
                    }
                }
            }
            
            // skip the processed part
            i = j;
        }
        
        List<Integer> res = new ArrayList<>();
        for (int idx = 0; idx < knowSecret.length; idx++) {
            if (knowSecret[idx]) {
                res.add(idx);
            }
        }
        return res;
    }
}