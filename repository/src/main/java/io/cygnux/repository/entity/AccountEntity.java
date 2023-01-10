package io.cygnux.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.cygnux.repository.constant.CommonQueryColumn;
import lombok.Data;

/**
 * Account database entity
 *
 * @author yellow013
 */
@Data
@TableName("cyg_account")
public final class AccountEntity {

    @TableId(type = IdType.AUTO)
    private long uid;

    @TableField(CommonQueryColumn.ACCOUNT_ID)
    private int accountId;

    @TableField(CommonQueryColumn.SUB_ACCOUNT_ID)
    private int subAccountId;

    @TableField(CommonQueryColumn.BROKER)
    private String broker;

    @TableField(CommonQueryColumn.INVESTOR_ID)
    private String investorId;

    @TableField("adaptor_type")
    private String adaptorType;

}
