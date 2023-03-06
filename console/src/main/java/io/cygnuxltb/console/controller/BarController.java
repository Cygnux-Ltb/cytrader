package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ServiceException;
import io.cygnuxltb.console.persistence.entity.BarEntity;
import io.cygnuxltb.console.service.BarService;
import io.cygnuxltb.console.controller.util.RequestUtil;
import io.cygnuxltb.console.controller.util.ResponseUtil;
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
        var bar = RequestUtil.bodyToObject(request, BarEntity.class);
        System.out.println("131451511");
        return bar == null ? ResponseUtil.badRequest()
                : service.putBar(bar)
                ? ResponseEntity.status(HttpStatus.ACCEPTED).build()
                : ResponseUtil.internalServerError();
    }

}
