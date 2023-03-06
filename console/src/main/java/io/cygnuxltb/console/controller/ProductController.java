package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ServiceException;
import io.cygnuxltb.console.persistence.entity.ProductEntity;
import io.cygnuxltb.console.service.ProductService;
import io.cygnuxltb.console.service.dto.InitFinish;
import io.cygnuxltb.console.controller.util.RequestUtil;
import io.cygnuxltb.console.controller.util.ResponseUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public final class ProductController {

    /**
     * 执行具体操作的executor
     */
    @Resource
    private ProductService service;

    private static final ConcurrentHashMap<Integer, InitFinish> CygInfoInitFinishCacheMap = new ConcurrentHashMap<>();

    /**
     * Get all product
     *
     * @return ResponseEntity<List < ProductEntity>>
     */
    @ExceptionHandler(ServiceException.class)
    @GetMapping("/product")
    public ResponseEntity<List<ProductEntity>> getAllProduct() {
        var list = service.getAll();
        return ResponseUtil.responseOf(list);
    }

    /**
     * @param request HttpServletRequest
     * @return ResponseEntity<?>
     */
    @ExceptionHandler(ServiceException.class)
    @PutMapping("/product/initialized")
    public ResponseEntity<?> putInitFinish(@RequestBody HttpServletRequest request) {
        InitFinish initFinish = RequestUtil.bodyToObject(request, InitFinish.class);
        if (initFinish == null)
            return ResponseUtil.badRequest();
        CygInfoInitFinishCacheMap.put(initFinish.getCygId(), initFinish);
        return ResponseUtil.ok();
    }

    /**
     * @param productId int
     * @return ResponseEntity<ProductEntity>
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable("productId") int productId) {
        ProductEntity entity = service.getProduct(productId);
        return ResponseUtil.responseOf(entity);
    }

}
