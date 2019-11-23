package io.redstone.config.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class InitFinish {

	@JSONField(name = "CygID")
	private Integer cygId;

	public Integer getCygId() {
		return cygId;
	}

	public InitFinish setCygId(Integer cygId) {
		this.cygId = cygId;
		return this;
	}

}
