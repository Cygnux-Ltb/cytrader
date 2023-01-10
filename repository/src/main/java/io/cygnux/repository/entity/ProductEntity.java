package io.cygnux.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.cygnux.repository.constant.CommonQueryColumn;
import lombok.Data;

/**
 * CygInfo Entity
 *
 * @author yellow013
 */
@Data
@TableName("cyg_product")
public final class ProductEntity {

    @TableId(type = IdType.AUTO)
    private long uid;

    @TableField("product_id")
    private int productId;

    @TableField("product_name")
    private String productName;

    @TableField(CommonQueryColumn.SUB_ACCOUNT_ID)
    private String subAccountId;

    @TableField(CommonQueryColumn.USER_ID)
    private String userId;

    @TableField("adaptor_type")
    private String interfaceType;

}
