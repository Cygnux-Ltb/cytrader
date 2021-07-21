package io.cygnus.restful.service.controller;

import static io.mercury.transport.http.MimeType.APPLICATION_JSON_UTF8;

import java.util.ArrayList;
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

import io.cygnus.repository.dao.BarDao;
import io.cygnus.repository.entity.BarEntity;
import io.cygnus.repository.service.BarService;
import io.cygnus.restful.service.base.CygRestfulApi;

@RestController("/bar")
public class BarController extends CygRestfulApi {

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
		
		
		
		List<BarEntity> bars = service.getTimeBinners(null, instrumentCode)
				
				query(instrumentCode, tradingDay);
		if (bars == null)
			return new ArrayList<>();
		return bars;
	}

	/**
	 * Put Binner
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping(consumes = APPLICATION_JSON_UTF8)
	public ResponseEntity<Integer> putBinners(@RequestBody HttpServletRequest request) {
		String json = getBody(request);
		BarEntity bar = jsonToObj(json, BarEntity.class);
		try {
			dao.save(bar);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (Exception e) {
			return internalServerError();
		}
	}

}
