package com.qht.service.impl;

import com.qht.dao.VueMapper;
import com.qht.entity.BlogPost;
import com.qht.service.VueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName VueServiceImpl
 * @Author q
 * @Date 2021/9/27 10:41
 * @Version 1.0
 */
@Service
public class VueServiceImpl implements VueService {

    private VueMapper vueMapper;
    @Autowired
    public void setVueMapper(VueMapper vueMapper) {
        this.vueMapper = vueMapper;
    }

    @Override
    public List<BlogPost> selectIndex() {
        return vueMapper.selectIndex();
    }
}
