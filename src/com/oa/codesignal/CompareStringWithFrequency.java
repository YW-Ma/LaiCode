package com.oa.codesignal;
// compare两个string，只有小写字母。
// 每个stirng内部可以任意换位置，所以位置不重要。
// 每个string内部两个letter出现的频率也可以互换，
// 所以这题只需要两个string每个frequency出现的 次数要一样。比如“babzccc” 和 “bbazzcz” 就返回“true”，因为z和c可以互换频率。
// 但是 “babzcccm” 和 “bbazzczl” 就不一样，因为m在第一个里出现过，第二个里没有出现过。
//     “babzcccml” 和 “bbazzczlm” 就一样
//
// If two strings are close enough?
// Given two rules to define two strings are close enough.
//   1. you can swap neighbor char any times. Ex. "abb" -> "bba" (可以随意换位置)
//   2. If two strings have the same character, then you can change the character into another.
//
//  If both strings contain "a" and "b", you can change all "a"s in the first string or change all "b"s in the first string. same as the second string
//  Ex.  Input: S1 = "babzccc", S2 = "abbzczz" Output: True

// Sol.
//  Use a dictionary to record the frequency of characters.
//  Remove the same part in dictionaries
//  try to find the pair that have different character but with the same frequency
public class CompareStringWithFrequency {

}
