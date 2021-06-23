package io.cygnus.restful.service.resources;

import static io.mercury.transport.http.MimeType.APPLICATION_JSON_UTF8;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cygnus.persistence.entity.TimeBinner;
import io.cygnus.persistence.service.BinnerDao;
import io.cygnus.restful.service.base.CygRestfulApi;

@RestController("/binner")
public class BinnerRestfulApi extends CygRestfulApi {

	/**
	 * Get Binners
	 * 
	 * @param cygId
	 * @param instrumentId
	 * @param tradingDay
	 * @return
	 */
	@GetMapping("/{cygId}")
	public List<TimeBinner> getBinners(@PathVariable("cygId") Integer cygId,
			@RequestParam("instrumentId") String instrumentId, @RequestParam("tradingDay") String tradingDay) {
		Date dateTradingDay = null;
		if (tradingDay != null) {
			dateTradingDay = changeTradingDay(tradingDay);
			if (dateTradingDay == null) {
				return null;
			}
		}
		BinnerDao dao = new BinnerDao();
		List<TimeBinner> timeBinners = dao.getTimeBinners(cygId, dateTradingDay, instrumentId);
		return timeBinners;
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
		TimeBinner timeBinner = jsonToObj(json, TimeBinner.class);
		BinnerDao binnerDao = new BinnerDao();
		if (binnerDao.addTimeBinners(timeBinner)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return httpInternalServerError();
	}

}
