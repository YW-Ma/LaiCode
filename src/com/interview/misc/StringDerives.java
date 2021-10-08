package com.interview.misc;

public class StringDerives {
    /*
    https://www.1point3acres.com/bbs/thread-803663-1-1.html
    
    Reference: https://leetcode.com/playground/cKUz6bCf
    
    coding是说如果两个string，如果其中一个string a可以通过去掉一个字符，再重组得到另一个string b,那么就说a可以derive到b。
    给一个list和一个target，找出所有可以derive到target的string，如果a能derive到b，b能derive到c，那么a也能derive到c。
    e.g. target: ab, list[ba, bca, acb, bac, abcd, adcbe, efgh],那么除了ba和efgh以外都可以derive到ab。
    
    第一眼感觉跟OA那个差不多，用一个graph把互相能derive的连起来，再从target开始dfs找连在一起的。
    但是区别是这里的要求是去掉一个字符后重组剩余字符能得到就算，而OA只是去掉一个字符。
    所以我想的是把list里所有的string都用char list表示，再把这些char list内部按字母顺序排序。
    然后char list做key原string做value得到一个map，比较两个长度相差为1的char list，如果只有一处不同说明可以相互转换，在图里连接。大于一处就pass。
    最后通过这个map把char list还原回原来的string。
    
    写到最后才发现如果用char list做key，那么 acb abc bca的char list都是 abc，最后用map还原回string的时候只能找到一个。
    然后这块花了很长时间没想到应该怎么处理，最后我就在每个char list的最后一位加一个数字表示index，这样key就不一样了。
    总之就是写得很繁琐，因为那个map key的bug写完已经没时间了，
    做完才反应过来没必要一开始就把string换成list，只是在比较的时候转换就行了，这样就不需要那个map。
    
    【另外，可以先sort一下，然后长度相差超过1直接break】
    

    第二题是如果word list里的每个word都有一个value，给你一个起点和终点(ab‍‍‌‌‌‌‍‌‍‍‌‌‌‌‌‍‌‌‌, adcbe),
    找到从ab生成adcbe的最长路径(路径上word的value加起来和最大)，
    说了个把所有路径都找到再取最大的思路，没时间写。
*/
}
