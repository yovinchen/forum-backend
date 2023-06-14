package com.yovinchen.forum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yovinchen.forum.mapper.BmsBillboardMapper;
import com.yovinchen.forum.model.entity.BmsBillboard;
import com.yovinchen.forum.service.IBmsBillboardService;
import org.springframework.stereotype.Service;

@Service
public class IBmsBillboardServiceImpl extends ServiceImpl<BmsBillboardMapper
        , BmsBillboard> implements IBmsBillboardService {

}
