package io.cygnux.repository.entities;

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
@Table(name = "product")
@Entity(name = ItProduct.EntityName)
public final class ItProduct {

    public final static String EntityName = "ItProduct";

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
