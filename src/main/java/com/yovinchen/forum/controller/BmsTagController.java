package com.yovinchen.forum.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yovinchen.forum.common.api.ApiResult;
import com.yovinchen.forum.model.entity.BmsPost;
import com.yovinchen.forum.model.entity.BmsTag;
import com.yovinchen.forum.service.IBmsTagService;
import io.swagger.annotations.*;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "标签管理接口")
@RestController
@RequestMapping("/tag")
public class BmsTagController extends BaseController {

    @Resource
    private IBmsTagService bmsTagService;

    @ApiOperation(value = "根据标签名获取文章列表", notes = "通过标签名获取文章列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "标签名", required = true, dataType = "String", paramType = "path"), @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", dataType = "Integer", paramType = "query"), @ApiImplicitParam(name = "size", value = "每页大小", defaultValue = "10", dataType = "Integer", paramType = "query")})
    @GetMapping("/{name}")
    public ApiResult<Map<String, Object>> getTopicsByTag(@ApiParam(name = "name", value = "标签名", required = true) @PathVariable("name") String tagName, @ApiParam(name = "page", value = "页码", defaultValue = "1") @RequestParam(value = "page", defaultValue = "1") Integer page, @ApiParam(name = "size", value = "每页大小", defaultValue = "10") @RequestParam(value = "size", defaultValue = "10") Integer size) {

        Map<String, Object> map = new HashMap<>(16);

        LambdaQueryWrapper<BmsTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BmsTag::getName, tagName);
        BmsTag one = bmsTagService.getOne(wrapper);
        Assert.notNull(one, "话题不存在，或已被管理员删除");
        Page<BmsPost> topics = bmsTagService.selectTopicsByTagId(new Page<>(page, size), one.getId());
        // 其他热门标签
        Page<BmsTag> hotTags = bmsTagService.page(new Page<>(1, 10), new LambdaQueryWrapper<BmsTag>().notIn(BmsTag::getName, tagName).orderByDesc(BmsTag::getTopicCount));

        map.put("topics", topics);
        map.put("hotTags", hotTags);

        return ApiResult.success(map);
    }

}
