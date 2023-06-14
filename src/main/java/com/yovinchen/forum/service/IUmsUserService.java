package com.yovinchen.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yovinchen.forum.model.dto.LoginDTO;
import com.yovinchen.forum.model.dto.RegisterDTO;
import com.yovinchen.forum.model.entity.UmsUser;
import com.yovinchen.forum.model.vo.ProfileVO;


public interface IUmsUserService extends IService<UmsUser> {

    /**
     * 注册功能
     *
     * @param dto
     * @return 注册对象
     */
    UmsUser executeRegister(RegisterDTO dto);
    /**
     * 获取用户信息
     *
     * @param username
     * @return dbUser
     */
    UmsUser getUserByUsername(String username);
    /**
     * 用户登录
     *
     * @param dto
     * @return 生成的JWT的token
     */
    String executeLogin(LoginDTO dto);
    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return
     */
    ProfileVO getUserProfile(String id);
}
