package com.yovinchen.forum.controller;

import com.yovinchen.forum.common.api.ApiResult;
import com.yovinchen.forum.model.entity.BmsTip;
import com.yovinchen.forum.service.IBmsTipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "每日一言管理接口")
@RestController
@RequestMapping("/tip")
public class BmsTipController extends BaseController {

    @Resource
    private IBmsTipService bmsTipService;
    /**
     * 获取一条随机的每日一言
     *
     * @return 随机获取到的每日一言信息
     */
    @ApiOperation(value = "获取一条随机的每日一言", notes = "获取一条随机的每日一言")
    @GetMapping("/today")
    public ApiResult<BmsTip> getRandomTip() {
        BmsTip tip = bmsTipService.getRandomTip();
        return ApiResult.success(tip);
    }
}

