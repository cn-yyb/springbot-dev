package com.sdpzhong.dev.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdpzhong.dev.entity.po.Article;

/**
 * @author zhongqing
 * @description 针对表【t_article】的数据库操作Mapper
 * @createDate 2024-07-16 17:19:54
 * @Entity com.sdpzhong.dev.entity.po.Article
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 获取文章分页数据
     *
     * @param page IPage<Article>
     * @return IPage<Article>
     */
    Page<Article> queryArticlePage(Page<Article> page);
}




