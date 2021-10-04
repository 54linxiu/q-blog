package com.qht.service;

import com.qht.entity.BlogPost;

import java.util.List;

public interface VueService {
    /**
     * 查询标题 和 省略内容
     * @return
     */
    List<BlogPost> selectIndex();
}
