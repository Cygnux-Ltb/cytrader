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

import io.cygnux.console.controller.base.BaseController;
import io.cygnux.console.service.PnlService;
import io.cygnux.repository.entities.internal.InPnlDaily;
import io.cygnux.repository.entities.internal.InPnlDailySettlement;

@RestController("/pnl")
public class PnlRestfulApi extends BaseController {

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
    public ResponseEntity<Object> getPnl(@RequestParam("strategyId") int strategyId,
                                         @RequestParam("tradingDay") int tradingDay) {
        if (checkParamIsNull(tradingDay))
            return badRequest();
        return responseOf(service.getPnlDailys(strategyId, tradingDay));
    }

    /**
     * Put PnlDaily
     *
     * @param request
     * @return
     */
    @PutMapping
    public ResponseEntity<Object> putPnlDailys(@RequestBody HttpServletRequest request) {
        var pnlDaily = bodyToObject(request, InPnlDaily.class);
        return pnlDaily == null ? badRequest() : service.putPnlDaily(pnlDaily) ? ok() : internalServerError();
    }

    /**
     * 查询结算PNL
     *
     * @param strategyId
     * @param tradingDay
     * @return
     */
    @GetMapping("/settlement")
    public ResponseEntity<List<InPnlDailySettlement>> getPnlSettlementDailys(
            @RequestParam("strategyId") int strategyId, @RequestParam("tradingDay") int tradingDay) {
        if (checkParamIsNull(tradingDay))
            return badRequest();
        return responseOf(service.getPnlDailySettlements(strategyId, tradingDay));
    }

}
