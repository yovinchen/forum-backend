package com.yovinchen.forum.model.vo;

import com.yovinchen.forum.model.entity.BmsTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@ApiModel("文章")
@NoArgsConstructor
@AllArgsConstructor
public class PostVO implements Serializable {
    private static final long serialVersionUID = -261082150965211545L;

    /**
     * 文章ID
     */
    @ApiModelProperty("文章ID")
    private String id;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private String userId;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String alias;

    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String username;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 评论统计
     */
    @ApiModelProperty("评论统计")
    private Integer comments;

    /**
     * 是否置顶
     */
    @ApiModelProperty("是否置顶")
    private Boolean top;

    /**
     * 是否加精
     */
    @ApiModelProperty("是否加精")
    private Boolean essence;

    /**
     * 收藏次数
     */
    @ApiModelProperty("收藏次数")
    private Integer collects;

    /**
     * 文章关联标签
     */
    @ApiModelProperty("文章关联标签")
    private List<BmsTag> tags;

    /**
     * 浏览量
     */
    @ApiModelProperty("浏览量")
    private Integer view;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date modifyTime;
}
