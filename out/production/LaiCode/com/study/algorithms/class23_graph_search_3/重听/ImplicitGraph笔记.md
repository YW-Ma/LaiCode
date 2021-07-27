# Implicit graphs:
- Find k-th smallest in sorted matrix
  - 靠脑补把sorted matrix脑补成graph，然后用BFS
- All subset
  - 先脑补出recursion tree，然后再做DFS
- DP 火柴题
  - 下、右 两个Edge
  
## 什么时候用DP、BFS1、BFS2、DFS
- DP：也是最小距离、最小步数。 可以先try一下能不能做出来。
- BFS1 的flooding性质--> 分层或者求步数（即层数）
- BFS2 求最短路径相关的
- DFS 剩下的题目

    
## Key Challenges
- what is node, what is edge    基础是要能学会建模
- efficiently finding neighbors 关键是如何高效找neighbors


### Q1 BFS-1 on implicit graphs
- [Seven Puzzle](./SevenPuzzle.java)
  - 最小步数 --> BFS1 or DP
    - 点：棋盘状态
    - 边：可变化情况4个
  - 一般做最小步数，都需要HashMap。存储Board到Steps Num。 两个作用：
    1. 判重复  （图里有环），HashSet也可以判重。
    2. 从起点到当前的最小步数，HashSet不能记录
  - 为什么BFS可以求最优解：因为它是"flooding"，所以会先遇到最优解，才会遇到次优解
  - Follow-up: What if there are thousands of queries? How to minimize the processing time for each query?
    (即不断有人问各种状态到final状态的步数。)
    - 预处理，如果是对8!个状态各求一次，那么开销很大： O(E+V)*8! = O(8!*4 + 8!)*8!
    - 优化预处理：因为是无向图，所以可以从final往回找：从final往回BFS，到达所有情况，并记录。 只需要O(E+V)
  

- [Word Ladder](./WordLadderII.java)
- 与edit distance不同，只能有一个操作（更换一个字母，且更换后的单词需要在wordList里能找到）
- 求最少步数 --> 要么DP、要么BFS1. 优先BFS1试试
  - 点：一个word
  - 边：一个可行变化，如何efficiently find neighbors？
    - 低效找邻居方案：把所有单词都撸一遍，看不同的字母是否只差1个。每次expand的时候需要 O(n)*O(wordLength)。最后导致O(n^2 * wordLength)
    - (优秀方法)：生成出所有一次可达的单词，然后看这些结果是否在wordList里面。由于只有26个字母，所以是 O(26 * wordLength) --> O(wordLength)
  
- 需要HashMap判重+记录steps
-  开销： `O(V * 26*L * L)`\
   `26 * L`求各情况   `L`求hashCode\
   `V`是num of words\
   `L`是word length

- FollowUps: 如何把ladder打印出来呢？
  - 把 parent记录下来，知道是从谁generate出来的。
  - 可以写成一个HashMap 叫 predecessor
  - 凡是BFS还需要路径，那么就用这个方案
  
- FollowUpsII: 如何把所有的shortest wordLadder打印出来（可能有多个最短path）
  

### Q2 BFS-2 on implicit graphs
- [没共同字符并且长度乘积最大](./LargestLengthProduct.java)
  - 找到字典strings中的两个string，使得他们之间没有共同字符，并且长度乘积最大
    - no common letters 怎么实现，注意 abc和cab 也是common。\
       预处理，获得bitMasks 即每个单词的 字母set。 开销是O(n*L)
  - **不用BFS-2做法如下**\
    for for loop 枚举所有对，并且用HashMap检查是否有共同字符\
    然后update 一个globalMax
  - **BFS-2的做法如下**\
    先获得长度乘积最大的，看有没有共同字符。如果有就找次大的，否则返回。
    - 为了先获得长度乘积最大的，需要先对size排序。
    - 如何找次大的？ 也需要一个 for for loop, 在sorted 以后的 word list里面找。   
        - 我们把第一个乘数认为是"行号"， 
        - 第二个乘数认为是"列号" 那么其实是一个matrix\
            ```
          s1*s2,  s1*s3, s1*s4 ...
                    s2*s3, s2*s4 ...
                           s3*s4 ... 
          ```
    - 时间复杂度是什么呢？
       - 预处理相同
       - 计算的时间 ????? 下课后计算一下
  
- [找到 3^x+5^y+7^z 的最小值](./KthSmallestThreeFiveSeven.java)
  - node是一个状态
  - branch是三个，即x、y、z各增加1
  - 用BFS-2来找smallest
  - 下课后写一下

- [给定k个器材和一些阻碍，找到一把chair，能让 到各器材距离sum 最小](./PlaceToPutChairI.java)
  - 先做假设，二维矩阵，离散化chair和器材，让与它们相交的格子代表它们。
  - 走路的路径认为是四连通的，不要clarify为八连通的 （4-connect）
  - 最简单的思路：
    - for for loop 所有可能放椅子的地方。一个loop中求取sum of 到k个健身器材的shortest distance（Dijkstra，有障碍的地方graph认为是没有node）
    - 开销 `O( (RC) * k * (RC)log(RC))`
    - 注意BFS-2自己的时间复杂度是O((V+E)logV) --> 怎么求的来着？？？？ logV是最差的找最邻居开销
  - 优化一下下：Dijkstra是点到面，不需要对k个器材分别求。
    - for for loop 所有可能放椅子的地方。 一个loop中，先dijkstra一次，然后sum起来更新。(RClogRC + k)
    - 开销 `O((RC) * ((RC)log(RC) + k))` 
    - 用加法因为是两个阶段 (有个截图可以看一下)
  - 再优化一下：从器材出发，可以干掉无用的开销（从椅子出发每次求了到empty位置的距离，无用）
    - 预处理获得一个三维矩阵：第一个维度是各个健身器材，第二、三个维度是到对应位置的开销
    - 开销 `O(k * RClogRC)`

- [课程规划](./CourseScheduleII.java)
  - 给了先修课列表，实际上这些课的依赖关系是一个Graph。（implicit graph）
  - 第一门课必须是没有incoming edges的点
  - 每次学了一门课，就把他删掉（这样才能产生新的"没有predecessor的点"）
  - solution:
    ```java
    while (still has points)
     1. pick a node with no incoming edges
     2. delete this node
     3. delete all the edges from this node```
  - 需要用什么数据结构跟踪 incoming edges呢？
    - 一个incomingEdges，跟踪所有node的incoming edges
    - 一个Q，存储了所有incoming edges为0的点
      - 在Q里挑一个，expand了。 
      - generate：减去edge from this node 时 incoming edge变为0的点
    - 一个topologicalOrder，记录expand的元素（即记录选课顺序）
  - 如果用PriorityQueue，则可以代替incomingEdges和Q
    - 但是需要使用update, Java没有提供update。我们要用`lazy deletion`实现。
      - 把 更新后的也放进来，并且维护一个boolean array记录expanded node
      - 在expand的时候肯定是先expand这个近的。 expand的以后记录bool array， 
      - 第二次遇到的（更新前的）时候，把它直接跳过。
  



