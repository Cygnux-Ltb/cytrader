package io.cygnux.console.controller;

import io.cygnux.console.service.BarService;
import io.cygnux.repository.entities.StBar;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static io.cygnux.console.utils.ControllerUtil.badRequest;
import static io.cygnux.console.utils.ControllerUtil.internalServerError;
import static io.cygnux.console.utils.ParamsValidateUtil.bodyToObject;
import static io.mercury.transport.http.base.MimeType.APPLICATION_JSON_UTF8;

@RestController("/bar")
public final class BarController {

    @Resource
    private BarService service;

    /**
     * Get Bars
     *
     * @param instrumentCode
     * @param tradingDay
     * @return
     */
    @GetMapping
    public List<StBar> getBars(@RequestParam("instrumentCode") String instrumentCode,
                               @RequestParam("tradingDay") int tradingDay) {
        return service.getBars(instrumentCode, tradingDay);
    }

    /**
     * Put Bar
     *
     * @param request
     * @return
     */
    @PutMapping(consumes = APPLICATION_JSON_UTF8)
    public ResponseEntity<Integer> putBar(@RequestBody HttpServletRequest request) {
        var bar = bodyToObject(request, StBar.class);
        return bar == null ? badRequest()
                : service.putBar(bar) ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : internalServerError();
    }

}
