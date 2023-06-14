package com.yovinchen.forum.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@ApiModel("小贴士")
@NoArgsConstructor
@TableName("bms_tip")
public class BmsTip implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    @TableField("`content`")
    private String content;

    /**
     * 作者
     */
    @ApiModelProperty("作者")
    private String author;

    /**
     * 1：使用，0：过期
     */
    @ApiModelProperty("状态(1-使用，0-过期)")
    private boolean type;

}
