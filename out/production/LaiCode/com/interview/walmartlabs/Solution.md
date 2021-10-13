# Walmart Labs
0.[对HashMap的实验](./HashMapExperiment.java)
    - 修改value，是否value会自动变化（reference的情况下）
        - 尝试修改作为value的 List 和 array中的元素，发现真的变了。根本不需要再put回去。
    - 即直接getValue 然后修改就行了（如果是reference type的话）

1. [332. Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/)
    - 拓扑偏序的准备阶段，有致命错误：
        - 准备出度的时候，如果只看from，会漏掉出度为0的node
        - 准备入度的时候，如果只看to，会漏掉入度为0的node
        - 因为漏掉了，所以在while loop的时候，走到这里，发现map没有，会出现NPE。
        - 这个和课表不一样，课表不会出现思路。 但是航班是会有死路的。 所以我[写了一个错误版本](./ReconstructItinerary_Wrong.java)
    - 实际上是一个遍历有向有环图、（注意是有环，不是acyclic) 如何避免走到死胡同产生无解问题： 不可以再使用while循环了，应该使用DFS。
          因为while循环删了node就从map里消失了。应该用DFS实现 trail-fail-and-fallback strategy.
    - 仔细看题并修改了问题后[正确版本](./ReconstructItinerary.java) 
        - 时间复杂度：for each Vi, we have d choices (at most d possible destinations)
        - then TC: O(branch ^ depth) = O(d ^ |E|)
        - hard to know what d is.
        - 主要是还会绕回来。。。所以并不是每个V只generate一次。
    
    - 更快的解决方案，实际上是在找eulerian path：(visits every edge exactly once)
        - 1. starts with a random node, and then follows and arbitrary unvisited edge to a neighbor. until one returns to the starting node. (this yields a first circle in the graph)
        - 2. if this circle covers all nodes, it's an Eulerian cycle. Otherwise, one choose another node among the cycles' nodes with unvisited edges and constructs another circle, called subtour.
        - 具体来看：
            - 从JFK触发，走unused edges，知道卡住发没有更多的unvisited外向边（outgoing edges）
            - 退回到上一个neighbor，找unused edges，重复这个步骤直到所有的edges都被访问了。 
            - 和DFS的差别：只有把当前node的所有neighbors都访问了，才能把自己加进去。 相当于是一种post-order的遍历。
            - 之前写的DFS相当于是先序遍历。[新版本](./ReconstructItinerary_new.java)
        - 只有自己的destList已经为空的时候，才会走到这里。 这意味着所有的路径都被探索过了，即自己之后的itinerary已经构造完毕了。所以可以安心加入自己。

2. [124. Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/)
    - 注意三部曲 [Binary Tree Maximum Path Sum](./BinaryTreeMaximumPathSum.java)

3. [138. Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/)
<<<<<<< HEAD
    - 方案1：dictionary, O(n) space
    - 方案2：每个node先复制一份在自己后面，然后根据原数组链接，构造新数组链接。 最后把两个拆开来。 O(1) extra space.
=======
    - 方案1：dictionary, [O(n) space]()
    - 方案2：每个node先复制一份在自己后面，然后根据原数组链接，构造新数组链接。 最后把两个拆开来。 [O(1) extra space]()
>>>>>>> 5c87eb018e689116c5fd39ff24e35a656ac042ad

4. [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive/)
    - Given an unsorted integer array nums, return the smallest missing positive integer.
    - 如果是 012, 那么返回下一个，即3.
    - 如果是 13, 返回miss的，即2.
    - 如果是 789, 返回miss的，即1.
    
    - hint：所有的negative和0，都可以被1替换掉（如果1不在就直接返回1完事）
        - O(n)方案 hashMap存储所有的值，然后从1开始往size loop，如果遇到n找不到，那么n就是第一个missing的。 也可以用一个size大小的数组来实现
        - O(1)方案 其实是投机取巧，把方案1的那个size数组套用到原数组上，由于一开始把小于1的都替换成1了，所以”如果是negative“就代表hash的存在。 赋予sign一个意义。
<<<<<<< HEAD
5. [460. LFU Cache](https://leetcode-cn.com/problems/lfu-cache/solution/lfuhuan-cun-by-leetcode-solution/)
    放OA结束做

6. [146. LRU Cache](https://leetcode-cn.com/problems/lru-cache/solution/)
    放OA结束做
=======
    - [code]()
5. [460. LFU Cache](https://leetcode-cn.com/problems/lfu-cache/solution/lfuhuan-cun-by-leetcode-solution/)
    

6. [146. LRU Cache](https://leetcode-cn.com/problems/lru-cache/solution/)
    
>>>>>>> 5c87eb018e689116c5fd39ff24e35a656ac042ad
   
7. []()面試官是一位別組的三哥，人巨好，先問了一道算法題，題目LC上沒找到大意是這樣：
給你一個 array 例如 [1,3,5,6,4,2] 然後給你一個正整數K, 例如K = 3，
問你這個array是否能剛好分成若干個subarray，使每個subarray的長度都是K並且subarray裡的數字排序後都差1，
以我給的例子而言答案就是True因為這個array可以被分成兩個 subarray [1, 2, 3] , [4, 5, 6] ，每個長度都是3且
排序後每個相鄰的樹枝間都相差1


8. 给定有序数组，target和k，要求找出最接近target的k个数按差值排序。如果差相同小的在前。[1,4,5,7,9], target = 8, k = 3 [7,9,5]   说了可以binary search 可以heap
   应该就是这个题。 需要知道怎么按差值排序。


9. [658. Find K Closest Elements](https://leetcode.com/problems/find-k-closest-elements/)
    - 用Binary Search：
        - 方案1: O(logN + k) --> binary search find the closest point(while (left < right - 1) 因为left最多是=mid), then expand (谁小移谁 moving either left or right depending on which is closer to x)
        - 方案2：O(log(N-k)) + subList的O(1) --> binary search the left border, mid 是 left border, mid + (k-1) 应该是right border。 现在就看right border 右侧的那个value是不是比left border 更近
            - 即 在 0 - N-k 这个范围内，查找valid window border, left和right+1, 如果right+1比left更近，那说明window右移才行。否则window可以尝试左移。
            - 即 left = mid + 1, right = mid 是两个条件。 因为left越过mid，所以while循环写 while (left < right) 就可以了
10. Singleton class follow up 是 how to do lazy instantiation
    - 123

11. Merge K List
    - 123

12. hashmap的implemen‍‍‌‌‌‌‍‌‍‍‌‌‌‌‌‍‌‌‌tation
    - 123

13. [545. Boundary of Binary Tree](https://leetcode.com/problems/boundary-of-binary-tree/)
    - 123
    - 
    
14. [Word Search](https://leetcode.com/problems/word-search/)
    - 123
    - 
    
15. [Palindrome](https://leetcode.com/problems/palindrome-number/)
    - [code](./Palindrome.java)   其实和reverse number很像。 只是这里只reverse一半。 而且考察了怎么停止、停止后怎么比较
    - hint: -121这种负值，reverse以后是 121-， 不被认为是palindrome。个位是0的也不被认为是palindrome。 另外reverse可能会出现溢出，所以最好只reverse一半。
        - 实际上是把后一边reverse，一边拆下来了。
    - 终止条件：很难判断什么叫“一半” 所以就直接直接“原来数字剩下的部分只要还大于新的一半”
    - 奇偶数问题：  121 会根据停止条件变成 1 + 12，   1221根据停止条件是 12 + 12，   所以如果原数组 == 新 || 新 / 10  都会被认为是palindrome
    - 也看一下[valid palindrome](https://leetcode.com/problems/valid-palindrome/)
    
16. 2 并发问题解决方案？
    比如：亚马逊某商品有2个库存，有10个人同时下单，如何处理？

    3 Cookie和Sessi‍‍‌‌‌‌‍‌‍‍‌‌‌‌‌‍‌‌‌on的区别？

    4 LocalStorage和Cookie的区别？

    5 AJAX工作原理，服务端、浏览器的双向通讯机制？

17. 题目：给一串字符like“wwbbbbwww”  --> white & black pieces
wendy和bob轮流拿走w或者b，仅当这个字母有相邻的相同字母时
如果轮到w的时候，没有w可以拿了，那bob就赢了
输出赢的人名

wwbbbbwww
   ^
wwbbbwww
      ^
wwbbbww
   ^
wwbbww
wendy cannot remove any piece, so Bob wins

就for循环找吧。

18. 字符串替换
“a” => “a”
“ab” => “abb”
“cb” => “cccbb”
"ccb" =>“ccccccbb”
```java
public String replaceAll(String regex, String replacement);
str.replaceAll("cb", "cccb")
```

