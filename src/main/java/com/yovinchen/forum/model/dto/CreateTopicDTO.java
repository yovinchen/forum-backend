package com.yovinchen.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "创建话题DTO")
public class CreateTopicDTO implements Serializable {
    private static final long serialVersionUID = -5957433707110390852L;

    /**
     * 标题
     */
    @ApiModelProperty(value = "话题标题", required = true)
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "话题内容", required = true)
    private String content;

    /**
     * 标签
     */
    @ApiModelProperty(value = "话题标签列表", required = true)
    private List<String> tags;

}
