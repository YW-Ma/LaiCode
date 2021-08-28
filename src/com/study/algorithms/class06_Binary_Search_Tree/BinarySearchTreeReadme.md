# Binary Search Tree
```
Binary Search Tree:
for every node, 
    all nodes in its left subtree is smller
    all nodes in its right subtree is larger.
```

## 54. Is Binary Search Tree Or Not
[Is Binary Search Tree Or Not](./checkBST.java)
```
Determine if a given binary tree is binary search tree.
There should no be duplicate keys in binary search tree.
```
```
Assumption:
1. no duplicate keys in BST
2. integer value within 32 bit [Integer.MIN_VALUE, Integer.MAX_VALUE]
3. input: a TreeNode root
4. output: boolean
5. null root, return true.
```

```
recursion:
what I pass to my children: the value range. for left: [Integer.MIN_VALUE, me-1]
what I expect my children tell me: boolean (value within range)
what I resposne to my parent: boolean, is I and my children fit inside the range given me.

base case: current is null, return true.
```

## 55. Get Keys In Binary Search Tree In Given Range
[Get Keys In Binary Search Tree In Given Range](./GetRange.java)

```
Get the list of keys in a given binary search tree 
in a given range[min, max] in ascending order, 
both min and max are inclusive.

Examples

        5

      /    \

    3        8

  /   \        \

 1     4        11

get the keys in [2, 5] in ascending order, 
result is  [3, 4, 5]  --> look like an In-Order traversal.

solution:
    in-order,
    if root.key smaller than range, than we can cut the left.
    if root.key larger than range, then we can cut the right.
    
```

```
assumption\
1. input a root with range, output a List<Integer>
2. if no keys, return empty list
```