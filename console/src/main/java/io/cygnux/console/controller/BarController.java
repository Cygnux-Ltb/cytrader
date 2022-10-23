package io.cygnux.console.controller;

import io.cygnux.console.service.BarService;
import io.cygnux.repository.entity.BarEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static io.cygnux.console.utils.ParamsValidateUtil.bodyToObject;
import static io.cygnux.console.utils.ResponseUtil.badRequest;
import static io.cygnux.console.utils.ResponseUtil.internalServerError;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

@RestController("/bar")
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
    @GetMapping
    public List<BarEntity> getBars(@RequestParam("instrumentCode") String instrumentCode,
                                   @RequestParam("tradingDay") int tradingDay) {
        return service.getBars(instrumentCode, tradingDay);
    }

    /**
     * Put Bar
     *
     * @param request HttpServletRequest
     * @return ResponseEntity<Integer>
     */
    @PutMapping(consumes = APPLICATION_JSON_UTF8)
    public ResponseEntity<Integer> putBar(@RequestBody HttpServletRequest request) {
        var bar = bodyToObject(request, BarEntity.class);
        return bar == null ? badRequest()
                : service.putBar(bar)
                ? ResponseEntity.status(HttpStatus.ACCEPTED).build()
                : internalServerError();
    }

}
