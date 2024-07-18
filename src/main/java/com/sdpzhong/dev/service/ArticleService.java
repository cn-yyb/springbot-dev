package com.sdpzhong.dev.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdpzhong.dev.entity.po.Article;

/**
 * @author zhongqing
 * @description 针对表【t_article】的数据库操作Service
 * @createDate 2024-07-16 17:19:54
 */
public interface ArticleService extends IService<Article> {


    /**
     * 获取文章分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Article> getArticlePage(int pageNum, int pageSize);

    /**
     * 自动更新定时发布状态的文章为发布态
     */
    void refreshArticlePendingPublishStatusRecords();
}
