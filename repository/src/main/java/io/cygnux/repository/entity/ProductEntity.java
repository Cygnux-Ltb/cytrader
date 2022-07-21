package io.cygnux.repository.entity;

import io.cygnux.repository.constant.CommonColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * CygInfo Entity
 *
 * @author yellow013
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "t_product")
@Entity
public final class ProductEntity {

    @Id
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name", length = 128)
    private String productName;

    @Column(name = CommonColumn.INVESTOR_ID)
    private String investorId;

    @Column(name = CommonColumn.BROKER_ID)
    private String brokerId;

    @Column(name = "trader_name", length = 128)
    private String traderName;

    @Column(name = "adaptor_type", length = 32)
    private String interfaceType;

}
