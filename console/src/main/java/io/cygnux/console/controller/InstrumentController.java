package io.cygnux.console.controller;

import static java.util.Arrays.stream;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.cygnux.console.service.InstrumentService;
import io.cygnux.console.service.dto.InstrumentPrice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnux.console.controller.base.BaseController;
import io.cygnux.repository.entities.internal.InInstrument;
import io.cygnux.repository.entities.internal.InInstrumentStatic;
import io.mercury.common.util.StringSupport;

@RestController("/instrument")
public class InstrumentController extends BaseController {

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
    public ResponseEntity<List<InInstrumentStatic>> getSettlementPrice(
            @RequestParam("instrumentCode") String instrumentCode,
            @RequestParam("tradingDay") int tradingDay) {
        if (checkParamIsNull(instrumentCode, tradingDay)) {
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
     * @param instrumentCodes
     * @return
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
     * @param request
     * @return
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
     * @param instrumentCode
     * @return
     */
    public ResponseEntity<Object> getSymbolTradingFeeByName(@RequestParam("instrumentCode") String instrumentCode) {
        var instrument = service.getInstrument(instrumentCode);
        return responseOf(instrument);
    }

    /**
     * Get [TradeableInstrument] for [symbol] and [tradingDay]
     *
     * @param symbol
     * @param tradingDay
     * @return
     */
    @GetMapping("/tradeable/{symbol}/{tradingDay}")
    public ResponseEntity<Object> getTradeables(@RequestParam("symbol") String symbol,
                                                @RequestParam("tradingDay") String tradingDay) {
        return responseOf(null);
    }

}
