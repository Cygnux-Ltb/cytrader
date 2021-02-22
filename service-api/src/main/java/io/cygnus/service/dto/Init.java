package io.cygnus.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class Init {

	@Getter
	@Setter
	private Integer cygId;

	@Getter
	@Setter
	private String tradingDay;

}
