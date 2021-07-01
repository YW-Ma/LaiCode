package com.study.practice.class_19.Enum.NewsRSSFeedEnum;

import java.io.IOException;

public enum NewsRSSFeedEnum {
    // 上面是一些枚举
    YAHOO_TOP_STORIES("http://rss.news.yahoo.com/rss/topstories"),
    CBS_TOP_STORIES("http://feeds.cbsnews.com/CBSNewsMain?format=xml"),
    LATIMES_TOP_STORIES("http://feeds.latimes.com/latimes/news?format=xml");

    // 下面完全就是一个正常的类
    private String rssUrl;

    private NewsRSSFeedEnum(String rss) {
        this.rssUrl = rss;
    }

    public String getRssURL() {
        return this.rssUrl;
    }

    public String fetch() {
        // fetch from this.rssUrl
        HttpReader myReader = new HttpReader(this.rssUrl);
        try {
            return myReader.fetch();
        } catch (IOException ioe) {
            // LOG.warn(ioe);
        } finally {
            myReader.close();
        }
        return null;
    }


// 使用的时候是这样的：在static instance后面调用fetch或者get啥的
//      List<NewsItem> results = parse(NewsRSSFeedEnum.YAHOO_TOP_STORIES.fetch());
//      itemList.add(results);



    public static class HttpReader {
        public HttpReader(String str) {}
        public String fetch() throws IOException {
            return "mock results";
        }
        public void close() {
            return;
        }
    }
}
