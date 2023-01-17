package io.cygnux.console.persistence.entity;

import io.cygnux.console.persistence.constant.CommonQueryColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * CygInfo Entity
 *
 * @author yellow013
 */
@Data
@Entity
@Table(name = "cyg_product")
public final class ProductEntity {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = CommonQueryColumn.SUB_ACCOUNT_ID)
    private String subAccountId;

    @Column(name = CommonQueryColumn.USER_ID)
    private String userId;

    @Column(name = "adaptor_type")
    private String interfaceType;

}
