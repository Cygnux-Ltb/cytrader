package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.service.InstrumentService;
import io.cygnuxltb.protocol.http.inbound.InstrumentPrice;
import io.cygnuxltb.protocol.http.outbound.InstrumentDTO;
import io.cygnuxltb.protocol.http.outbound.InstrumentSettlementDTO;
import io.mercury.common.http.MimeType;
import io.mercury.common.lang.Throws;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.util.StringSupport;
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

/**
 * 交易标的查询接口
 */
@RestController
@RequestMapping(path = "/instrument", produces = MimeType.APPLICATION_JSON_UTF8)
public final class InstrumentController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(InstrumentController.class);

    @Resource
    private InstrumentService service;


    /**
     * 获取结算价格
     *
     * @param tradingDay     int
     * @param instrumentCode String
     * @return List<InstrumentSettlementDTO>
     */
    @GetMapping(path = "/settlement")
    public List<InstrumentSettlementDTO> getSettlementPrice(
            @RequestParam("tradingDay") int tradingDay,
            @RequestParam("instrumentCode") String instrumentCode) {
        if (ControllerUtil.paramIsNull(instrumentCode, tradingDay)) {
            return null;
        }
        return service.getInstrumentSettlement(tradingDay, instrumentCode);
    }


    /**
     * 获取最新价格
     *
     * @param instrumentCodes String
     * @return ResponseEntity<List < InstrumentPrice>>
     */
    @GetMapping(path = "/last")
    public List<InstrumentPrice> getLastPrice(
            @RequestParam("instrumentCodes") String instrumentCodes) {
        if (StringSupport.isNullOrEmpty(instrumentCodes))
            Throws.illegalArgument("instrumentCodes");
        return service.getLastPrice(instrumentCodes.split(","));
    }

    /**
     * 更新最新价格
     *
     * @param request HttpServletRequest
     * @return ResponseEntity<Object>
     */
    @PutMapping(path = "/last", produces = MimeType.APPLICATION_JSON_UTF8)
    public ResponseStatus putLastPrice(@RequestBody HttpServletRequest request) {
        var price = ControllerUtil.bodyToObject(request, InstrumentPrice.class);
        if (price == null)
            return ResponseStatus.BAD_REQUEST;
        service.putLastPrice(price.getInstrumentCode(), price.getLastPrice());
        return ResponseStatus.OK;
    }

    /**
     * 获取交易费用
     *
     * @param instrumentCode String
     * @return ResponseEntity<Object>
     */
    public List<InstrumentDTO> getSymbolTradingFeeByName(
            @RequestParam("instrumentCode") String instrumentCode) {
        return service.getInstrument(instrumentCode);
    }


    /**
     * 获取可交易的标的
     *
     * @param symbol     String
     * @param tradingDay String
     * @return ResponseEntity<Object>
     */
    @GetMapping(path = "/tradable/{tradingDay}/{symbol}")
    public ResponseStatus getTradableInstrument(@PathVariable("tradingDay") int tradingDay,
                                                @PathVariable("symbol") String symbol) {
        return ResponseStatus.OK;
    }

}
