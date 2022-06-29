package io.cygnux.console.controller;

import io.cygnux.console.service.InstrumentService;
import io.cygnux.console.service.dto.InstrumentPrice;
import io.cygnux.repository.entities.StInstrumentStatic;
import io.mercury.common.util.StringSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static io.cygnux.console.utils.ControllerUtil.*;
import static io.cygnux.console.utils.ParamsValidateUtil.bodyToObject;
import static io.cygnux.console.utils.ParamsValidateUtil.paramIsNull;
import static java.util.Arrays.stream;

@RestController("/instrument")
public final class InstrumentController {

    @Resource
    private InstrumentService service;

    /**
     * Get Settlement Price
     *
     * @param instrumentCode
     * @param tradingDay
     * @return
     */
    @GetMapping("/static_info")
    public ResponseEntity<List<StInstrumentStatic>> getSettlementPrice(
            @RequestParam("instrumentCode") String instrumentCode,
            @RequestParam("tradingDay") int tradingDay) {
        if (paramIsNull(instrumentCode, tradingDay)) {
            return badRequest();
        }
        var instrumentSettlements = service.getInstrumentStatic(instrumentCode, tradingDay);
        return responseOf(instrumentSettlements);
    }

    // LastPrices Cache
    private static final ConcurrentHashMap<String, InstrumentPrice> lastPriceMap = new ConcurrentHashMap<>();

    /**
     * Get LastPrices
     *
     * @param instrumentCodes
     * @return
     */
    @GetMapping("/last_price")
    public ResponseEntity<List<InstrumentPrice>> getLastPrice(
            @RequestParam("instrumentCodes") String instrumentCodes) {
        if (StringSupport.isNullOrEmpty(instrumentCodes))
            return badRequest();
        var lastPrices = stream(instrumentCodes.split(",")).map(instrumentCode -> lastPriceMap
                        .putIfAbsent(instrumentCode, new InstrumentPrice().setInstrumentCode(instrumentCode)))
                .collect(Collectors.toList());
        return responseOf(lastPrices);
    }

    /**
     * Put LastPrice
     *
     * @param request
     * @return
     */
    @PutMapping("/last_price")
    public ResponseEntity<Object> putLastPrice(
            @RequestBody HttpServletRequest request) {
        var price = bodyToObject(request, InstrumentPrice.class);
        if (price == null)
            return badRequest();
        lastPriceMap.put(price.getInstrumentCode(), price);
        return ok();
    }

    /**
     * Get [SymbolTradingFee] for [symbol]
     *
     * @param instrumentCode
     * @return
     */
    public ResponseEntity<Object> getSymbolTradingFeeByName(
            @RequestParam("instrumentCode") String instrumentCode) {
        var instrument = service.getInstrument(instrumentCode);
        return responseOf(instrument);
    }

    /**
     * Get [TradableInstrument] for [symbol] and [tradingDay]
     *
     * @param symbol
     * @param tradingDay
     * @return
     */
    @GetMapping("/tradable/{symbol}/{tradingDay}")
    public ResponseEntity<Object> getTradableInstrument(
            @PathVariable("symbol") String symbol,
            @PathVariable("tradingDay") String tradingDay) {
        return responseOf(null);
    }

}
