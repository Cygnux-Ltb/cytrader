package io.cygnux.console.controller;

import io.cygnux.console.controller.base.ServiceException;
import io.cygnux.console.persistence.entity.BarEntity;
import io.cygnux.console.service.BarService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.cygnux.console.controller.util.RequestUtil.bodyToObject;
import static io.cygnux.console.controller.util.ResponseUtil.badRequest;
import static io.cygnux.console.controller.util.ResponseUtil.internalServerError;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

@RestController
@RequestMapping(path = "/bar")
public final class BarController {

    @Resource
    private BarService service;

    /**
     * Get Bars
     *
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<BarEntity>
     */
    @ExceptionHandler(ServiceException.class)
    @GetMapping(path = "/{tradingDay}")
    public List<BarEntity> getBars(@PathVariable("tradingDay") int tradingDay,
                                   @RequestParam("instrumentCode") String instrumentCode) {
        System.out.println(tradingDay);
        return service.getBars(instrumentCode, tradingDay);
    }

    /**
     * Put Bar
     *
     * @param request HttpServletRequest
     * @return ResponseEntity<Integer>
     */
    @ExceptionHandler(ServiceException.class)
    @PutMapping(consumes = APPLICATION_JSON_UTF8)
    public ResponseEntity<Integer> putBar(@RequestBody HttpServletRequest request) {
        var bar = bodyToObject(request, BarEntity.class);
        return bar == null ? badRequest()
                : service.putBar(bar)
                ? ResponseEntity.status(HttpStatus.ACCEPTED).build()
                : internalServerError();
    }

}
