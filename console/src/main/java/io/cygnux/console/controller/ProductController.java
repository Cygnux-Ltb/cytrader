package io.cygnux.console.controller;

import io.cygnux.console.dto.InitFinish;
import io.cygnux.repository.entities.TProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

import static io.cygnux.console.utils.ResponseUtil.*;
import static io.cygnux.console.utils.ParamsValidateUtil.bodyToObject;

@RestController("/product")
public final class ProductController {

    /**
     * 执行具体操作的executor
     */
    @Resource
    private ProductService service;

    /**
     * Get All cygInfo
     *
     * @return
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
    public ResponseEntity<TProduct> getProduct(
            @PathVariable("productId") int productId) {
        TProduct entity = service
        return responseOf(entity);
    }

}
