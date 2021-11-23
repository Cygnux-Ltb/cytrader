package io.cygnus.console.controller;

import static io.cygnus.console.transport.OutboxPublisherGroup.GROUP_INSTANCE;
import static io.mercury.transport.http.MimeType.APPLICATION_JSON_UTF8;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.console.controller.base.BaseController;
import io.cygnus.console.service.ParamService;
import io.cygnus.console.service.dto.pack.OutboxMessage;
import io.cygnus.console.service.dto.pack.OutboxTitle;
import io.cygnus.repository.entity.CygStrategyParam;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import io.mercury.transport.api.Publisher;

@RestController("/update_param")
public class UpdateParamController extends BaseController {

	private final Logger log = CommonLoggerFactory.getLogger(getClass());

	@Resource
	private ParamService service;

	/**
	 * 
	 * @param cygId
	 * @param request
	 * @return
	 */
	@PutMapping(consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
	public ResponseEntity<Object> updateParam(@RequestParam("cygId") Integer cygId,
			@RequestBody HttpServletRequest request) {

		// 将参数转换为List
		List<CygStrategyParam> strategyParams = bodyToList(request, CygStrategyParam.class);
		// 获取Publisher
		Publisher<String> publisher = GROUP_INSTANCE.getMember(cygId);
		// 转换为需要发送的发件箱消息
		String msg = JsonWrapper.toJson(new OutboxMessage<>(OutboxTitle.UpdateStrategyParams.name(), strategyParams));
		// 发送消息
		publisher.publish(msg);
		// 返回Put成功标识
		return ok();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping(path = "/safe", consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
	public ResponseEntity<Object> updateParamSafe(@RequestBody HttpServletRequest request) {
		var strategyParam = bodyToObject(request, CygStrategyParam.class);
		if (strategyParam == null)
			return badRequest();
		log.info("method updateParamSafe recv : {}", strategyParam);
		switch (service.updateParamSafe(strategyParam)) {
		// 更新成功返回Ok状态码
		case 1:
			return ok();
		// 返回错误参数状态码
		case -1:
			return badRequest();
		// 否则返回服务器内部错误状态码
		default:
			return internalServerError();
		}
	}

}
