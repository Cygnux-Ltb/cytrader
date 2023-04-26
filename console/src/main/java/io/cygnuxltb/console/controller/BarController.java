package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.persistence.entity.BarEntity;
import io.cygnuxltb.console.service.BarService;
import io.cygnuxltb.protocol.http.outbound.BarM1DTO;
import io.mercury.common.http.MimeType;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * 历史行情接口
 */
@RestController
@RequestMapping(path = "/bar", produces = MimeType.APPLICATION_JSON_UTF8)
public final class BarController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(BarController.class);

    @Resource
    private BarService service;

    /**
     * 获取1分钟Bar
     *
     * @param tradingDay     交易日
     * @param instrumentCode 标的代码
     * @return List<BarEntity>
     * @apiNote 获取行情Bar
     */
    @GetMapping
    public List<BarM1DTO> getBars(@RequestParam("tradingDay") int tradingDay,
                                  @RequestParam("instrumentCode") String instrumentCode) {
        log.info("get bars with : tradingDay -> {}, instrumentCode -> {}", tradingDay, instrumentCode);
        return service.getBars(instrumentCode, tradingDay);
    }

    /**
     * Put Bar
     *
     * @param request HttpServletRequest
     * @return ResponseEntity<Integer>
     * @apiNote 添加行情Bar
     */
    @PostMapping(consumes = APPLICATION_JSON_UTF8)
    public ResponseStatus putBar(@RequestBody HttpServletRequest request) {
        var bar = ControllerUtil.bodyToObject(request, BarEntity.class);
        log.info("put bar -> {}", bar);
        return bar == null ? ResponseStatus.BAD_REQUEST
                : service.putBar(bar)
                ? ResponseStatus.CREATED
                : ResponseStatus.INTERNAL_ERROR;
    }

}
