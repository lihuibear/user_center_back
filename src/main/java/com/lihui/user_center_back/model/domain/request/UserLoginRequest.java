package com.lihui.user_center_back.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 *
 * @author lihui
 */
@Data
public class UserLoginRequest implements Serializable {
    //序列化id
    public static final long seriaVersionUID = 3191241716373120793L;
    private String userAccount;
    private String userPassword;

}
