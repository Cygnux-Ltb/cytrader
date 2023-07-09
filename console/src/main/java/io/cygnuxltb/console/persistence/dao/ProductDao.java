package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product DAO
 *
 * @author yellow013
 */
@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Long> {

    /**
     * @param productId int
     * @return ProductEntity
     */
    ProductEntity queryByProductId(int productId);

    /**
     * @param productName String
     * @return ProductEntity
     */
    ProductEntity queryByProductName(String productName);

}
