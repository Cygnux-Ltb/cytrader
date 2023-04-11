package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.component.CommandDispatcher;
import io.cygnuxltb.console.controller.base.ServiceException;
import io.cygnuxltb.console.controller.util.RequestUtil;
import io.cygnuxltb.console.controller.util.ResponseUtil;
import io.cygnuxltb.console.persistence.entity.ParamEntity;
import io.cygnuxltb.console.service.ParamService;
import io.cygnuxltb.protocol.http.dto.pack.OutboxMessage;
import io.cygnuxltb.protocol.http.dto.pack.OutboxTitle;
import io.mercury.common.log.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.mercury.common.http.MimeType.APPLICATION_JSON_UTF8;

@RestController
@RequestMapping(path = "/command")
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
     * @param productId int
     * @param request   HttpServletRequest
     * @return ResponseEntity<?>
     */
    @ExceptionHandler(ServiceException.class)
    @PutMapping(path = "/param", consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
    public ResponseEntity<?> updateParam(@RequestParam("productId") int productId,
                                         @RequestBody HttpServletRequest request) {
        // 将参数转换为List
        List<ParamEntity> strategyParams = RequestUtil.bodyToList(request, ParamEntity.class);
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
        return ResponseUtil.ok();
    }

    /**
     * @param request HttpServletRequest
     * @return ResponseEntity<?>
     */
    @ExceptionHandler(ServiceException.class)
    @PutMapping(path = "/safe", consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
    public ResponseEntity<?> updateParamSafe(@RequestBody HttpServletRequest request) {
        var strategyParam = RequestUtil.bodyToObject(request, ParamEntity.class);
        if (strategyParam == null)
            return ResponseUtil.badRequest();
        log.info("method updateParamSafe recv : {}", strategyParam);
        return switch (service.updateParamSafe(strategyParam)) {
            // 更新成功返回Ok状态码
            case 0 -> ResponseUtil.ok();
            // 返回错误参数状态码
            case -1 -> ResponseUtil.badRequest();
            // 否则返回服务器内部错误状态码
            default -> ResponseUtil.internalServerError();
        };
    }

}
