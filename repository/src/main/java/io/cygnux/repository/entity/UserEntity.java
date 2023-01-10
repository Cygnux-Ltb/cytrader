package io.cygnux.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.cygnux.repository.constant.CommonQueryColumn;
import lombok.Data;

@Data
@TableName("cyg_user")
public class UserEntity {

    @TableId(type = IdType.AUTO)
    private long uid;

    @TableField(CommonQueryColumn.USER_ID)
    private int userId;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("email")
    private String email;

    @TableField("phone")
    private String phone;

    @TableField(CommonQueryColumn.SUB_ACCOUNT_ID)
    private int subAccountId;

}
