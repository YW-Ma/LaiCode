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