package com.sdpzhong.dev.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdpzhong.dev.entity.po.Article;
import com.sdpzhong.dev.mapper.ArticleMapper;
import com.sdpzhong.dev.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * @author zhongqing
 * @description 针对表【t_article】的数据库操作Service实现
 * @createDate 2024-07-16 17:19:54
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {


    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public IPage<Article> getArticlePage(int pageNum, int pageSize) {
        // 创建分页器
        Page<Article> articlePage = new Page<>(pageNum, pageSize);

        // LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // queryWrapper.orderByDesc(Article::getId).leftJoin(User.class, User::getUid, User::getUsername);

        // 分页查询
        // page(articlePage);

        articleMapper.queryArticlePage(articlePage);

        System.out.println("当前页码值：" + articlePage.getCurrent());
        System.out.println("每页显示数：" + articlePage.getSize());
        System.out.println("一共多少页：" + articlePage.getPages());
        System.out.println("一共多少条数据：" + articlePage.getTotal());
        System.out.println("数据：" + articlePage.getRecords());

        return articlePage;
    }
}




