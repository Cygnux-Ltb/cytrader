package io.cygnus.restful.service.controller;

import static io.mercury.transport.http.MimeType.APPLICATION_JSON_UTF8;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.repository.entity.BarEntity;
import io.cygnus.repository.service.BarService;
import io.cygnus.restful.service.base.BaseController;

@RestController("/bar")
public class BarController extends BaseController {

	@Resource
	private BarService service;

	/**
	 * Get Bars
	 * 
	 * @param instrumentId
	 * @param tradingDay
	 * @return
	 */
	@GetMapping
	public List<BarEntity> getBars(@RequestParam("instrumentCode") String instrumentCode,
			@RequestParam("tradingDay") int tradingDay) {
		return service.getBars(instrumentCode, tradingDay);
	}

	/**
	 * Put Bar
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping(consumes = APPLICATION_JSON_UTF8)
	public ResponseEntity<Integer> putBar(@RequestBody HttpServletRequest request) {
		String json = getBody(request);
		BarEntity bar = toObject(json, BarEntity.class);
		return service.putBar(bar) ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : internalServerError();
	}

}
