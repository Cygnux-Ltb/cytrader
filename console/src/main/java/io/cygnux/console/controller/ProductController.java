package io.cygnux.console.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import io.cygnux.console.service.CygInfoService;
import io.cygnux.console.service.dto.InitFinish;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.cygnux.console.controller.base.BaseController;
import io.cygnux.repository.entities.internal.InProduct;

@RestController("/product")
public class CygController extends BaseController {

    /**
     * 执行具体操作的executor
     */
    @Resource
    private CygInfoService service;

    /**
     * Get All cygInfo
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> getAllCygInfo() {
        var list = service.getAll();
        return responseOf(list);
    }

    private static final ConcurrentHashMap<Integer, InitFinish> CygInfoInitFinishCacheMap = new ConcurrentHashMap<>();

    /**
     * @param request
     * @return
     */
    @PutMapping("/initialized")
    public ResponseEntity<Object> putInitFinish(@RequestBody HttpServletRequest request) {
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
    public ResponseEntity<InProduct> getProduct(@PathParam("productId") int productId) {
        InProduct entity = service.get(productId);
        return responseOf(entity);
    }

}
