# Tree+Recursion

## Path Problem in Binary Tree
> - Bottom-Up: 三部曲
> - Top-Down: carry a `path prefix` or `prefix sum` while traversing the tree, return a boolean or void.

        // 和bottom-up一样，需要考虑四种情况（无孩子、一个左孩子、一个右孩子、两个孩子）
        // BottomUp中，为了汇报自己下面的max，分别（汇报自己，汇报自己+左孩子最大，汇报自己+右孩子最大，汇报自己+孩子中大的）
        // TopDown中，为了汇报自己下面的max，需要告知自己下一级别最新的prefixSum，然后按照情况返回对应孩子的返回值。
        
        // 他俩的区别在于，top-down的算法，是类似于DFS的，最底下的node知道的情报最多
        // bottom-up的算法，则是越靠近root的知道的情报越多

1. **max path from leaf to root**
    - [bottom up](./BottomUp/MaxPathSumLeafToRoot.java): 询问子树各自最大值，选最大的加上自己。
    - [top down](./TopDown/MaxPathSumLeafToRoot.java):  传递 accumulate_sum （第二类）
    - 一般max xxx 都需要传递一个max[1]来记录
2. **straight path sum to a target**
    - [top-down](./TopDown/BinaryTreePathSumToTarget.java): iterate over the tree, 每次检查是否有target path在头上出现过  O(n*height)
      - 为了检查，需要记录自己头上的path，然后用O(height) 来撸一遍
    - [top down（高效但是易错）](./TopDown/BinaryTreePathSumToTarget2.java)：iterate over the tree, 每次记录prefix_sum，并且通过下方逻辑求是否有target在自己上面出现过。 O(n)
      - if cur_prefix_sum - target = historical_prefix_sum
      - 即 当前路径 - 半中央到当前节点的路径 = root到某个历史上的点的路径
      - 那么我们知道，这个历史上的点，到当前节点，就是和威target的路径。
3. **max path sum from 直上直下的 any node to any node**
    - [bottom up](./BottomUp/MaxPathSumBinaryTreeIII.java): ask max single path，update globalMax，return new max single sum。
    - [top down](./TopDown/MaxPathSumBinaryTreeIII.java) 注意此答案待检查:  
      - pass `max-prefix-sum` (the largest sum ends at root), 
      - 计算以当前root结尾的`新max-prefix-sum`（考虑继承prefix 或 不继承prefix），更新max。
      - 传递给下一layer，让它们去计算自己的 `max-prefix-sum`