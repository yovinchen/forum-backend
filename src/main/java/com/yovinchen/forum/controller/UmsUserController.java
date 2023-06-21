package com.yovinchen.forum.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yovinchen.forum.common.api.ApiResult;
import com.yovinchen.forum.jwt.JwtUtil;
import com.yovinchen.forum.model.dto.LoginDTO;
import com.yovinchen.forum.model.dto.RegisterDTO;
import com.yovinchen.forum.model.entity.BmsPost;
import com.yovinchen.forum.model.entity.UmsUser;
import com.yovinchen.forum.service.IBmsPostService;
import com.yovinchen.forum.service.IUmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/ums/user")
public class UmsUserController extends BaseController {
    @Resource
    private IUmsUserService iUmsUserService;
    @Resource
    private IBmsPostService iBmsPostService;

    @ApiOperation(value = "用户注册", notes = "用户注册接口")
    @PostMapping("/register")
    public ApiResult<Map<String, Object>> register(@ApiParam(name = "dto", value = "注册信息", required = true) @Valid @RequestBody RegisterDTO dto) {
        UmsUser user = iUmsUserService.executeRegister(dto);
        if (ObjectUtils.isEmpty(user)) {
            return ApiResult.failed("账号注册失败");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        return ApiResult.success(map);
    }

    @ApiOperation(value = "用户登陆", notes = "用户登陆接口")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult<Map<String, String>> login(@ApiParam(name = "dto", value = "登陆信息", required = true) @Valid @RequestBody LoginDTO dto) {
        String token = iUmsUserService.executeLogin(dto);
        if (ObjectUtils.isEmpty(token)) {
            return ApiResult.failed("账号密码错误");
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("token", token);
        return ApiResult.success(map, "登录成功");
    }

    @ApiOperation(value = "获取当前用户登陆信息", notes = "获取当前用户登陆信息接口")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ApiResult<UmsUser> getUser(@ApiParam(name = "userName", value = "用户名", required = true) @RequestHeader(value = JwtUtil.USER_NAME) String userName) {
        UmsUser user = iUmsUserService.getUserByUsername(userName);
        return ApiResult.success(user);
    }

    @ApiOperation(value = "登出", notes = "用户登出接口")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ApiResult<Object> logOut() {
        return ApiResult.success(null, "注销成功");
    }

    @ApiOperation(value = "根据用户名获取该用户发布的文章列表", notes = "通过用户名获取文章列表")
    @GetMapping("/{username}")
    public ApiResult<Map<String, Object>> getUserByName(@ApiParam(name = "username", value = "用户名", required = true) @PathVariable("username") String username, @ApiParam(name = "pageNo", value = "页码", defaultValue = "1") @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, @ApiParam(name = "size", value = "每页大小", defaultValue = "10") @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> map = new HashMap<>(16);
        UmsUser user = iUmsUserService.getUserByUsername(username);
        Assert.notNull(user, "用户不存在");
        Page<BmsPost> page;
        if (Objects.equals(user.getUsername(), "admin")) {
            page = iBmsPostService.page(new Page<>(pageNo, size), null);
        } else {
            page = iBmsPostService.page(new Page<>(pageNo, size), new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getUserId, user.getId()));

        }
        map.put("user", user);
        map.put("topics", page);
        return ApiResult.success(map);
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息接口")
    @PostMapping("/update")
    public ApiResult<UmsUser> updateUser(@ApiParam(name = "umsUser", value = "用户信息", required = true) @RequestBody UmsUser umsUser) {
        iUmsUserService.updateById(umsUser);
        return ApiResult.success(umsUser);
    }
}

