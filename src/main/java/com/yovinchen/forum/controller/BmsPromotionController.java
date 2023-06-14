package com.yovinchen.forum.controller;

import com.yovinchen.forum.common.api.ApiResult;
import com.yovinchen.forum.model.entity.BmsPromotion;
import com.yovinchen.forum.service.IBmsPromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@Api(tags = "推广管理接口")
@RestController
@RequestMapping("/promotion")
public class BmsPromotionController extends BaseController {

    @Resource
    private IBmsPromotionService bmsPromotionService;

    @ApiOperation(value = "获取所有推广信息", notes = "获取所有推广信息列表")
    @GetMapping("/all")
    public ApiResult<List<BmsPromotion>> list() {
        List<BmsPromotion> list = bmsPromotionService.list();
        return ApiResult.success(list);
    }

}


