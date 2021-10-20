package com.interview.walmartlabs.leetcode;

import com.study.util.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryTreeSerializeAndDeserialize {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public class Codec {
        
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            preOrder(root, sb);
            return sb.toString();
        }
        
        private void preOrder(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("X,");
                return;
            }
            sb.append(root.val).append(",");
            preOrder(root.left, sb);
            preOrder(root.right, sb);
            return;
        }
        
        
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] data_array = data.split(","); // get each string
            List<String> array = new ArrayList<String>(Arrays.asList(data_array));
            return reconstruct(array);
        }
        
        private TreeNode reconstruct(List<String> array) {
            if (array.size() == 0) {
                return null;
            }
            if (array.get(0).equals("X")) {
                array.remove(0);
                return null;
            }
            int val = Integer.valueOf(array.get(0));
            array.remove(0); // delete the visited one
            TreeNode root = new TreeNode(val);
            root.left = reconstruct(array);
            root.right = reconstruct(array);
            return root;
        }
    }
}
