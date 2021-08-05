# Breadth First Search
1. a FIFO queue

- [Kth Smallest Number in Sorted Matrix]()

# Best First Search
1. a Priority queue

2. Time Complexity:
   - worst case time complexity for Best First Search is `O(n * Log n)` where n is number of nodes. 
   - In worst case, we may have to visit all nodes before we reach goal.
   - Note that priority queue is implemented using Min(or Max) Heap, and `insert` and `remove` operations take `O(log n)` time.