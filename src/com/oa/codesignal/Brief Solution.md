# Brief Solution

1. [Compare String With Frequency](./CompareStringWithFrequency.java)【重点】：理解成 frequency的数量要相同即可。 所以先求frequency，然后求frequency的数量。 为了化简一些，char和frequency都一样的就直接抵消掉了。（也可以不抵消）
2. [Broken Keyboard](./BrokenKeyboard.java)【重点】：API的运用：Character.isLetter(char ch),  str.toLowerCase().split(" "),  以及上面一题用过的 map.keySet()

## Code Signal 1-2
1. [check operation](./CheckOperation.java) 四个数组，验证第一个数组的数组+第二个数组的符号+第三个数组的数字，是否等于第四个数组的target
2. [is sub-matrix full](./isSubmatrixFull.java) 注意出一组、入一组
3. [letter combinations of a phone number](./LetterCombinationsOfPhoneNumber.java) DFS
4. [strings head and tail](./CheckStringHeadAndTail.java) 返回boolean array, 如果相邻的两个string的头和尾相同则return true。otherwise false
5. [Split and Swap](./SplitAndSwap.java)【不需要in-place，直接开stringBuilder就行。 in-place的话就两两用"I love yahoo" trick即可】Given a string and a list of numbers, split the string according to the list and swap it. Return the swapped string.
6. [Compute Product and Sum](./ComputeProductAndSum.java)  Compute product - sum of a number.
   For example, given 123456, product would be 1x2x3x4x56 = 720, sum would be 1+2+3+4+5+6 = 21, return product - sum
7. [题外练习 - reverse integer 并考虑overflow](./ReverseInteger.java) 【注意】 正负数都可以用 res = res * 10 + input%10 来反转。while写成 != 10就行了
8. [rotate matrix for k times](./RotateImage.java)【由于rotate 4次归零，所以 k%4 才是要rotate的次数， 调用RotateImage k%4 次即可】

## Code Signal 3
1. [Sorting boundary elements of a matrix](./SortBoudnaryElements.java) 先取出边界（不需要按顺序取），然后sort，然后按顺序放回去。 注意怎么表达Boundary数组和原来grid的位置的对应关系。
2. [ray from start to end (with reflect)](./RayReflect.javas)  模拟光路的行为，试探走到边界i、j有一个会反向后重新试探。
3. [fit with cell replacement]
4. [beauty of a square matrix + sort and replace](./BeautyOfMatrix.java)

## Code Signal 4
1. [Event Emitter]
2. [create rectangles + check can fit or not](./TwoKindsOfOperations.java)