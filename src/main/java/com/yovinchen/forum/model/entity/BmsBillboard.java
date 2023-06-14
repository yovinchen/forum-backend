package com.yovinchen.forum.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@ApiModel("公告牌")
@Accessors(chain = true)
@TableName("bms_billboard")
@NoArgsConstructor
@AllArgsConstructor
public class BmsBillboard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 公告牌
     */
    @ApiModelProperty("公告内容")
    @TableField("content")
    private String content;

    /**
     * 公告时间
     */
    @ApiModelProperty("公告时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 1：展示中，0：过期
     */
    @ApiModelProperty("展示状态，false：过期，true：展示中")
    @Builder.Default
    @TableField("`show`")
    private boolean show = false;

}
