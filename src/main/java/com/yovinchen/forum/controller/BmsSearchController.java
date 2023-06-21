package com.yovinchen.forum.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yovinchen.forum.common.api.ApiResult;
import com.yovinchen.forum.model.vo.PostVO;
import com.yovinchen.forum.service.IBmsPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "搜索管理接口")
@RestController
@RequestMapping("/search")
public class BmsSearchController extends BaseController {

    @Resource
    private IBmsPostService postService;

    @GetMapping
    @ApiOperation(value = "根据关键字搜索文章列表", notes = "根据指定关键字，分页查询文章列表")
    public ApiResult<Page<PostVO>> searchList(@ApiParam(name = "keyword", value = "搜索关键字", required = true) @RequestParam("keyword") String keyword, @ApiParam(name = "pageNum", value = "页码", defaultValue = "1") @RequestParam("pageNum") Integer pageNum, @ApiParam(name = "pageSize", value = "每页显示条数", defaultValue = "10") @RequestParam("pageSize") Integer pageSize) {
        Page<PostVO> results = postService.searchByKey(keyword, new Page<>(pageNum, pageSize));
        return ApiResult.success(results);
    }
}
