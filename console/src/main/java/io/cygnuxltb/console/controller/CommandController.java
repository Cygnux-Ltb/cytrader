package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.component.CommandDispatcher;
import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.controller.util.ControllerUtil;
import io.cygnuxltb.console.persistence.entity.ParamEntity;
import io.cygnuxltb.console.service.ParamService;
import io.cygnuxltb.protocol.http.pack.OutboxMessage;
import io.cygnuxltb.protocol.http.pack.OutboxTitle;
import io.mercury.common.http.MimeType;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

/**
 * 系统指令服务
 */
@RestController
@RequestMapping(path = "/command", produces = MimeType.APPLICATION_JSON_UTF8)
public final class CommandController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CommandController.class);

    @Resource
    private CommandDispatcher dispatcher;

    @Resource
    private ParamService service;

    public ResponseEntity<String> get() {
        return null;
    }


    /**
     * 更新参数
     *
     * @param productId int
     * @param request   HttpServletRequest
     * @return ResponseEntity<?>
     */
    @PutMapping(path = "/param", consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
    public ResponseStatus updateParam(@RequestParam("productId") int productId,
                                      @RequestBody HttpServletRequest request) {
        // 将参数转换为List
        List<ParamEntity> strategyParams = ControllerUtil.bodyToList(request, ParamEntity.class);
        // 获取Publisher
        //dispatcher.sendCommand();
        //Publisher<String, String> publisher = GROUP_INSTANCE.getMember(cygId);
        // 转换为需要发送的发件箱消息
        String msg = JsonWrapper.toJson(new OutboxMessage<>()
                .setTitle(OutboxTitle.UpdateStrategyParams.name())
                .setContent(strategyParams));
        // 发送消息
        //publisher.publish(msg);
        // 返回Put成功标识
        return ResponseStatus.UPDATED;
    }

    /**
     * 安全更新参数
     *
     * @param request HttpServletRequest
     * @return ResponseEntity<?>
     */
    @PutMapping(path = "/safe", consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
    public ResponseStatus updateParamSafe(@RequestBody HttpServletRequest request) {
        var strategyParam = ControllerUtil.bodyToObject(request, ParamEntity.class);
        if (strategyParam == null)
            return ResponseStatus.BAD_REQUEST;
        log.info("method updateParamSafe recv : {}", strategyParam);
        return switch (service.updateParamSafe(strategyParam)) {
            // 更新成功返回Ok状态码
            case 0 -> ResponseStatus.UPDATED;
            // 返回错误参数状态码
            case -1 -> ResponseStatus.BAD_REQUEST;
            // 否则返回服务器内部错误状态码
            default -> ResponseStatus.INTERNAL_ERROR;
        };
    }

}
