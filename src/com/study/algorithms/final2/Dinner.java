package com.study.algorithms.final2;

import java.util.ArrayList;
import java.util.List;

public class Dinner {
    // input: an array of names(capital letters)
    // output: T/F,  if we can arrange the students so that the names of all students around the table form an "infinite loop"
    //         i.e.  for each pair of neighboring students s1 and s2, the last letter of s1's name must be identical to the first letter of s2's name
    
    // DFS:
    // [ _ , _ , _ , _ ]
    // each layer: try to put remaining people in current seat
    // layers: # of seats
    // branches: # of matched names from remaining people
    // post-possessing: check first one and last one's name
    
    public boolean canFormInfiniteLoop(String[] names) {
        return helper(names, 0);
    }
    
    private boolean helper(String[] names, int prevIndex) {
        // base case:
        if (prevIndex == names.length - 1) { // try to put the last one
            return isMatch(names[prevIndex], names[0]); // lastOne's end and first one's beginning
        }
    
        // recursion rule:
        // try to find a matched student name
        boolean hasMatched = false;
        for (int i = prevIndex + 1; i < names.length; i++) {
            if (isMatch(names[prevIndex], names[i])) {
                swap(names, i, prevIndex + 1);
                if (helper(names, prevIndex + 1)) {
                    hasMatched = true; // has matched at lease once
                }
                swap(names, i, prevIndex + 1);
            }
        }
        return hasMatched;
    }
    
    private void swap(String[] names, int i, int j) {
        String str = names[i];
        names[i] = names[j];
        names[j] = str;
    }
    
    private boolean isMatch(String last, String begin) {
        if (last.charAt(last.length() - 1) == begin.charAt(0)) {
            return true;
        }
        return false;
    }
}
