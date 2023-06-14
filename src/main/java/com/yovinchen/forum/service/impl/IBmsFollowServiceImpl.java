package com.yovinchen.forum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yovinchen.forum.mapper.BmsFollowMapper;
import com.yovinchen.forum.model.entity.BmsFollow;
import com.yovinchen.forum.service.IBmsFollowService;
import org.springframework.stereotype.Service;


@Service
public class IBmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow> implements IBmsFollowService {
}
