package com.yovinchen.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel(description = "评论DTO")
public class CommentDTO implements Serializable {

    private static final long serialVersionUID = -5957433707110390852L;

    @ApiModelProperty(value = "话题id", required = true)
    private String topic_id;

    /**
     * 内容
     */
    @ApiModelProperty(value = "评论内容", required = true)
    private String content;

}
