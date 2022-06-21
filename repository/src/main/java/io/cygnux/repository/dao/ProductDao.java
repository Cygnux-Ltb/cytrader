package io.cygnux.repository.dao;

import io.cygnux.repository.entities.internal.InProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CygInfo DAO
 *
 * @author yellow013
 */
@Repository
public interface ProductDao extends JpaRepository<InProduct, Integer> {

}
