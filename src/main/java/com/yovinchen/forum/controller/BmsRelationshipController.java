package com.yovinchen.forum.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yovinchen.forum.common.api.ApiResult;
import com.yovinchen.forum.common.exception.ApiAsserts;
import com.yovinchen.forum.jwt.JwtUtil;
import com.yovinchen.forum.model.entity.BmsFollow;
import com.yovinchen.forum.model.entity.UmsUser;
import com.yovinchen.forum.service.IBmsFollowService;
import com.yovinchen.forum.service.IUmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "关注管理接口")
@RestController
@RequestMapping("/relationship")
public class BmsRelationshipController extends BaseController {

    @Resource
    private IBmsFollowService bmsFollowService;

    @Resource
    private IUmsUserService umsUserService;

    @GetMapping("/subscribe/{userId}")
    @ApiOperation(value = "关注用户", notes = "通过传入用户ID，让当前用户关注该用户")
    public ApiResult<Object> handleFollow(@RequestHeader(value = JwtUtil.USER_NAME) String userName, @PathVariable("userId") @ApiParam(name = "userId", value = "用户ID", required = true, example = "12345") String parentId) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        if (parentId.equals(umsUser.getId())) {
            ApiAsserts.fail("您脸皮太厚了，怎么可以关注自己呢 😮");
        }
        BmsFollow one = bmsFollowService.getOne(new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, parentId).eq(BmsFollow::getFollowerId, umsUser.getId()));
        if (!ObjectUtils.isEmpty(one)) {
            ApiAsserts.fail("已关注");
        }

        BmsFollow follow = new BmsFollow();
        follow.setParentId(parentId);
        follow.setFollowerId(umsUser.getId());
        bmsFollowService.save(follow);
        return ApiResult.success(null, "关注成功");
    }

    @GetMapping("/unsubscribe/{userId}")
    @ApiOperation(value = "取消关注用户", notes = "通过传入用户ID，让当前用户取消关注该用户")
    public ApiResult<Object> handleUnFollow(@RequestHeader(value = JwtUtil.USER_NAME) String userName, @PathVariable("userId") @ApiParam(name = "userId", value = "用户ID", required = true, example = "12345") String parentId) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        BmsFollow one = bmsFollowService.getOne(new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, parentId).eq(BmsFollow::getFollowerId, umsUser.getId()));
        if (ObjectUtils.isEmpty(one)) {
            ApiAsserts.fail("未关注！");
        }
        bmsFollowService.remove(new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, parentId).eq(BmsFollow::getFollowerId, umsUser.getId()));
        return ApiResult.success(null, "取关成功");
    }

    @GetMapping("/validate/{topicUserId}")
    @ApiOperation(value = "查询是否关注该用户", notes = "通过传入用户ID，查询当前用户是否已经关注该用户，返回结果为布尔类型")
    public ApiResult<Map<String, Object>> isFollow(@RequestHeader(value = JwtUtil.USER_NAME) String userName, @PathVariable("topicUserId") @ApiParam(name = "topicUserId", value = "用户ID", required = true, example = "12345") String topicUserId) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        Map<String, Object> map = new HashMap<>(16);
        map.put("hasFollow", false);
        if (!ObjectUtils.isEmpty(umsUser)) {
            BmsFollow one = bmsFollowService.getOne(new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, topicUserId).eq(BmsFollow::getFollowerId, umsUser.getId()));
            if (!ObjectUtils.isEmpty(one)) {
                map.put("hasFollow", true);
            }
        }
        return ApiResult.success(map);
    }
}
