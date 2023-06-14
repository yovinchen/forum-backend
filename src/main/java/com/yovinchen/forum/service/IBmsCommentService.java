package com.yovinchen.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yovinchen.forum.model.dto.CommentDTO;
import com.yovinchen.forum.model.entity.BmsComment;
import com.yovinchen.forum.model.entity.UmsUser;
import com.yovinchen.forum.model.vo.CommentVO;

import java.util.List;


public interface IBmsCommentService extends IService<BmsComment> {
    /**
     *
     *
     * @param topicid
     * @return {@link BmsComment}
     */
    List<CommentVO> getCommentsByTopicID(String topicid);

    BmsComment create(CommentDTO dto, UmsUser principal);
}
