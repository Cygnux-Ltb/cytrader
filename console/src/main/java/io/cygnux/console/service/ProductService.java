package io.cygnux.console.service;

import io.cygnux.console.persistence.dao.ProductDao;
import io.cygnux.console.persistence.entity.ProductEntity;
import io.cygnux.console.persistence.util.DaoExecutor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    @Resource
    private ProductDao productDao;

    public List<ProductEntity> getAll() {
        return DaoExecutor.select(() -> productDao.findAll(), ProductEntity.class);
    }

    public ProductEntity getProduct(int productId) {
        return productDao.queryByProductId(productId);
    }

}
