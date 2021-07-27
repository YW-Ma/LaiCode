package com.study.algorithms.final2;

import com.study.util.TreeNode;


// 难点： a是b（或者b是a）的母节点的情况。

// 做法1：记录一下是否遇到了a、b。 如果都遇到了，那么才认为获得了distance。
//       如果只遇到a，那么以a为root。进行 b、b 的LCA一样的模式搜索。 获得一个distance。返回
//       这和"a,b不一定在tree里"的那个题目同理。

// 做法2：先找到LCA，然后再找距离。分两个阶段做。


public class Distance {
    class Resp {
        TreeNode node;
        int depth;
        int distance;
        boolean aFlag;
        boolean bFlag;
        
        public Resp(TreeNode n, int i) {
            this.node = n;
            this.depth = i;
        }
    }
    
    public int getDistance(TreeNode root, TreeNode a, TreeNode b) { // assume a and b in tree and not null;
        Resp resp = helper(root, a, b);
        
        if (resp.aFlag && resp.bFlag) {
            return resp.distance;
        } else if (resp.aFlag) {
            return helper(a, b, b).distance;
        }
        return helper(b, a, a).distance;
    }
    
    private Resp helper(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return new Resp(null, 0);
        }
        if (root == a || root == b) {
            Resp resp = new Resp(root, 0);
            if (root == a) {
                resp.aFlag = true;
            } else {
                resp.bFlag = true;
            }
            return resp;
        }
        
        Resp left = helper(root.left, a, b);
        Resp right = helper(root.right, a, b);
        
        if (left.node == null && right.node == null) {
            return new Resp(null, 0);
        }
        if (left.node == null) {
            right.depth++;
            return right;
        }
        if (right.node == null) {
            left.depth++;
            return left;
        }
        
        Resp res = new Resp(root, left.depth + 1);
        res.distance = left.depth + right.depth + 2;
        if (left.aFlag || right.aFlag) {
            res.aFlag = true;
        }
        if (left.bFlag || right.bFlag) {
            res.bFlag = true;
        }
        return res;
    }
}
