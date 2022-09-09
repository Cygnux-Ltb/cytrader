package io.cygnux.console.service;

import io.cygnux.repository.entity.ProductEntity;
import io.mercury.common.log.Log4j2LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(ProductService.class);

    public List<ProductEntity> getAll() {
        return null;
    }

    public ProductEntity get(int productId) {
        return null;
    }

}
