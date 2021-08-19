# Histogram Questions

## Solution 1 Fundamental
[basic](LargestRectangleHistogram.java)
- 上边界：某个bar的top
- 下边界：x-axis
- 左右边界：某个bar的index

```
可以循环一下，以每个index的高度开始向左向右找
如果掉到悬崖下，就说明找到一个边界。
然后搞到每个bar的top为顶的 rect的最大值。

每次尝试，只是在试探rectangle的长度
因为rectangle的高度是固定的（即当前index对应的height）

```

O(n^2)

## Solution 2 better idea(recommended)
[better idea](LargestRectangleHistogram.java)
```
solution 1是唯一的解决方案，
我们只能提高获得每个index对应的左右边界的效率。

从左向右走
Rule1:
当前index的左边界 = 沿用之前的左边界  ，左边>=自己
                  自己           ，左边<自己
Rule2:                  
当前index的右边界 = 后续才能确定：
    确定方法是，
    发现悬崖的时候
    回头给所有比悬崖底部高的index确定right border
```
  
```
注意，需要回头看，所以需要选择stack数据结构：   
1. stack存储什么？
    - 存储各index的“index”和“height”
2. stack作用和使用？
    - 用于确定右边界
    - 发现悬崖，则弹栈顶元素，确定它的右边界。重复这个操作。
    
```
