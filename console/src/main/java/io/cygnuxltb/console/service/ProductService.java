package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.ProductDao;
import io.cygnuxltb.console.persistence.entity.ProductEntity;
import io.cygnuxltb.console.persistence.util.DaoExecutor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    @Resource
    private ProductDao dao;

    public List<ProductEntity> getAll() {
        return DaoExecutor.select(() -> dao.findAll(), ProductEntity.class);
    }

    public ProductEntity getProduct(int productId) {
        return dao.queryByProductId(productId);
    }

}
