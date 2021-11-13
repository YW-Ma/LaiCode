package com.interview.bloomberg;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAllAdjacentDuplicates {
    
    // given a string s, an integer k
    // choosing k adjacent and equal letters from s, and remove them
    // concat the rest part.

    //    Input: s = "deeedbbcccbdaa", k = 3
    //    Output: "aa" 因为删掉eee,ccc后，发现bbb可以删掉，删掉后又发现ddd可以删掉
    
    // 之前做过两个两个删除的（用reader/writer ptr），还做过两个两个反复删除的（用stack或者多走几遍）
    // 这里也是类似后者，要反复删除，
    // Sol 1: Brute Force --> loop through O(n/k) times, O(n) * O(n/k) = O(n^2/k)
    // Sol 2: Stack or two pointer
    
    // stack:
    //   首先看继承以前思想的：
    //      1. 一个StringBuilder（可以用原字符串代替，变成two-pointer）
    //      2. 一个counts stack, 逻辑和下面相同，但是只要不到k就记得加入StringBuilder
    //      3. StringBuilder在counts弹栈的时候，删掉 [i-k+1, i+1) 这部分
    //   和以前的不同，这次搞两个stack，一个是counter，一个是element （也可以作为Cell一起存）
    //      1. 如果当前字符和上一个不同，counter压栈1，element压栈新元素
    //      2. 如果当前字符和上一个相同，栈顶+1， 实际上需要poll, +1, offer 三个步骤
    //      3. 如果栈顶达到了k，就把这个frame不要了（两个栈都要操作）
    //      4. 最后在stringBuilder把这些浮现出来。 由于是stack所以append完了还要reverse()才能toString()
    
    // two-pointers:
    //   优化掉字符stack O(n), 并可以实现原地操作但是要O(nk)
    //   方案1：reader, writer
    //          每次把reader的字符复制到writer。
    //          如果 writer发现：新字符（则stack压1），和上一个一样（stack顶+1）
    //          当栈顶在操作后是k，则弹栈，writer也后减去k以后退。（相当于删掉了元素）
    
    // 实现了一下stack方法
    public String removeDuplicates(String s, int k) {
        char[] sa = s.toCharArray();
        Deque<Integer> counts = new ArrayDeque<>(); // stack
        int w = 0; // writer, slow
        int r = 0;
        while (r < s.length()) {
            sa[w] = sa[r]; // copy
            if (w == 0 || sa[w] != sa[w - 1]) { // 第一个或新的（避免了下标非法）
                counts.offerLast(1);
            } else {
                int count = counts.pollLast() + 1;
                if (count == k) { // 这样也就不用塞回去了
                    w = w - k;
                } else {
                    counts.offerLast(count);
                }
            }
            w++;
            r++;
        }
        // w指向了下一个要添加的位置
        return new String(sa, 0, w);
    }
}
