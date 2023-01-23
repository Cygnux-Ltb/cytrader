package io.cygnux.console.controller;

import io.cygnux.console.persistence.entity.PnlEntity;
import io.cygnux.console.persistence.entity.PnlSettlementEntity;
import io.cygnux.console.service.PnlService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.cygnux.console.controller.util.ParamsValidateUtil.bodyToObject;
import static io.cygnux.console.controller.util.ParamsValidateUtil.paramIsNull;
import static io.cygnux.console.controller.util.ResponseUtil.badRequest;
import static io.cygnux.console.controller.util.ResponseUtil.internalServerError;
import static io.cygnux.console.controller.util.ResponseUtil.ok;
import static io.cygnux.console.controller.util.ResponseUtil.responseOf;

@RestController("/pnl")
public final class PnlRestfulApi {

    @Resource
    private PnlService service;

    /**
     * 查询PNL
     *
     * @param strategyId int
     * @param tradingDay int
     * @return ResponseEntity<List < PnlEntity>>
     */
    @GetMapping
    public ResponseEntity<List<PnlEntity>> getPnl(@RequestParam("strategyId") int strategyId,
                                                  @RequestParam("tradingDay") int tradingDay) {
        if (paramIsNull(tradingDay))
            return badRequest();
        return responseOf(service.getPnl(strategyId, tradingDay));
    }

    /**
     * Put PnlDaily
     *
     * @param request HttpServletRequest
     * @return ResponseEntity<?>
     */
    @PutMapping
    public ResponseEntity<?> putPnl(@RequestBody HttpServletRequest request) {
        var pnlDaily = bodyToObject(request, PnlEntity.class);
        return pnlDaily == null
                ? badRequest() : service.putPnl(pnlDaily)
                ? ok() : internalServerError();
    }

    /**
     * 查询结算PNL
     *
     * @param strategyId int
     * @param tradingDay int
     * @return ResponseEntity<List < PnlSettlementEntity>>
     */
    @GetMapping("/settlement")
    public ResponseEntity<List<PnlSettlementEntity>> getPnlSettlementDaily(
            @RequestParam("strategyId") int strategyId, @RequestParam("tradingDay") int tradingDay) {
        if (paramIsNull(tradingDay))
            return badRequest();
        return responseOf(service.getPnlSettlement(strategyId, tradingDay));
    }

}
