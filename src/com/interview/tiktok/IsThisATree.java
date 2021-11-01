package com.interview.tiktok;

/*
*
* 输入例如(A, B)(B, C)(D, E)...,问能不能构成tree， --> 这输入应该是描述了单向边
如果可以，请输出(A(B(C)(D))(E(F)))这种格式；
如果不行，返回error。

这里它给了五种error，要分别判断，
其中有：duplicated edges，cycle in the tree，more than two child nodes, more than one root， 其他error。
* */


import java.util.HashSet;
import java.util.Set;

public class IsThisATree {
    public String constructExpression(String s) {
        boolean[][] graph = new boolean[26][26]; // 26个字母的graph。其实也可以改成两个map
        Set<Character> set = new HashSet<>(); // 存储都遇到过哪些node
        boolean E2 = false;
        int numOfEdges = 0;
        for (int i = 1; i < s.length(); i += 6) {
            // 6 letters是一个tuple
            int x = s.charAt(i) - 'A';
            int y = s.charAt(i + 2) - 'A';
            if (graph[x][y]) {
                return "E2"; // E2, duplicate edges
            }
            graph[x][y] = true;
            set.add(s.charAt(i));
            set.add(s.charAt(i + 2));
            numOfEdges++;
        }

        boolean E1 = false;
        for (int i = 0; i < 26; i++) {
            int count = 0;
            for (int j = 0; j < 26; j++) {
                if (graph[i][j]) {
                    count++;
                }
            }
            if (count > 2) {
                return "E1"; // E1 more than two child nodes
            }
        }

        // 检查是不是成环 cycle in the tree 或者 more than one root
        int numOfRoots = 0;
        char root = ' ';
        System.out.println(set); // 把所有node都打印出来？ 估计是为了调试
        for (Character c : set) {
            boolean isRoot = true;
            for (int i = 0; i < 26; i++) {
                // 对每一个node，如果能找到一个到它的edge，那么它不是root
                if (graph[i][c - 'A']) {
                    isRoot = false;
                    break;
                }
            }
            if (isRoot) {
                numOfRoots++;
                boolean[] visited = new boolean[26];
                if (hasCycle(c, graph, visited)) {
                    return "E3"; // 如果找到cycle了就返回E3.
                }
                root = c;
            }
        }
        if (numOfRoots == 0) {
            return "E3"; // 如果没有root，那肯定也是成环了。
        }
        if (numOfRoots > 1) {
            return "E4"; // 超过一个root了
        }
        // 余下的情况，可以放心构建tree表达式：
        return getSexpression(root, graph);
    }

    // 如果在DFS的过程中又走回来了，说明是true，有cycle。 如果某层发现所有的孩子都走不回去，就返回false。
    private boolean hasCycle(char root, boolean[][] graph, boolean[] visited) {
        // recursion detection：
        // base case:
        if (visited[root - 'A']) {
            return true;
        }
        // recursion rule:
        visited[root - 'A'] = true;
        for (int i = 0; i < 26; i++) {
            if (graph[root - 'A'][i]) {
                if (hasCycle((char)('A' + i), graph, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    // getExpression: 也是通过DFS来构建的
    private String getSexpression(char root, boolean[][] graph) {
        StringBuilder res = new StringBuilder();
        res.append("(" + root);
        // 对自己的左右孩子分别call这个函数，把结果拼接起来
        boolean isRight = false;
        for (int i = 0; i < 26; i++) {
            if (graph[root - 'A'][i]) {
                res.append(getSexpression((char)('A' + i), graph));
            }
        }
        return res.append(")").toString();
    }
}
