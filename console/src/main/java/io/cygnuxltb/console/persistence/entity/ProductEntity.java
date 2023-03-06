package io.cygnuxltb.console.persistence.entity;

import io.cygnuxltb.console.persistence.CommonColumn;
import io.mercury.persistence.rdb.ColumnDefinition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Product Entity
 *
 * @author yellow013
 */
@Data
@Entity
@Table(name = "cyg_product")
public final class ProductEntity {

    @Id
    @Column(name = ColumnDefinition.UID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = CommonColumn.SUB_ACCOUNT_ID)
    private String subAccountId;

    @Column(name = CommonColumn.USER_ID)
    private String userId;

    @Column(name = "adaptor_type")
    private String interfaceType;

}
