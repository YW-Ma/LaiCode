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

