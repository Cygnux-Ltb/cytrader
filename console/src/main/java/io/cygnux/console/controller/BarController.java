package io.cygnux.console.controller;

import io.cygnux.console.controller.base.BaseController;
import io.cygnux.console.service.BarService;
import io.cygnux.repository.entities.internal.InBar;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static io.mercury.transport.http.base.MimeType.APPLICATION_JSON_UTF8;

@RestController("/bar")
public class BarController extends BaseController {

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
    public List<InBar> getBars(@RequestParam("instrumentCode") String instrumentCode,
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
        InBar bar = bodyToObject(request, InBar.class);
        return bar == null ? badRequest()
                : service.putBar(bar) ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : internalServerError();
    }

}
