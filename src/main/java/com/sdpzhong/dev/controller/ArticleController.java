package com.sdpzhong.dev.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sdpzhong.dev.entity.dto.IPageDto;
import com.sdpzhong.dev.entity.po.Article;
import com.sdpzhong.dev.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
@Tag(name = "文章模块", description = "文章相关接口")
public class ArticleController {

    private final ArticleService articleService;

    /* 获取文章分页数据 */
    @GetMapping("/page")
    @Operation(summary = "获取文章分页数据")
    public IPage<Article> page(@Parameter(name = "IPageDto") IPageDto dto) {
        return articleService.getArticlePage(dto.current, dto.size);
    }


    /* 检索文章 */

    /* 获取文章详情 */

    /* 文章热门排行（阅读量、点赞、收藏、转发） */

    /* 发布文章 */

    /* 删除文章 */

    /* 获取个人文章分页数据 */

    /* 修改文章状态 */
}
