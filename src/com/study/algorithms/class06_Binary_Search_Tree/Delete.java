package com.study.algorithms.class06_Binary_Search_Tree;

import com.study.util.TreeNode;

public class Delete {
    // TC: O(height)
    public TreeNode deleteTree(TreeNode root, int val) { // return the updated root.
        // corner case:
        if (root == null) {
            return null;
        }

        if (root.key > val) {
            root.left = deleteTree(root.left, val);
        } else if (root.key < val) {
            root.right = deleteTree(root.right, val);
        } else {
            if (root.left == null && root.right == null) {
                return null; // delete this node
            } else if (root.right != null) { // right exists, prefer the smallest in right
                TreeNode smallest = pickSmallest(root.right, root);
                smallest.right = root.right;
                smallest.left = root.left;
                return smallest;
            } else { // 其实这个没有必要这么复杂，走到这里说明right == null. 那么直接把左边顶上来即可。
                // TreeNode largest = pickLargest(root.left);
                // largest.left = removeLargest(root.left);
                // return largest;
                return root.left;
            }
        }
        return root;
    }

    private TreeNode pickSmallest(TreeNode cur, TreeNode prv) { // 注意要把smallest拽下来。即需要考虑 smallest.right != null的情况。
        while (cur.left != null) {
            prv = cur;
            cur = cur.left;
        }
        // now cur.left == null, but cur.right can be not null
        if (cur.right == null) {
            if (prv.left == cur) {
                prv.left = null;
            } else {
                prv.right = null;
            }
            return cur;
        }

        // if cur.right is not null
        if (prv.left == cur) {
            prv.left = cur.right;
        } else {
            prv.right = cur.right;
        }
        return cur;
    }
}

/*
* ## Delete
[Delete](./Delete.java)
```
core: return the updated root.
```

Algorithm

- If `key > root.val` then delete the node to delete is in the right subtree `root.right = deleteNode(root.right, key)`.
- If `key < root.val` then delete the node to delete is in the left subtree `root.left = deleteNode(root.left, key)`.
- If `key == root.val` then the node to delete is right here. Let's do it :
    - If the node is a `leaf`, the delete process is straightforward : root = null.
    - If the node is not a leaf and `has the right child`, then `cherry-pick the smallest one in right subtree`, and update root by it. `return newRoot`
    - If the node is not a leaf and `has only the left child`, then `return root.left`.
Return root.

参考[laioffer答案](https://docs.google.com/document/d/1Qimmqsz4we-YM88nSVKazlcxomsLXXOZrNdqzigExEM/edit)
```
上述的 has the right child 的情况，实际上是可以分为下述两个情况的：
1. smallest是叶子 - 相安无事。
2. smallest（6）还有个smallest.right存在（7）
            5           target = 5.
           / \
          3    6        <-- 找到tar后，把右子树的smallest更新上去作为新root。
        / \    / \
       2  4   #   7
上述例子中，会找到右侧的smallest即6
如果使用：5变6，然后在新root的右子树中把smallest删掉，就会出问题。
删掉6会把7丢失掉。

正确的做法是，如果发现 smallest.right != null, 就把它接到smallest本来该在的位置上。
在helper function里面分两类情况后处理，就可以实现这个目的。
```
* */
