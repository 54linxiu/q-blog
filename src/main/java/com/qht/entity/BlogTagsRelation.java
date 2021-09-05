package com.qht.entity;

/**
 * @ClassName blogTagsRelation
 * @Author q
 * @Date 2021/9/5 14:05
 * @Version 1.0
 */
public class BlogTagsRelation {
    private int relationId;
    private int blogId;
    private int tagsId;

    public BlogTagsRelation() {
    }

    public BlogTagsRelation(int relationId, int blogId, int tagsId) {
        this.relationId = relationId;
        this.blogId = blogId;
        this.tagsId = tagsId;
    }
}
