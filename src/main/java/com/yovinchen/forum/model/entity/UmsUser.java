package com.yovinchen.forum.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ApiModel("用户")
@TableName("ums_user")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UmsUser implements Serializable {
    private static final long serialVersionUID = -5051120337175047163L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty("昵称")
    @TableField("alias")
    private String alias;

    @ApiModelProperty(hidden = true)
    @JsonIgnore()
    @TableField("password")
    private String password;

    @ApiModelProperty("头像url")
    @Builder.Default
    @TableField("avatar")
    private String avatar = "https://api.multiavatar.com/1.png";

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("手机号")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty("个人简介")
    @Builder.Default
    @TableField("bio")
    private String bio = "自由职业者";

    @ApiModelProperty("积分")
    @Builder.Default
    @TableField("score")
    private Integer score = 0;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @TableField("token")
    private String token;

    @ApiModelProperty("是否激活")
    @Builder.Default
    @TableField("active")
    private Boolean active = true;

    /**
     * 状态。1:使用，0:已停用
     */
    @ApiModelProperty("状态，1:使用，0:已停用")
    @Builder.Default
    @TableField("`status`")
    private Boolean status = true;

    /**
     * 用户角色
     */
    @ApiModelProperty("用户角色")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty(hidden = true)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(hidden = true)
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;
}
