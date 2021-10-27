# LRU

- HashMap的变量顺序是不可预测的，这意味着便利的输出顺序并不一定和HashMap的插入顺序是一致的。
- 这个特性使得它不能独立实现LRU。为了实现这个功能：
    - 选择1. 我们需要手动实现一个Doubly LinkedList + HashMap
    - 选择2. 我们需要使用LinkedHashMap

## 1. 注意事项
1. OOD 的时候，其实可以略去DoublyLinkedList这一层，因为它里面的函数（moveToHead、pollLRUNode）其实是LRU的特点。
2. hashMap负责通过key立刻找到对应的Node（也方便移动它） -->  从而实现O(1)的get
3. doublyLinkedList的意义是方便淘汰，维护一个真实的LRU序列。 


## 2. 另一个方案：
There is a structure called ordered dictionary, it combines behind both hashmap and linked list.
Java实际上提供了一个实现，即LinkedHashMap。
我们只需要激活淘汰策略即可（通过override激活）
```java
// 代码中默认是false，
// 应该是每次put的时候会被调用，如果返回false就不管，返回true就删掉eldest。
protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
    return false;
}

// 所以应该变成
@Override
protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
    return size() > capactiy;
}
```
