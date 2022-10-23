package io.cygnux.console.controller;

import io.cygnux.console.service.ParamService;
import io.cygnux.console.service.StrategyService;
import io.cygnux.repository.entity.ParamEntity;
import io.cygnux.repository.entity.StrategyEntity;
import io.mercury.common.log.Log4j2LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static io.cygnux.console.utils.ParamsValidateUtil.bodyToObject;
import static io.cygnux.console.utils.ResponseUtil.badRequest;
import static io.cygnux.console.utils.ResponseUtil.internalServerError;
import static io.cygnux.console.utils.ResponseUtil.ok;
import static io.cygnux.console.utils.ResponseUtil.responseOf;

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
     * @return ResponseEntity<List < StrategyEntity>>
     */
    @GetMapping
    public ResponseEntity<List<StrategyEntity>> getStrategies() {
        return responseOf(strategyService.getStrategies());
    }

    /**
     * 使用StrategyId作为get params访问Strategy
     *
     * @param strategyId int
     * @return ResponseEntity<StrategyEntity>
     */
    public ResponseEntity<StrategyEntity> getStrategyById(@RequestParam("strategyId") int strategyId) {
        StrategyEntity strategy = strategyService.getStrategy(strategyId);
        return responseOf(strategy);
    }

    /**
     * 使用StrategyId作为URI访问Param
     *
     * @param strategyId int
     * @return ResponseEntity<Object>
     */
    @GetMapping("/param")
    public ResponseEntity<Object> getParamsByStrategyId(@RequestParam("strategyId") int strategyId) {
        List<ParamEntity> strategyParams = paramService.getStrategyParams(strategyId);
        return responseOf(strategyParams);
    }

    /**
     * Put StrategyParam URI is StrategyId
     *
     * @param strategyId int
     * @return HttpServletRequest
     */
    @PutMapping("/{strategyId}/param")
    public ResponseEntity<Object> putParamsByStrategyId(@PathVariable("strategyId") int strategyId,
                                                        @RequestBody HttpServletRequest request) {
        var params = bodyToObject(request, ParamEntity.class);
        log.info("putParamsByStrategyId recv : {}", params);
        return params == null ? badRequest() : paramService.putStrategyParam(params) ? ok() : internalServerError();
    }

}
