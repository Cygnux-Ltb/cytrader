package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ServiceException;
import io.cygnuxltb.console.controller.util.RequestUtil;
import io.cygnuxltb.console.controller.util.ResponseUtil;
import io.cygnuxltb.console.persistence.entity.PnlEntity;
import io.cygnuxltb.console.persistence.entity.PnlSettlementEntity;
import io.cygnuxltb.console.service.PnlService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping(path = "/pnl")
public final class PnlController {

    @Resource
    private PnlService service;

    /**
     * 查询PNL
     *
     * @param strategyId int
     * @param tradingDay int
     * @return ResponseEntity<List < PnlEntity>>
     */
    @ExceptionHandler(ServiceException.class)
    @GetMapping(path = "/{tradingDay}")
    public ResponseEntity<List<PnlEntity>> getPnl(@PathVariable("tradingDay") int tradingDay,
                                                  @RequestParam("strategyId") int strategyId) {
        if (RequestUtil.paramIsNull(tradingDay))
            return ResponseUtil.badRequest();
        return ResponseUtil.responseOf(service.getPnl(strategyId, tradingDay));
    }

    /**
     * Put PnlDaily
     *
     * @param request HttpServletRequest
     * @return ResponseEntity<?>
     */
    @ExceptionHandler(ServiceException.class)
    @PutMapping(consumes = APPLICATION_JSON_UTF8)
    public ResponseEntity<?> putPnl(@RequestBody HttpServletRequest request) {
        var pnlDaily = RequestUtil.bodyToObject(request, PnlEntity.class);
        return pnlDaily == null
                ? ResponseUtil.badRequest() : service.putPnl(pnlDaily)
                ? ResponseUtil.ok() : ResponseUtil.internalServerError();
    }

    /**
     * 查询结算PNL
     *
     * @param strategyId int
     * @param tradingDay int
     * @return ResponseEntity<List < PnlSettlementEntity>>
     */
    @ExceptionHandler(ServiceException.class)
    @GetMapping("/settlement")
    public ResponseEntity<List<PnlSettlementEntity>> getPnlSettlementDaily(
            @RequestParam("strategyId") int strategyId, @RequestParam("tradingDay") int tradingDay) {
        if (RequestUtil.paramIsNull(tradingDay))
            return ResponseUtil.badRequest();
        return ResponseUtil.responseOf(service.getPnlSettlement(strategyId, tradingDay));
    }

}
