package com.interview.WeeklyContest;

public class FindAllPeopleWithSecret {
    // 给定一个证书n，表示有n个专家（他们编号从0到n-1）
    // 另外有一个 int[][] meetings  是会议记录
    //       meetings[i] = [x_i, y_i, time_i]   -->  每个会议记录是x_i和y_i在time_i开了一个会。 注意一个专家可以同时参加多场会议
    // 现在提供了一个整数firstPerson
    // 假设专家 0 有一个秘密，他在时间 0 把这个秘密给了专家firstPerson
    // --> 每次会议会 x_i 会把秘密发送给 y_i
    // --> 这个秘密共享是瞬间的，比如y_i在 time_1 了解了秘密，就可以瞬间在time_1分享给其他人    -->  估计是graph问题？

    // 问所有会议都结束后，所有知道了这个秘密的专家的列表。

    
}
