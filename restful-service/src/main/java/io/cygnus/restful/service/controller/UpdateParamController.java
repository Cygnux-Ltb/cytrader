package io.cygnus.restful.service.controller;

import static io.mercury.transport.http.MimeType.APPLICATION_JSON_UTF8;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.persistence.entity.StrategyParam;
import io.cygnus.restful.service.base.BaseController;
import io.cygnus.restful.service.base.CygRestfulApi;
import io.cygnus.restful.service.resources.executor.UpdateParamExecutor;
import io.cygnus.restful.service.transport.OutboxPublisherGroup;
import io.cygnus.service.dto.pack.OutboxMessage;
import io.cygnus.service.dto.pack.OutboxTitle;
import io.mercury.common.character.Charsets;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.StringUtil;
import io.mercury.transport.api.Publisher;

@RestController("/update_param")
public class UpdateParamController extends BaseController {

	private final Logger log = CommonLoggerFactory.getLogger(getClass());

	private UpdateParamExecutor executor = new UpdateParamExecutor();

	/**
	 * 
	 * @param cygId
	 * @param request
	 * @return
	 */
	@PutMapping(consumes = APPLICATION_JSON_UTF8, produces = APPLICATION_JSON_UTF8)
	public ResponseEntity<Object> updateParam(@RequestParam("cygId") Integer cygId,
			@RequestBody HttpServletRequest request) {
		// 获取输入参数
		String json = getBody(request);
		log.info("method updateParam recv : {}", json);
		if (StringUtil.isNullOrEmpty(json)) {
			return httpBadRequest();
		}
		// 将参数转换为List
		List<StrategyParam> strategyParams = toList(json, StrategyParam.class);
		// 获取Publisher
		Publisher<byte[]> publisher = OutboxPublisherGroup.INSTANCE.acquireMember(cygId);
		// 转换为需要发送的发件箱消息
		String msg = outboxMessageToJson(new OutboxMessage<>(OutboxTitle.UpdateStrategyParams.name(), strategyParams));
		// 发送消息
		publisher.publish(msg.getBytes(Charsets.UTF8));
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
		// 获取输入参数
		String json = getBody(request);
		log.info("method updateParamSafe recv : {}", json);
		if (StringUtil.isNullOrEmpty(json)) {
			return httpBadRequest();
		}
		// 将参数转换为StrategyParam
		StrategyParam strategyParam = toObject(json, StrategyParam.class);
		if (checkParamIsNull(strategyParam)) {
			return httpBadRequest();
		}
		switch (executor.updateParamSafe(strategyParam)) {
		// 更新成功返回Ok状态码
		case 1:
			return httpOk();
		// 返回错误参数状态码
		case -1:
			return httpBadRequest();
		// 否则返回服务器内部错误状态码
		default:
			return httpInternalServerError();
		}
	}

}
