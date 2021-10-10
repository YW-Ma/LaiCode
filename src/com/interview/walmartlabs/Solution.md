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

2. [124. Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/)


3. [138. Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/)


4. [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive/)

5. [460. LFU Cache](https://leetcode.com/problems/lfu-cache/)

6. [146. LRU Cache](https://leetcode.com/problems/lru-cache/)

7. []()面試官是一位別組的三哥，人巨好，先問了一道算法題，題目LC上沒找到大意是這樣：
給你一個 array 例如 [1,3,5,6,4,2] 然後給你一個正整數K, 例如K = 3，
問你這個array是否能剛好分成若干個subarray，使每個subarray的長度都是K並且subarray裡的數字排序後都差1，
以我給的例子而言答案就是True因為這個array可以被分成兩個 subarray [1, 2, 3] , [4, 5, 6] ，每個長度都是3且
排序後每個相鄰的樹枝間都相差1


8. 给定有序数组，target和k，要求找出最接近target的k个数按差值排序。如果差相同小的在前。[1,4,5,7,9], target = 8, k = 3 [7,9,5]   说了可以binary search 可以heap，没说two pointer也没问。


9. [658. Find K Closest Elements](https://leetcode.com/problems/find-k-closest-elements/)

10. Singleton class follow up 是 how to do lazy instantiation

11. Merge K List

12. hashmap的implemen‍‍‌‌‌‌‍‌‍‍‌‌‌‌‌‍‌‌‌tation
