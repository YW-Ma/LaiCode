# Two Sum Pairs

## Find all possible pairs
- [TwoSumAllPairs 1](./TwoSumAllPairsI.java)
- HashMap:  value --> indices of the visited elements have this value.
- Example: [2,2,4],6 ==> [2,4] [2,4]

## Find all pairs (not distinct)
- [TwoSumAllPairs 2](./TwoSumAllPairsII.java)
- HashMap:  value --> the remaining count that can be used
- Example: [2,2,4],6 ==> [2,4]
- Example: [1,1,1,1,1],2 => [1,1] [1,1]

## Find all pairs (distinct)
- [TwoSumAllPairs 3](./TwoSumAllPairsIII.java)
- HashMap: value --> is this the first time? (can be a counter)
  - **[for num*2 == target]**: only record the first occurrence
    - check if count is 1
  - **[for normal case]**: only record the first occurrence
    - check if count is null or 0
  - **[for cannot match]**: add counter
- Example: [1,1,1,1,1],2 => [1,1]   (should be distinct)

- 优化：
  - 如果想知道某个东西是否存在过，不要用 `getOrDefault(key, 0) >= 1`.  应该使用`.containsKey(key)`
  
- 如果是使用 sorted array来做
  - 那么重复的内容表现为一段连续的相同数字
  - 其实只要跳过 `left > 0 && array[left] == array[left - 1]` 对应的段落，就没问题
  - 只需要保证left不重复，那么就不会有duplicate results了。 
  - 注意right那边不能去重，不然会漏掉distinct

