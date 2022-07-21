package io.cygnux.console.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnux.console.service.PnlService;
import io.cygnux.repository.entity.PnlEntity;
import io.cygnux.repository.entity.PnlSettlementEntity;

import static io.cygnux.console.utils.ResponseUtil.*;
import static io.cygnux.console.utils.ParamsValidateUtil.bodyToObject;
import static io.cygnux.console.utils.ParamsValidateUtil.paramIsNull;

@RestController("/pnl")
public final class PnlRestfulApi {

    @Resource
    private PnlService service;

    /**
     * 查询PNL
     *
     * @param strategyId
     * @param tradingDay
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> getPnl(
            @RequestParam("strategyId") int strategyId,
            @RequestParam("tradingDay") int tradingDay) {
        if (paramIsNull(tradingDay))
            return badRequest();
        return responseOf(service.getPnl(strategyId, tradingDay));
    }

    /**
     * Put PnlDaily
     *
     * @param request
     * @return
     */
    @PutMapping
    public ResponseEntity<Object> putPnlDailys(
            @RequestBody HttpServletRequest request) {
        var pnlDaily = bodyToObject(request, PnlEntity.class);
        return pnlDaily == null ? badRequest() : service.putPnl(pnlDaily) ? ok() : internalServerError();
    }

    /**
     * 查询结算PNL
     *
     * @param strategyId
     * @param tradingDay
     * @return
     */
    @GetMapping("/settlement")
    public ResponseEntity<List<PnlSettlementEntity>> getPnlSettlementDailys(
            @RequestParam("strategyId") int strategyId,
            @RequestParam("tradingDay") int tradingDay) {
        if (paramIsNull(tradingDay))
            return badRequest();
        return responseOf(service.getPnlSettlement(strategyId, tradingDay));
    }

}
