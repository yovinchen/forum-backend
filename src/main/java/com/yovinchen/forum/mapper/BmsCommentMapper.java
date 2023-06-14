package com.yovinchen.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yovinchen.forum.model.entity.BmsComment;
import com.yovinchen.forum.model.vo.CommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BmsCommentMapper extends BaseMapper<BmsComment> {

    /**
     * getCommentsByTopicID
     *
     * @param topicid
     * @return
     */
    List<CommentVO> getCommentsByTopicID(@Param("topicid") String topicid);
}
