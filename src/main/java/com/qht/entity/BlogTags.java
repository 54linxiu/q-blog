package com.qht.entity;

/**
 *  博客标签
 * @ClassName BlogTags
 * @Author q
 * @Date 2021/9/4 11:24
 * @Version 1.0
 */
public class BlogTags {
    private int tagsId;
    private String tagsName;

    public BlogTags() {
    }

    public BlogTags(int tagsId, String tagsName) {
        this.tagsId = tagsId;
        this.tagsName = tagsName;
    }

    public int getTagsId() {
        return tagsId;
    }

    public void setTagsId(int tagsId) {
        this.tagsId = tagsId;
    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }

    @Override
    public String toString() {
        return "BlogTags{" +
                "tagsId=" + tagsId +
                ", tagsName='" + tagsName + '\'' +
                '}';
    }
}
