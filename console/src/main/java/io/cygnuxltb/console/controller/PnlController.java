package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.persistence.entity.PnlEntity;
import io.cygnuxltb.console.persistence.entity.PnlSettlementEntity;
import io.cygnuxltb.console.service.PnlService;
import io.mercury.common.http.MimeType;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * PNL服务接口
 */
@RestController
@RequestMapping(path = "/pnl", produces = MimeType.APPLICATION_JSON_UTF8)
public final class PnlController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(PnlController.class);

    @Resource
    private PnlService service;

    /**
     * 查询PNL
     *
     * @param strategyId int
     * @param tradingDay int
     * @return ResponseEntity<List < PnlEntity>>
     */
    @GetMapping(path = "/{tradingDay}")
    public List<PnlEntity> getPnl(@PathVariable("tradingDay") int tradingDay,
                                  @RequestParam("strategyId") int strategyId) {
        if (ControllerUtil.paramIsNull(tradingDay))
            throw new IllegalArgumentException("get pnl param error -> " + tradingDay);
        return service.getPnl(strategyId, tradingDay);
    }

    /**
     * Put PnlDaily
     *
     * @param request HttpServletRequest
     * @return ResponseEntity<?>
     */
    @PutMapping(consumes = APPLICATION_JSON_UTF8)
    public ResponseStatus putPnl(@RequestBody HttpServletRequest request) {
        var pnlDaily = ControllerUtil.bodyToObject(request, PnlEntity.class);
        return pnlDaily == null
                ? ResponseStatus.BAD_REQUEST : service.putPnl(pnlDaily)
                ? ResponseStatus.OK : ResponseStatus.INTERNAL_ERROR;
    }

    /**
     * 查询结算PNL
     *
     * @param strategyId int
     * @param tradingDay int
     * @return ResponseEntity<List < PnlSettlementEntity>>
     */
    @GetMapping("/settlement")
    public List<PnlSettlementEntity> getPnlSettlement(@RequestParam("strategyId") int strategyId,
                                                      @RequestParam("tradingDay") int tradingDay) {
        if (ControllerUtil.illegalTradingDay(tradingDay, log))
            throw new IllegalArgumentException("");
        return (service.getPnlSettlement(strategyId, tradingDay));
    }

}
