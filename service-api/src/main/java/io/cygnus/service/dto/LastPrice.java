package io.cygnus.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class LastPrice {

	@Getter
	@Setter
	private String instrumentId;

	@Getter
	@Setter
	private double lastPrice;

}
