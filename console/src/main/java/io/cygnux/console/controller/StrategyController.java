package io.cygnux.console.controller;

import io.cygnux.console.service.ParamService;
import io.cygnux.console.service.StrategyService;
import io.cygnux.repository.entities.ItParam;
import io.cygnux.repository.entities.ItStrategy;
import io.mercury.common.log.Log4j2LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static io.cygnux.console.utils.ControllerUtil.*;
import static io.cygnux.console.utils.ParamsValidateUtil.bodyToObject;

@RestController("/strategy")
public final class StrategyController {

    private final Logger log = Log4j2LoggerFactory.getLogger(getClass());

    @Resource
    private StrategyService strategyService;

    @Resource
    private ParamService paramService;

    /**
     * 返回全部Strategy
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ItStrategy>> getStrategies() {
        return responseOf(strategyService.getStrategies());
    }

    /**
     * 使用StrategyId作为get params访问Strategy
     *
     * @param strategyId
     * @return
     */
    public ResponseEntity<ItStrategy> getStrategyById(@RequestParam("strategyId") int strategyId) {
        ItStrategy strategy = strategyService.getStrategy(strategyId);
        return responseOf(strategy);
    }

    /**
     * 使用StrategyId作为URI访问Param
     *
     * @param strategyId
     * @return
     */
    @GetMapping("/param")
    public ResponseEntity<Object> getParamsByStrategyId(@RequestParam("strategyId") int strategyId) {
        List<ItParam> strategyParams = paramService.getStrategyParams(strategyId);
        return responseOf(strategyParams);
    }

    /**
     * Put StrategyParam URI is StrategyId
     *
     * @param strategyId
     * @return
     */
    @PutMapping("/{strategyId}/param")
    public ResponseEntity<Object> putParamsByStrategyId(@PathVariable("strategyId") int strategyId, @RequestBody HttpServletRequest request) {
        var params = bodyToObject(request, ItParam.class);
        log.info("putParamsByStrategyId recv : {}", params);
        return params == null ? badRequest() : paramService.putStrategyParam(params) ? ok() : internalServerError();
    }

}
