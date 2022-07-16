package io.cygnux.console.controller;

import io.cygnux.console.dto.pack.OutboxMessage;
import io.cygnux.console.dto.pack.OutboxTitle;
import io.cygnux.console.service.ParamService;
import io.cygnux.console.transport.CommandDispatcher;
import io.cygnux.repository.entities.TParam;
import io.mercury.common.log.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import io.mercury.transport.api.Publisher;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static io.cygnux.console.utils.ParamsValidateUtil.bodyToList;
import static io.cygnux.console.utils.ParamsValidateUtil.bodyToObject;
import static io.cygnux.console.utils.ResponseUtil.*;
import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

@RestController("command")
public final class CommandController {

    @Resource
    private CommandDispatcher dispatcher;

    @Resource
    private ParamService service;

    private static final Logger log = Log4j2LoggerFactory.getLogger(CommandController.class);

    public ResponseEntity<String> get() {

        return null;
    }


    /**
     * @param cygId
     * @param request
     * @return
     */
    @PutMapping(path = "/param", consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
    public ResponseEntity<Object> updateParam(@RequestParam("productId") int productId,
                                              @RequestBody HttpServletRequest request) {
        // 将参数转换为List
        List<TParam> strategyParams = bodyToList(request, TParam.class);
        // 获取Publisher
        dispatcher.sendCommand();
        Publisher<String, String> publisher = GROUP_INSTANCE.getMember(cygId);
        // 转换为需要发送的发件箱消息
        String msg = JsonWrapper.toJson(new OutboxMessage<>(OutboxTitle.UpdateStrategyParams.name(), strategyParams));
        // 发送消息
        publisher.publish(msg);
        // 返回Put成功标识
        return ok();
    }

    /**
     * @param request
     * @return
     */
    @PutMapping(path = "/safe", consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
    public ResponseEntity<Object> updateParamSafe(@RequestBody HttpServletRequest request) {
        var strategyParam = bodyToObject(request, TParam.class);
        if (strategyParam == null)
            return badRequest();
        log.info("method updateParamSafe recv : {}", strategyParam);
        return switch (service.updateParamSafe(strategyParam)) {
            // 更新成功返回Ok状态码
            case 0 -> ok();
            // 返回错误参数状态码
            case -1 -> badRequest();
            // 否则返回服务器内部错误状态码
            default -> internalServerError();
        };
    }


}
