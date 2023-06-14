package com.yovinchen.forum.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vdurmont.emoji.EmojiParser;
import com.yovinchen.forum.common.api.ApiResult;
import com.yovinchen.forum.jwt.JwtUtil;
import com.yovinchen.forum.model.dto.CreateTopicDTO;
import com.yovinchen.forum.model.entity.BmsPost;
import com.yovinchen.forum.model.entity.UmsUser;
import com.yovinchen.forum.model.vo.PostVO;
import com.yovinchen.forum.service.IBmsPostService;
import com.yovinchen.forum.service.IUmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(tags = "帖子管理接口")
@RestController
@RequestMapping("/post")
public class BmsPostController extends BaseController {

    @Resource
    private IBmsPostService iBmsPostService;
    @Resource
    private IUmsUserService umsUserService;

    @ApiOperation(value = "获取帖子列表", notes = "根据标签类型获取帖子列表")
    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@ApiParam(name = "tab", value = "标签类型", defaultValue = "latest") @RequestParam(value = "tab", defaultValue = "latest") String tab, @ApiParam(name = "pageNo", value = "页码", defaultValue = "1") @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, @ApiParam(name = "pageSize", value = "每页显示数量", defaultValue = "10") @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = iBmsPostService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }


    @ApiOperation(value = "创建帖子", notes = "通过传入参数来创建一个新的帖子")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ApiResult<BmsPost> create(@RequestHeader(value = JwtUtil.USER_NAME) String userName, @ApiParam(name = "dto", value = "帖子内容", required = true) @RequestBody CreateTopicDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsPost topic = iBmsPostService.create(dto, user);
        return ApiResult.success(topic);
    }


    @ApiOperation(value = "查看帖子详情", notes = "通过ID查看帖子详情")
    @GetMapping()
    public ApiResult<Map<String, Object>> view(@ApiParam(name = "id", value = "帖子ID", required = true) @RequestParam("id") String id) {
        Map<String, Object> map = iBmsPostService.viewTopic(id);
        return ApiResult.success(map);
    }


    @ApiOperation(value = "获取帖子推荐列表", notes = "通过当前帖子 ID 获取相关的推荐帖子列表")
    @GetMapping("/recommend")
    public ApiResult<List<BmsPost>> getRecommend(@ApiParam(name = "id", value = "当前帖子ID", required = true) @RequestParam("topicId") String id) {
        List<BmsPost> topics = iBmsPostService.getRecommend(id);
        return ApiResult.success(topics);
    }


    @ApiOperation(value = "更新帖子信息", notes = "用户可以通过该接口修改自己的帖子信息")
    @PostMapping("/update")
    public ApiResult<BmsPost> update(@ApiParam(name = "userName", value = "用户名", required = true) @RequestHeader(value = JwtUtil.USER_NAME) String userName, @Valid @RequestBody BmsPost post) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        Assert.isTrue(umsUser.getId().equals(post.getUserId()), "非本人无权修改");
        post.setModifyTime(new Date());
        post.setContent(EmojiParser.parseToAliases(post.getContent()));
        iBmsPostService.updateById(post);
        return ApiResult.success(post);
    }


    @ApiOperation(value = "删除帖子", notes = "用户可以通过该接口删除自己的帖子")
    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete(@ApiParam(name = "userName", value = "用户名", required = true) @RequestHeader(value = JwtUtil.USER_NAME) String userName, @ApiParam(name = "id", value = "帖子ID", required = true) @PathVariable("id") String id) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        BmsPost byId = iBmsPostService.getById(id);
        Assert.notNull(byId, "来晚一步，话题已不存在");
        Assert.isTrue(byId.getUserId().equals(umsUser.getId()), "你为什么可以删除别人的话题？？？");
        iBmsPostService.removeById(id);
        return ApiResult.success(null, "删除成功");
    }

}
