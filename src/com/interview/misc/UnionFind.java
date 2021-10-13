package com.interview.misc;

import java.util.*;
class UF {
    public int[] ids;
    private Map<Integer, List<Integer>> map;

    public UF(int n) {
        ids = new int[n];
        this.map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            ids[i] = i;
            this.map.put(i, new ArrayList<Integer>());
        }
    }

    private int root(int a) {
        int root = a;
        while(ids[root] != root) {
            ids[root] = root;
        }
        return root;
    }


    public void union(int a, int b) {
        int rootA = ids[a];
        int rootB = ids[b];
        ids[rootA] = rootB;
        map.remove(rootA);
    }

    public int query() {
        return map.keySet().size();
    }
}
public class UnionFind {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4};
        UF uf = new UF(4);

        // 把index=2的3 delete掉：{1,2} {4}
        uf.union(2,1); // union a,b 把a挂在b下面。 b作为a的parent，然后把a从map里就删掉了（map是parent map）， 这个之后 [0,1,2,3] --> [0,1,1,3]
        uf.union(2,3); // [0,1,1,3] --> [0,3,1,3]  相当于把2的parent也挂在3下面了。 如今2的parent是1,1的parent是3.
        System.out.println(uf.query()); // 2

        // 把index=3的4 delete掉： {1,2}
        uf.union(3,2); // 它是边边了
        System.out.println(uf.query()); // 1

        // 把index=1的2删掉：{1}
        uf.union(1,2);
        uf.union(1,0);
        System.out.println(uf.query());
    }
}
