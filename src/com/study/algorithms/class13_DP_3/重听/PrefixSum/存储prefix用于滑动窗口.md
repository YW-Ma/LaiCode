    // 先看一个题目：
    // {3,2,1,4,5,3,2,6}
    // Given an integer array A[N] --> there are repeated queries asking for the sum between A[i] and A[j]
    // then what should we do in order to speed up the query.

    // 如何设计存储什么呢？
    // Plan 1 (not wisdom) 背下来全部答案，记录在二维数组中。
    // Plan 2 (recommended) 背下来prefix，记录在一位数组中。如果求 [x,y]的， 则用 prefix[y] - prefix[x-1] 即可。但是要注意一下定义域，x-1不能小于0；

    // Sliding window 会用到prefixS


    // 举一反三：
    // [1] Given a matrix of integers (positive & negative & 0s)
    // how to find the (sub-matrix with the largest sum)?

    // Plan 1 (very naive, O(n^6)) --> 有O(n^4)sub-matrix，对每个matrix 循环i,j找max。一共O(n^6)

    // Plan 2 (DP, O(n^4)) -->             蓝颜色        = 蓝prefix - 红prefix  - 绿prefix  + 橘黄prefix （都是 左侧PrefixSum
    //              Sum of [(k,t),(i,j)] matrix = M[i][j]  - M[k-1][j] - M[i][t-1] + M[k-1][t-1]S
    //              DP不存储子问题(子问题是Sum)，而是存储求问题时遇到的重复内容 (预先都计算好)
    //              需要枚举所有sub-matrix，所以需要O(n^4)

    // Plan 3 (DP, O(n^3)) --> 需要拍扁一个维度。
    //          我们选择把top-row和bottom-row之间的内容压扁(sum)求最大 O(n)。
    //              step 1: using the column-wise prefix sum to 拍扁
    //              step 2: 求largest subarray
    //          枚举所有top-row,bottom-row组合 O(n^2)