package io.redstone.adaptor.ctp.dto;

public class CtpInboundMsg {

	private CtpInboundTitle title;
	private String content;

	public CtpInboundMsg(CtpInboundTitle title, String content) {
		this.title = title;
		this.content = content;
	}

	public CtpInboundTitle getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

}
