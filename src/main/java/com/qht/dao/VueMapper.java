package com.qht.dao;

import com.qht.entity.BlogPost;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VueMapper {

    List<BlogPost> selectIndex();


}
