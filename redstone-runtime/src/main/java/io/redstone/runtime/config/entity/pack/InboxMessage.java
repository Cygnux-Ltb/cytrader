package io.redstone.engine.config.entity.pack;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = { "Title", "Content" })
public class InboxMessage {

	@JSONField(name = "Title")
	private String title;

	@JSONField(name = "Content")
	private String content;

	public InboxMessage() {
	}

	/**
	 * @param title
	 * @param content
	 */
	public InboxMessage(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public InboxMessage setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public InboxMessage setContent(String content) {
		this.content = content;
		return this;
	}

}
