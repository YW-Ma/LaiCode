package com.interview.walmartlabs.leetcode;

import java.util.*;

public class MedianFinderHeap {
    Queue<Integer> higher; // higher side
    Queue<Integer> lower; // lower side

    public MedianFinderHeap() {
        this.higher = new PriorityQueue<>();
        this.lower = new PriorityQueue<>((a, b)-> -Integer.compare(a, b));
    }

    public void addNum(int num) {
        // 先加到对应的heap里面，然后调整heap的size，使得一样大或者higher比lower大1
        // -->  简化，加入其中一个，然后维护size

        // 加入可能有问题，比如 13    35 我加入2，我肯定不能无脑加入右边
        // 【此时需要先加入一侧，比如左侧  产生123  35， 然后再把左边的top弄到右边 12  335】
        // 这样才叫做加入了。 不然顺序可能是错的。
        // 等加入完了，就考虑维护size的事情


        lower.offer(num);
        higher.offer(lower.poll());
        while (lower.size() < higher.size()) {
            lower.offer(higher.poll());
        }
    }

    public double findMedian() {
        if (lower.size() < higher.size()) {
            return higher.peek();
        } else if (lower.size() > higher.size()) {
            return lower.peek();
        } else {
            return (double)(lower.peek() + higher.peek()) / 2;
        }
    }
}
