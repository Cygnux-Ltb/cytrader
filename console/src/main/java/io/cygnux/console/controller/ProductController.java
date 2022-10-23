package io.cygnux.console.controller;

import io.cygnux.console.dto.InitFinish;
import io.cygnux.console.service.ProductService;
import io.cygnux.repository.entity.ProductEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

import static io.cygnux.console.utils.ParamsValidateUtil.bodyToObject;
import static io.cygnux.console.utils.ResponseUtil.badRequest;
import static io.cygnux.console.utils.ResponseUtil.ok;
import static io.cygnux.console.utils.ResponseUtil.responseOf;

@RestController("/product")
public final class ProductController {

    /**
     * 执行具体操作的executor
     */
    @Resource
    private ProductService service;

    /**
     * Get all product
     *
     * @return ResponseEntity<Object>
     */
    @GetMapping
    public ResponseEntity<Object> getAllProduct() {
        var list = service.getAll();
        return responseOf(list);
    }

    private static final ConcurrentHashMap<Integer, InitFinish> CygInfoInitFinishCacheMap = new ConcurrentHashMap<>();

    /**
     * @param request
     * @return
     */
    @PutMapping("/initialized")
    public ResponseEntity<Object> putInitFinish(
            @RequestBody HttpServletRequest request) {
        InitFinish initFinish = bodyToObject(request, InitFinish.class);
        if (initFinish == null)
            return badRequest();
        CygInfoInitFinishCacheMap.put(initFinish.getCygId(), initFinish);
        return ok();

    }

    /**
     * @param productId
     * @return
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductEntity> getProduct(
            @PathVariable("productId") int productId) {
        ProductEntity entity = service.get(productId);
        return responseOf(entity);
    }

}
