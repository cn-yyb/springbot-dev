package com.sdpzhong.dev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdpzhong.dev.common.ArticleStatus;
import com.sdpzhong.dev.entity.po.Article;
import com.sdpzhong.dev.mapper.ArticleMapper;
import com.sdpzhong.dev.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author zhongqing
 * @description 针对表【t_article】的数据库操作Service实现
 * @createDate 2024-07-16 17:19:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    private final ArticleMapper articleMapper;

    @Override
    public IPage<Article> getArticlePage(int pageNum, int pageSize) {
        // 创建分页器
        Page<Article> articlePage = new Page<>(pageNum, pageSize);

        // 分页查询
        // page(articlePage);

        articleMapper.queryArticlePage(articlePage);

        return articlePage;
    }

    /**
     * 未设置发布时间文章，默认将提交时间作为发布时间
     * <p>
     * +
     */
    @Transactional
    @Override
    public void refreshArticlePendingPublishStatusRecords() {

        log.info("==== refreshArticlePendingPublishStatusRecords ====");

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper
                .eq(Article::getStatus, ArticleStatus.PENDING_PUBLISH.getStatus())
                .and(
                        qw -> qw.isNull(Article::getPublishTime)
                                .or()
                                .lt(Article::getPublishTime, new Date())
                )
                .select(Article::getId);

        List<Article> articles = list(queryWrapper);

        if (!articles.isEmpty()) {
            // 更新文章状态为发布态
            articles.forEach(article -> article.setStatus(ArticleStatus.PUBLISHED.getStatus()));
            updateBatchById(articles);
        }

    }
}




