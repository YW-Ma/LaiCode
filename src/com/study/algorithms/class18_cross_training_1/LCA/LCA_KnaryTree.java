package com.study.algorithms.class18_cross_training_1.LCA;

import java.util.ArrayList;
import java.util.List;

public class LCA_KnaryTree {
  // 和LCA的物理意义一样，返回in the subtree rooted at root, the LCA of a and b

  // 重听，用重听笔记：
  class KnaryTreeNode {
    int key;
    List<KnaryTreeNode> children;
    KnaryTreeNode(int key) {
      this.key = key;
      this.children = new ArrayList<>();
    }
  }
  // must have all nodes
  // return the lca

  //     root
  //    / |||  \
  //   b1      bn
  //
  // what I expect from my child: tell me a LCA candidate (LCA or one of target, or null)
  // what I do: find out a LCA candidate
  // case 1: I am one of target nodes --> return myself
  // case 2: if got more than one --> return myself --> use a "found" to record if necessary
  // case 3: if only one child found --> pass it (target or LCA)
  // case 4: all children return null --> return null

  // Time: O(n) n is the #of nodes in tree
  // Space: O(height) --> space on stack

  // input:  root, Node a, Node b
  // output: LCA TreeNode
  public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b) {
    if (root == null || root == a || root == b) { // case 1
      return root;
    }
    KnaryTreeNode found = null;
    for (KnaryTreeNode child : root.children) {
      KnaryTreeNode node = lowestCommonAncestor(child, a, b);
      if (node == null) {
        continue;
      }
      if (found == null) { // found one
        found = node;
        continue;
      } else { // found both -->case 2
        return root;
      }
    }
    return found; // case 3, 4
  }
}
