package com.yovinchen.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yovinchen.forum.model.entity.BmsTip;

public interface IBmsTipService extends IService<BmsTip> {
    BmsTip getRandomTip();
}
