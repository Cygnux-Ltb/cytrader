package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.ProductDao;
import io.cygnuxltb.console.persistence.entity.ProductEntity;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;

@Component
public class ProductService {

    @Resource
    private ProductDao dao;

    public List<ProductEntity> getAll() {
        return select(ProductEntity.class,
                () -> dao.findAll());
    }

    public ProductEntity getById(int productId) {
        return dao.queryByProductId(productId);
    }

}
