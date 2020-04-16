package io.mercury.ftdc.adaptor.dto;

public class CtpOutboundMsg {

	private CtpOutboundTitle title;
	private String content;

	public CtpOutboundMsg(CtpOutboundTitle title, String content) {
		this.title = title;
		this.content = content;
	}

	public CtpOutboundTitle getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

}
