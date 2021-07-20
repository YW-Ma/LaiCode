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
    - bottom up: 询问子树各自最大值，选最大的加上自己。
    - top down:  传递 accumulate_sum （第二类）
    - 一般max xxx 都需要传递一个max[1]来记录
2. **straight path sum to a target**
    - bottom up: 检查当前root到后代中是否有一个解：
        - 有可能自己key就相当于target，
        - 也有可能是左子树或右子树中存在`sum`为`target-root.key`的`path`。
    - top down：generate & remember `path-prefix` in HashSet, post-process to find target.
3. **max path sum from 直上直下的 any node to any node**
    - bottom up: ask max single path，update globalMax，return new max single sum。
    - top down:  
      - pass `max-prefix-sum` (the largest sum ends at root), 
      - 计算以当前root结尾的`新max-prefix-sum`（考虑继承prefix 或 不继承prefix），更新max。
      - 传递给下一layer，让它们去计算自己的 `max-prefix-sum`