package com.yovinchen.forum.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Builder
@ApiModel("标签")
@TableName("bms_tag")
@Accessors(chain = true)
public class BmsTag implements Serializable {
    private static final long serialVersionUID = 3257790983905872243L;

    @ApiModelProperty("主键")
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;
    /**
     * 当前标签下的话题个数
     */
    @ApiModelProperty("当前标签下的话题个数")
    @TableField("topic_count")
    @Builder.Default
    private Integer topicCount = 1;
}
