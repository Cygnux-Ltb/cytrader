package io.cygnux.console.controller;

import io.cygnux.console.persistence.entity.ProductEntity;
import io.cygnux.console.service.ProductService;
import io.cygnux.console.service.dto.InitFinish;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static io.cygnux.console.controller.util.ParamsValidateUtil.bodyToObject;
import static io.cygnux.console.controller.util.ResponseUtil.badRequest;
import static io.cygnux.console.controller.util.ResponseUtil.ok;
import static io.cygnux.console.controller.util.ResponseUtil.responseOf;

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
     * @return ResponseEntity<List<ProductEntity>>
     */
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProduct() {
        var list = service.getAll();
        return responseOf(list);
    }

    private static final ConcurrentHashMap<Integer, InitFinish> CygInfoInitFinishCacheMap = new ConcurrentHashMap<>();

    /**
     * @param request HttpServletRequest
     * @return ResponseEntity<?>
     */
    @PutMapping("/initialized")
    public ResponseEntity<?> putInitFinish(@RequestBody HttpServletRequest request) {
        InitFinish initFinish = bodyToObject(request, InitFinish.class);
        if (initFinish == null)
            return badRequest();
        CygInfoInitFinishCacheMap.put(initFinish.getCygId(), initFinish);
        return ok();

    }

    /**
     * @param productId int
     * @return ResponseEntity<ProductEntity>
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductEntity> getProduct(
            @PathVariable("productId") int productId) {
        ProductEntity entity = service.getProduct(productId);
        return responseOf(entity);
    }

}
