package com.qht.entity;

/**
 * 博客分类
 * @ClassName BlogSort
 * @Author q
 * @Date 2021/9/4 11:22
 * @Version 1.0
 */
public class BlogSort {
    private int sortId;
    private String sortName;

    public BlogSort() {
    }

    public BlogSort(int sortId, String sortName) {
        this.sortId = sortId;
        this.sortName = sortName;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    @Override
    public String toString() {
        return "BlogSort{" +
                "sortId=" + sortId +
                ", sortName='" + sortName + '\'' +
                '}';
    }
}
