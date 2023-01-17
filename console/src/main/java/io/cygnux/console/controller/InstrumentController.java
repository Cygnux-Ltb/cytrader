package io.cygnux.console.controller;

import io.cygnux.console.service.dto.InstrumentPrice;
import io.cygnux.console.service.InstrumentService;
import io.cygnux.console.persistence.entity.InstrumentSettlementEntity;
import io.mercury.common.util.StringSupport;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static io.cygnux.console.controller.util.ParamsValidateUtil.bodyToObject;
import static io.cygnux.console.controller.util.ParamsValidateUtil.paramIsNull;
import static io.cygnux.console.controller.util.ResponseUtil.badRequest;
import static io.cygnux.console.controller.util.ResponseUtil.ok;
import static io.cygnux.console.controller.util.ResponseUtil.responseOf;
import static java.util.Arrays.stream;

@RestController("/instrument")
public final class InstrumentController {

    @Resource
    private InstrumentService service;

    /**
     * Get Settlement Price
     *
     * @param instrumentCode String
     * @param tradingDay     int
     * @return ResponseEntity<List < InstrumentSettlementEntity>>
     */
    @GetMapping("/settlement")
    public ResponseEntity<List<InstrumentSettlementEntity>> getSettlementPrice(
            @RequestParam("instrumentCode") String instrumentCode,
            @RequestParam("tradingDay") int tradingDay) {
        if (paramIsNull(instrumentCode, tradingDay)) {
            return badRequest();
        }
        var instrumentSettlements = service.getInstrumentSettlement(instrumentCode, tradingDay);
        return responseOf(instrumentSettlements);
    }

    // LastPrices Cache
    private static final ConcurrentHashMap<String, InstrumentPrice> lastPriceMap = new ConcurrentHashMap<>();

    /**
     * Get LastPrices
     *
     * @param instrumentCodes String
     * @return ResponseEntity<List < InstrumentPrice>>
     */
    @GetMapping("/last_price")
    public ResponseEntity<List<InstrumentPrice>> getLastPrice(@RequestParam("instrumentCodes") String instrumentCodes) {
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
     * @param request HttpServletRequest
     * @return ResponseEntity<Object>
     */
    @PutMapping("/last_price")
    public ResponseEntity<Object> putLastPrice(@RequestBody HttpServletRequest request) {
        var price = bodyToObject(request, InstrumentPrice.class);
        if (price == null)
            return badRequest();
        lastPriceMap.put(price.getInstrumentCode(), price);
        return ok();
    }

    /**
     * Get [SymbolTradingFee] for [symbol]
     *
     * @param instrumentCode String
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> getSymbolTradingFeeByName(@RequestParam("instrumentCode") String instrumentCode) {
        var instrument = service.getInstrument(instrumentCode);
        return responseOf(instrument);
    }

    /**
     * Get [TradableInstrument] for [symbol] and [tradingDay]
     *
     * @param symbol     String
     * @param tradingDay String
     * @return ResponseEntity<Object>
     */
    @GetMapping("/tradable/{symbol}/{tradingDay}")
    public ResponseEntity<Object> getTradableInstrument(@PathVariable("symbol") String symbol,
                                                        @PathVariable("tradingDay") String tradingDay) {
        return responseOf(null);
    }

}
