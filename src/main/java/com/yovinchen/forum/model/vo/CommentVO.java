package com.yovinchen.forum.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
@ApiModel("评论")
public class CommentVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String id;

    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    private String content;

    /**
     * 所属话题id
     */
    @ApiModelProperty("所属话题id")
    private String topicId;

    /**
     * 评论用户id
     */
    @ApiModelProperty("评论用户id")
    private String userId;

    /**
     * 评论用户名
     */
    @ApiModelProperty("评论用户名")
    private String username;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
}
