package com.qt.model;

import java.util.List;

/**
 * Created by defore on 16/4/3.
 * 图文消息(最外层)
 */
public class NewsMessage extends BaseMessage{
    private int ArticleCount;
    private List<News> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<News> getArticles() {
        return Articles;
    }

    public void setArticles(List<News> articles) {
        Articles = articles;
    }
}
