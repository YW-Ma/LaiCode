package com.study.practice.class_19.Enum.NewsRSSFeedEnum;

import java.util.ArrayList;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        List<String> results = new ArrayList<>();
        results.add(NewsRSSFeedEnum.YAHOO_TOP_STORIES.fetch());
    }
}
