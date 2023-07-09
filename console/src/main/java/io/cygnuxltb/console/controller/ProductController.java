package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.persistence.entity.ProductEntity;
import io.cygnuxltb.console.service.ProductService;
import io.cygnuxltb.protocol.http.inbound.ControlCommand.InitFinish;
import io.mercury.common.http.MimeType;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 产品服务接口
 */
@RestController
@RequestMapping(path = "/product", produces = MimeType.APPLICATION_JSON_UTF8)
public final class ProductController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(ProductController.class);

    /**
     * 执行具体操作的executor
     */
    @Resource
    private ProductService service;

    private final ConcurrentHashMap<Integer, InitFinish> CygInfoInitFinishCacheMap = new ConcurrentHashMap<>();

    /**
     * 获取全部产品
     *
     * @return ResponseEntity<List < ProductEntity>>
     */
    @GetMapping("/all")
    public List<ProductEntity> getAllProduct() {
        return service.getAll();
    }

    /**
     * 获取指定产品信息
     *
     * @param productId int
     * @return ResponseEntity<ProductEntity>
     */
    @GetMapping
    public ProductEntity getProduct(@RequestParam("productId") int productId) {
        return service.getById(productId);
    }

    /**
     * 产品初始化
     *
     * @param request HttpServletRequest
     * @return ResponseEntity<?>
     */
    @PutMapping("/init")
    public ResponseStatus putInitFinish(@RequestBody HttpServletRequest request) {
        InitFinish initFinish = ControllerUtil.bodyToObject(request, InitFinish.class);
        if (initFinish == null)
            return ResponseStatus.BAD_REQUEST;
        CygInfoInitFinishCacheMap.put(initFinish.getSysId(), initFinish);
        return ResponseStatus.OK;
    }


}
