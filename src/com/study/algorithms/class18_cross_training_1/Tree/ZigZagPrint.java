package com.study.algorithms.class18_cross_training_1.Tree;

import com.study.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ZigZagPrint {
  // 保留queue的物理意义不变：
  //    while循环每层一次，while循环进入的时候queue一定从左到右维护了当前level所有节点。
  // 改变顺序：
  //    偶数层先expand右边的，存到一个Deque里面。
  //    从queue的另一头塞入。（Deque）
  // 奇数层：从左边expand当前层size次，generate的时候从右边入queue，而且先generate right child。
  // 偶数层：从右边expand当前层size次，generate的时候从左边入queue，而且先generate left child。

    public List<Integer> zigZag(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerFirst(root);
        boolean isEven = true;

        while (!deque.isEmpty()) {
            int size = deque.size();
            if (isEven) {
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.pollLast();
                    res.add(node.key); // node必然非null
                    // 防止NPE，只有非null才放进去
                    if (node.right != null) {
                        deque.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.offerFirst(node.left);
                    }
                }
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.pollFirst();
                    res.add(node.key);
                    if (node.left != null) {
                        deque.offerLast(node.left);
                    }
                    if (node.right != null) {
                        deque.offerLast(node.right);
                    }
                }
            }
            // 记得奇偶数交替
            isEven = !isEven;
        }
        return res;
    }
}

/*
To enable screen reader support, press Ctrl+Alt+Z To learn about keyboard shortcuts, press Ctrl+slash
Lowest Common Ancestor Of M Nodes In A K-nary Tree
Given M nodes in a K-nary tree, find their lowest common ancestor.

https://app.laicode.io/app/problem/648

public class LCAVI {
  static class KnaryTreeNode {
    int key;
    List<KnaryTreeNode> children;

    public KnaryTreeNode(int key) {
      this.key = key;
      this.children = new ArrayList<>();
    }
  }

  // TC: O(n)
  // SC: O(h + m)
  public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, List<KnaryTreeNode>
nodes) {
    // Assumptions: the list of nodes is not null or not empty,
    // all the nodes in the list are guaranteed to be in the tree.
    Set<KnaryTreeNode> set = new HashSet<KnaryTreeNode>(nodes);
    return helper(root, set);
  }

  private KnaryTreeNode helper(KnaryTreeNode root, Set<KnaryTreeNode> set) {
    if (root == null || set.contains(root)) {
      return root;
    }
    KnaryTreeNode found = null;
    for (KnaryTreeNode child : root.children) {
      KnaryTreeNode node = helper(child, set);
      if (node == null) {
        continue;
      }
      if (found == null) {
        found = node;
      } else {
        return root;
      }
    }
    return found;
  }
}

Lowest Common Ancestor Of M Nodes In A K-nary Tree
Turn on screen reader support

Process finished with exit code 0

* */
