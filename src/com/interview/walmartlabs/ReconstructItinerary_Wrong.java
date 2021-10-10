package com.interview.walmartlabs;

import java.util.*;
import java.util.Map.Entry;

public class ReconstructItinerary_Wrong {
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();   // 我这个解法是错的。如果是这个例子，会直接走到AAA，然后就死在那里了。
        tickets.add(List.of("JFK","AAA"));
        tickets.add(List.of("JFK","BBB"));
        tickets.add(List.of("BBB","CCC"));
        tickets.add(List.of("CCC","JFK"));

        List<String> res = findItinerary(tickets);
        System.out.println(res);
    }

    // 两个问题：
    // 1. 没看清题，实际上始终从JFK出发，不需要找入度最小的点。
    // 2. 逻辑有问题，如果出现Solution中那种， JFK --> AAA 然后死路一条的，则会掉进去。


    public static List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        if (tickets == null || tickets.size() == 0) {
            return res;
        }

        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        // 重点是怎么找到in-degree最小的那个值。用HashMap加入to的话，小心漏掉入度为0的（因为不在to里面）
        for (List<String> jump : tickets) { // 这样写可以吗？
            String from = jump.get(0);
            String to = jump.get(1);
            // 准备好空间（一定要都准备好，否则有漏掉出入度为0的node的情况）
            if (!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            if (!map.containsKey(to)) {
                map.put(to, new ArrayList<>());
            }
            if (!inDegree.containsKey(from)) {
                inDegree.put(from,0);
            }
            if (!inDegree.containsKey(to)) {
                inDegree.put(to, 0);
            }

            // 放入from
            List<String> tos = map.get(from);
            tos.add(to);
            map.put(from, tos); // List的add返回值竟然不是自己，而是一个boolean。。。。所以要先拆出来加入了才行
            // 放入to
            inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
        }

        // 由于要lexical order，如果两个value一样，就按照key来比较 --> 但是发现答案不是这样的。他们的开始点不一定是最小的那个。 很奇怪。
        Map.Entry<String, Integer> min = Collections.min(inDegree.entrySet(), new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1,  Map.Entry<String, Integer> e2) {
                if (e1.getValue().compareTo(e2.getValue()) != 0) {
                    return e1.getValue().compareTo(e2.getValue());
                }
                return e1.getKey().compareTo(e2.getKey());
            }
        });


        String prev = min.getKey();
        res.add(prev);
        while (map.get(prev).size() != 0) {
            List<String> nexts = map.get(prev);
            // GET THE STRING WITH SMALLER LEXICAL ORDER
            String next = nexts.get(0);
            int idx = 0;
            for (int i = 1; i < nexts.size(); i++) {
                if (next.compareTo(nexts.get(i)) > 0) {
                    next = nexts.get(i);
                    idx = i;
                }
            }
            nexts.remove(idx);
            res.add(next);
            prev = next;
        }
        return res;
    }
}
