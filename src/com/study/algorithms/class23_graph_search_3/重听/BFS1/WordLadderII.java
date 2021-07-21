package com.study.algorithms.class23_graph_search_3.重听.BFS1;

//"662 Word Ladder II
//Request edit access
//
//
//Share
//FileEditViewToolsHelp
//To enable screen reader support, press ⌘+Option+Z To learn about keyboard shortcuts, press ⌘slash
//Word Ladder II
//Transform a given word into another given word in the fewest steps. In each step, you are allowed to
//change only one letter and the resultant word must be in the input dictionary.
//https://app.laicode.io/app/problem/662


/**
 * - 与edit distance不同，只能有一个操作（更换一个字母，且更换后需要在wordList里面）
    - 求最少步数 --> 要么DP、要么BFS1. 优先BFS1试试
        - 点：一个word
        - 边：一个可行变化，如何efficiently find neighbors？
            - 低效找邻居方案：把所有单词都撸一遍，看不同的字母是否只差1个。每次expand的时候需要 O(n)*O(wordLength)。最后导致O(n^2 * wordLength)
            - (优秀方法)：生成出所有一次可达的单词，然后看这些结果是否在wordList里面。由于只有26个字母，所以是 O(26 * wordLength) --> O(wordLength)
        
    - 需要HashMap判重+记录steps
 
 开销： O(V * 26*L * L)
           各情况   求hashCode
 V是num of words
 L是length
 
 - FollowUps: 如何把ladder打印出来呢？
    - 把 parent记录下来，知道是从谁generate出来的。
    - 可以写成一个HashMap 叫 predecessor
    - 凡是BFS还需要路径，那么就用这个方案
 
 - FollowUpsII: 如何把所有的shortest wordLadder打印出来（可能有多个最短path）
 */

import java.util.*;

public class WordLadderII {
    static class NeighborFinder {
        public NeighborFinder(List<String> words) {
            for (int i = 0; i < words.size(); i++) {
                String word = words.get(i);
                wordIndex.put(word, i);
            }
            this.words = words;
        }
        
        public List<Integer> findNeighbors(int i) {
            List<Integer> neighbors = new ArrayList<>(16);
            String word = words.get(i);
            StringBuilder wordModifier = new StringBuilder(word);
            for (int j = 0; j < wordModifier.length(); j++) {
                char orig = word.charAt(j);
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == orig) {
                        continue;
                    }
                    wordModifier.setCharAt(j, c);
                    int neighbor = wordIndex.getOrDefault(wordModifier.toString(), -1);
                    if (neighbor != -1) {
                        neighbors.add(neighbor);
                    }
                }
                wordModifier.setCharAt(j, orig);
            }
            return neighbors;
        }
        
        private Map<String, Integer> wordIndex = new HashMap<>();
        private List<String> words;
    }
    
    static class Tracer {
        public Tracer(List<String> words) {
            this.words = words;
            
            preds = new ArrayList<>(words.size());
            for (int i = 0; i < words.size(); i++) {
                preds.add(new ArrayList<>(16));
            }
        }
        
        public void addPredecessor(int x, int y) {
            preds.get(y).add(x);
        }
        
        public List<List<String>> findLadders(int beginIndex, int y) {
            List<List<String>> ladders = new ArrayList<>();
            List<String> trace = new ArrayList<>();
            trace.add(words.get(y));
            findLaddersHelper(beginIndex, y, trace, ladders);
            return ladders;
        }
        
        private void findLaddersHelper(int beginIndex, int y, List<String> trace,
                                       List<List<String>> ladders) {
            if (beginIndex == y) {
                List<String> ladder = new ArrayList<>(trace);
                Collections.reverse(ladder);
                ladders.add(ladder);
                return;
            }
            for (int x : preds.get(y)) {
                trace.add(words.get(x));
                findLaddersHelper(beginIndex, x, trace, ladders);
                trace.remove(trace.size() - 1);
            }
        }
        
        private List<String> words;
        private List<List<Integer>> preds;
    }
    
    public List<List<String>> findLadders(String beginWord, String endWord,
                                          List<String> wordList) {
        int endIndex = wordList.indexOf(endWord);
        if (endIndex == -1) {
            return new ArrayList<>();
        }
        
        // Pre-process Input: build an ArrayList word list  (if .indexOf method fail, then we cannot use input wordlist directoly)
        List<String> words;
        int beginIndex = wordList.indexOf(beginWord);
        if (beginIndex == -1) {
            // the input wordList might not be appendable (e.g., the result of Arrays.asList).
            words = new ArrayList<String>(wordList);
            words.add(beginWord);
            beginIndex = words.size() - 1;
        } else {
            words = wordList;
        }
        
        // Step 1: prepare NeighborFinder --> use a HashMap to record all possible target, each call will try 'a'-'z' convert for each letter.
        NeighborFinder finder = new NeighborFinder(words);
        
        // Step 2: BFS (explore the graph layer by layer)
        Queue<Integer> queue = new ArrayDeque<>();
        int[] step = new int[words.size()];
        Arrays.fill(step, -1);
        
        queue.offer(beginIndex);
        step[beginIndex] = 0;
        Tracer tracer = new Tracer(words);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            if (x == endIndex) {
                return tracer.findLadders(beginIndex, endIndex);
            }
            for (int y : finder.findNeighbors(x)) { // find neighbors for nodes in queue
                if (step[y] == -1) {
                    queue.offer(y);
                    step[y] = step[x] + 1;
                }
                if (step[x] + 1 == step[y]) {
                    tracer.addPredecessor(x, y); // the tracer is set to record the path.
                }
            }
        }
        return new ArrayList<>();
    }
}

//Time Complexity: O(E+V) // V is number of words, E can be at most V^2
//Space Complexity: O(E+V)
//Word Ladder II
//Turn on screen reader support"
