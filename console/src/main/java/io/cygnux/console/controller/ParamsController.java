package io.cygnux.console.controller;

import io.cygnux.console.service.ParamService;
import io.cygnux.console.service.dto.pack.OutboxMessage;
import io.cygnux.console.service.dto.pack.OutboxTitle;
import io.cygnux.repository.entities.ItParam;
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

import static io.cygnux.console.transport.OutboxPublisherGroup.GROUP_INSTANCE;
import static io.cygnux.console.utils.ControllerUtil.*;
import static io.cygnux.console.utils.ParamsValidateUtil.bodyToList;
import static io.cygnux.console.utils.ParamsValidateUtil.bodyToObject;
import static io.mercury.transport.http.base.MimeType.APPLICATION_JSON_UTF8;

@RestController("/params")
public final class ParamsController {

    private final Logger log = Log4j2LoggerFactory.getLogger(getClass());

    @Resource
    private ParamService service;

    /**
     * @param cygId
     * @param request
     * @return
     */
    @PutMapping(consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
    public ResponseEntity<Object> updateParam(@RequestParam("cygId") Integer cygId,
                                              @RequestBody HttpServletRequest request) {

        // 将参数转换为List
        List<ItParam> strategyParams = bodyToList(request, ItParam.class);
        // 获取Publisher
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
        var strategyParam = bodyToObject(request, ItParam.class);
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
