# Breadth First Search
1. a `FIFO` queue

- [Get Keys in Binary Tree Layer By Layer](./LayerByLayer.java)
    - 注意expand的时候，不要expand会让generate时NPE的东西。（即检查是否为null，非null才generate）
- [Determine if an undirected graph is bipartite](./Bipartite.java)\
  whether a graph's node can be divided into two groups, such that the nodes in each group do not have direct edges between the nodes that belong to the same group

```
       1
      /  \
     2 -- 3
```

```
 queue [1]
 generated{1u}

 1(u)           expand 1, 标记为u集合
 2(v), 3(v)     generate 2、3，标记欧威v集合
 queue [2,3]
 generated{1u,2v,3v}  -->  发现原来里面无2，3，所以加入。

 2(v)         expand 2
 1(u), 3(u)   generate 1,3
 queue [3]
 generated{1u,2v,3v} --> 发现1匹配，但是3不符合，return false

 发现3的集合归属有conflict。
```
```
 如何记录？
    通过一个HashMap来查conflict，key是node（node），value是set归属（颜色）
 如何一个一个访问？
    通过BFS，flooding地访问。
```

- [Check if a given binary tree is completed](CheckCompleted.java)
    - A complete binary tree is one in which every level of the binary tree is completely filled \
      except possibly the last level.
    - Furthermore, all nodes are as far left as possible.
    - 使用BFS, 在每一层里，只要见到过一次#，该层的后面就只允许出现#了。而且不允许有下一层了。（涉及层间关系，必须要用到BFS了）
    - 可以理解成，CBT转换为一个数组，这个数组只有尾部是null。

```
        5
      /    \
    3        8
  /   \
1      4

is completed.

        5
      /    \
    3        8
  /   \        \
1      4        11

is not completed.
```

```
Corner Cases
    What if the binary tree is null? Return true in this case.
```

```
The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4
```


# Best First Search
1. a `Priority` queue
2. Time Complexity:
   - Min(or Max) Heap, and `insert` and `remove` operations take `O(hgeight) = O(log n)` time.

- [Kth Smallest Number in Sorted Matrix](./KthSmallestNumber.java)
   - size k minHeap is used, so TC: `O(k logk)`, SC: `O(mn + k)` (m*n is the size for visited matrix)
   
