package com.study.algorithms.class20_cross_training_3;

import java.util.*;

public class PutBoxIntoContainer {
    static class Box {
        int width;
        int height;
        
        public Box(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
    
    static int curWidth = 0;
    static int curHeight = 0;
    
    public static List<int[]> calculate(List<Box> boxes, int containerWidth, int containerHeight) {
        List<int[]> res = new ArrayList<>();
        
        int maxColWidth = 0;
        
        for (int i = 0; i < boxes.size(); i++) {
            int curBoxWidth = boxes.get(i).width;
            int curBoxHeight = boxes.get(i).height;
            
            if (curHeight + curBoxHeight > containerHeight) {
                curHeight = 0;
                curWidth += maxColWidth;
                res.add(new int[]{curWidth, curHeight});
                maxColWidth = 0;
            } else if (curWidth + curBoxWidth > containerWidth) {
                return res;
            } else {
                res.add(new int[]{curWidth, curHeight});
            }
            
            curHeight += curBoxHeight;
            maxColWidth = Math.max(curBoxWidth, maxColWidth);
        }
        
        return res;
    }
}
