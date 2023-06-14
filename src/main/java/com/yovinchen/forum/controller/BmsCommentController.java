package com.yovinchen.forum.controller;

import com.yovinchen.forum.common.api.ApiResult;
import com.yovinchen.forum.jwt.JwtUtil;
import com.yovinchen.forum.model.dto.CommentDTO;
import com.yovinchen.forum.model.entity.BmsComment;
import com.yovinchen.forum.model.entity.UmsUser;
import com.yovinchen.forum.model.vo.CommentVO;
import com.yovinchen.forum.service.IBmsCommentService;
import com.yovinchen.forum.service.IUmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "评论管理接口")
@RestController
@RequestMapping("/comment")
public class BmsCommentController extends BaseController {

    @Resource
    private IBmsCommentService bmsCommentService;
    @Resource
    private IUmsUserService umsUserService;

    @ApiOperation(value = "根据话题ID获取评论列表", notes = "根据话题ID获取评论列表")
    @GetMapping("/get_comments")
    public ApiResult<List<CommentVO>> getCommentsByTopicID(@ApiParam(value = "话题ID", required = true) @RequestParam(value = "topicid", defaultValue = "1") String topicid) {
        List<CommentVO> lstBmsComment = bmsCommentService.getCommentsByTopicID(topicid);
        return ApiResult.success(lstBmsComment);
    }

    @ApiOperation(value = "添加评论", notes = "添加评论")
    @PostMapping("/add_comment")
    public ApiResult<BmsComment> create(@ApiParam(value = "当前用户名称", required = true) @RequestHeader(value = JwtUtil.USER_NAME) String userName, @ApiParam(value = "评论内容相关信息", required = true) @RequestBody CommentDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsComment comment = bmsCommentService.create(dto, user);
        return ApiResult.success(comment);
    }

}
