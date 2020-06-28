package com.hdu;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/5/2
 * @Time 下午4:39
 */
public class Article {
    private Integer id;
    private String title;
    private String content;

    public Article(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
