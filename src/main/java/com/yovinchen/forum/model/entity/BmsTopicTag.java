package com.yovinchen.forum.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@ApiModel("话题标签关联")
@TableName("bms_post_tag")
@Accessors(chain = true)
public class BmsTopicTag implements Serializable {
    private static final long serialVersionUID = -5028599844989220715L;

    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("标签id")
    @TableField("tag_id")
    private String tagId;

    @ApiModelProperty("话题id")
    @TableField("topic_id")
    private String topicId;
}
