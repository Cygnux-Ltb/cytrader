package io.cygnux.console.controller;

import static io.mercury.transport.http.base.MimeType.APPLICATION_JSON_UTF8;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.cygnux.console.service.BarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnux.console.controller.base.BaseController;
import io.cygnux.repository.entity.CygBar;

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
	public List<CygBar> getBars(@RequestParam("instrumentCode") String instrumentCode,
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
		CygBar bar = bodyToObject(request, CygBar.class);
		return bar == null ? badRequest()
				: service.putBar(bar) ? ResponseEntity.status(HttpStatus.ACCEPTED).build() : internalServerError();
	}

}
