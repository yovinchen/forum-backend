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
@ApiModel("广告活动")
@TableName("bms_promotion")
@Accessors(chain = true)
public class BmsPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 广告标题
     */
    @ApiModelProperty("广告标题")
    @TableField("title")
    private String title;

    /**
     * 广告链接
     */
    @ApiModelProperty("广告链接")
    @TableField("link")
    private String link;

    /**
     * 说明
     */
    @ApiModelProperty("说明")
    @TableField("`description`")
    private String description;

    public BmsPromotion() {
    }

}
