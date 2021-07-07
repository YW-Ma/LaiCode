package com.study.algorithms.class18_cross_training_1.LCA;

import java.util.*;

public class LCA_MNodesKnaryTree {
//  Lowest Common Ancestor Of M Nodes In A K-nary Tree
//  Given M nodes in a K-nary tree, find their lowest common ancestor.
//
//  https://app.laicode.io/app/problem/648

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

  static class KnaryTreeNode {
    int key;
    List<KnaryTreeNode> children;

    public KnaryTreeNode(int key) {
      this.key = key;
      this.children = new ArrayList<>();
    }
  }
}

