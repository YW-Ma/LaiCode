# Arrays.sort()的开销

##1.  Arrays.sort(Object[])  即reference type
- 对于reference type，用的是TimSort Algo。
- 开销是 O(nlogn), 类似mergeSort。

目的：需要一个稳定的排序算法。
- 举例来说，对于primitive type，1和1是一样的。所以不稳定也没关系。
- 但是Object则不同，某个字段一样，不代表其他字段一样。
- 如果要先按照身高排序，再按照年龄排序。用QuickSort就会出现年龄组内部的身高乱序了。

##2.  Arrays.sort(int[]) 以及其他primitive type

- 开销是 O(nlogn) on Avg. Worst case is O(n^2)
- 用的QuickSort，不稳定也没关系，反正都一样。