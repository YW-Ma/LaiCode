#总结
1. CoinChange:
    - 当成跳格子，一次可以跳1，2，3个格子这种。
    - 注意错误处理: (回溯的idx要合法 --> 本身范围合法+值合法) --> 不能从-1个格子开始跳+不能从跳不到的格子开始跳。
    
2. Two Sum:
    - 如果是无序的，就用HashSet, O(n) one pass就可以过。（包括BST那道题）
    - 如果要排序，用sort + while left < right,  O(nlogn + n)可以过。
    
3. 