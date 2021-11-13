package com.interview.bloomberg;

import java.util.*;

public class BrowserHistory {
    List<String> list; // array for urls
    int end;
    int cur;
    public BrowserHistory(String homepage) {
        list = new ArrayList<>();
        list.add(homepage);
        cur = 0;
        end = 0;
    }
    
    public void visit(String url) {
        if (list.size() == cur + 1) {
            list.add(url);
        } else { // end > cur
            list.set(cur + 1, url);
        }
        cur++;
        end = cur; // 在visit后，end始终结束于当前页面。
    }
    // e k c i
    //       .
    //       .
    
    public String back(int steps) {
        if (steps >= cur) {
            steps = cur;
        }
        cur = cur - steps;
        return list.get(cur);
    }
    
    public String forward(int steps) {
        if (steps >= end - cur) {
            steps = end - cur;
        }
        cur = cur + steps;
        return list.get(cur);
    }
}
