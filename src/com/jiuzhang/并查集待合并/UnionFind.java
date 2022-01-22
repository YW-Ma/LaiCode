package com.jiuzhang.并查集待合并;

import java.util.HashMap;

public class UnionFind {
    // fields:
    HashMap<Integer, Integer> fathers = new HashMap<>();
    HashMap<Integer, Integer> sizeOfSet = new HashMap<>();
    int numOfSet = 0;
    
    // add
    public void add(int x) {
        if (fathers.containsKey(x)) {
            return;
        }
        fathers.put(x, null);
        numOfSet++;
        sizeOfSet.put(x, 1);
    }
    
    // find
    // 携带路径压缩
    public int find(int x) {
        // find the root
        int root = x;
        while (fathers.get(root) != null) {
            root = fathers.get(root);
        }
        // update the node in the path
        int cur = x;
        while (cur != root) {
            int originalFather = fathers.get(cur);
            fathers.put(cur, root);
//            cur = fathers.get(cur); // 不可以直接移动，这样会走到"新的" father，而不是原来路径上的father了
            cur = originalFather;
        }
        // return root
        return root;
    }
    
    // merge
    public void merge(int x, int y) {
        // find their root (set)
        int rootX = find(x);
        int rootY = find(y);
        if (rootY == rootX) {
            return;
        }
        // merge
        fathers.put(rootY, rootX); // Y挂在X下面
        numOfSet--;
        sizeOfSet.put(rootX, sizeOfSet.get(rootY) + sizeOfSet.get(rootX));
    }
}
