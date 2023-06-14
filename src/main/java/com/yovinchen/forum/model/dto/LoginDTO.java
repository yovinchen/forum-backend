package com.yovinchen.forum.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Data
@ApiModel(description = "登录DTO")
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 602891322506543807L;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 15, message = "登录用户名长度在2-15")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "登录密码长度在6-20")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "记住我", example = "true")
    private Boolean rememberMe;
}
