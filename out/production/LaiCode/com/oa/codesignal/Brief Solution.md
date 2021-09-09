# Brief Solution

1. [Compare String With Frequency](./CompareStringWithFrequency.java)【重点】：理解成 frequency的数量要相同即可。 所以先求frequency，然后求frequency的数量。 为了化简一些，char和frequency都一样的就直接抵消掉了。（也可以不抵消）
2. [Broken Keyboard](./BrokenKeyboard.java)【重点】：API的运用：Character.isLetter(char ch),  str.toLowerCase().split(" "),  以及上面一题用过的 map.keySet()