# solutions 

1. [WiggleSort](./WiggleSort.java) 
    - 一共两个[solution](https://leetcode.com/problems/wiggle-sort/solution/)
        - sort + 在不符合要求的符号处swap。 比如 12345，就 23, 45 分别swap。变成 13254.   O(nlogn)
        - one-pass, 每个检查是否不符合不符合就swap。 这是因为本体的order特别：
            - [0] <= [1] >= [2] <= [3].
            - 如果12顺序不对，那么就是 0 <= 1 <= 2. 这样swap 1，2 并不会影响第一个 less than or equal sign
    
2. [EvenSubarray](./EvenSubarray.java)
    - preSum其实不需要，里面的for loop自己记录有多少个odd即可
    - 由于需要去重，所以需要把具体的内容招到，可以用subList，也可以用StringBuilder造。用hashSet去重。
    
3. [Longest Arithmetic Subsequence(最长等差数列)](./LongestArithmeticSubsequence.java)
    - input: array
    - output: length of the longest arithmetic subsequences in it
    - 定义是等差数列，解题方法要用到DP。
        - 问的是"最长的"， 那么DP要存储"某个情况下最长的是多长"，情况数量是DP长度
        - 情况的总数：用for-for-loop思维，以每个index结束的subseq中（这样方便继承，以每个index开始的subseq不太方便继承），某个diff的对应一个长度。
    - for-for循环
        - 外层循环所有的数字
        - 内层循环这个数字之前的所有元素
        - 获得i、j的diff：如果diff在dp里面，对应dp元素加一
        - 否则初始化这个diff元素为2（长度为两个元素）
      ```java
         // 3 5 1 3 5     7
         // 假设已经考虑过了 3 5 1 3 5 现在来了7
         // 那么DP里面，diff2对应的是
         //   val 3 5 1 3 5
         // dif-2 1 2 1 2 3,  可以看出来两个5的dif-2是不一样的，在7中利用的方法是用j扫描 取dp[j][2] + 1的最大的那个即可。
         //
         // 所以DP是在不同外层for loop之间传递了信息（把之前轮中5的2、3这样的信息，传递给了7，让7能得知自己应该是4）
         ```
          